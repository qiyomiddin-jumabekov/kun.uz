package dasturlash.uz.service;

import dasturlash.uz.dto.verification.ConfirmDto;
import dasturlash.uz.entity.EmailHistory;
import dasturlash.uz.entity.EmailVerification;
import dasturlash.uz.entity.Profile;
import dasturlash.uz.enums.CodeStatus;
import dasturlash.uz.enums.Status;
import dasturlash.uz.enums.Visible;
import dasturlash.uz.repository.EmailRepository;
import dasturlash.uz.repository.EmailVerificationRepository;
import dasturlash.uz.repository.ProfileRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailVerificationService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private EmailVerificationRepository emailVerificationRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private EmailRepository emailRepository;

    public boolean sendCode(String toEmail, String code) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("Verification Code");
            helper.setText("""
                    <div style="font-family: Arial; max-width: 500px; margin: auto;
                                border: 1px solid #ddd; border-radius: 10px; padding: 30px;">
                        <h2 style="color: #4CAF50; text-align: center;">Tasdiqlash Kodi</h2>
                        <p style="text-align: center; font-size: 14px; color: #555;">
                            Sizning tasdiqlash kodingiz:
                        </p>
                        <div style="text-align: center; background: #f5f5f5;
                                    border-radius: 8px; padding: 20px; margin: 20px 0;">
                            <h1 style="color: #333; letter-spacing: 10px;">%s</h1>
                        </div>
                        <p style="text-align: center; color: #999; font-size: 12px;">
                            Kod 2 daqiqa ichida amal qiladi
                        </p>
                    </div>
                    """.formatted(code), true);

            mailSender.send(message);
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException("Email yuborilmadi!");
        }
    }

    public boolean emailVerifyMethod(String e, String username) {
        String code = String.valueOf((int) (Math.random() * 90000) + 10000);
        boolean d = sendCode(e, code);
        if (d) {
            EmailVerification email = new EmailVerification();
            email.setEmail(e);
            email.setCode(code);
            email.setUsername(username);
            email.setCreatedTime(LocalDateTime.now());
            email.setExpiredTime(LocalDateTime.now().plusMinutes(2));
            email.setCodeStatus(CodeStatus.SEND);
            emailVerificationRepository.save(email);

            // For Email History
            EmailHistory history = new EmailHistory();
            history.setMessage("Code : " + code);
            history.setCreatedDate(LocalDateTime.now());
            history.setEmail(e);
            emailRepository.save(history);
            return true;
        }
        return false;
    }

    public String confirmCode(ConfirmDto request) {
        Profile profile = profileRepository.findByEmail(request.email());
        if (profile == null) {
            throw new IllegalArgumentException("Profile not found with email");
        } else if (profile.getStatus() == Status.ACTIVE) {
            throw new IllegalArgumentException("Profile is already active");
        }
        boolean d = emailVerificationRepository.confirmEmailVerification(
                request.email(),
                request.code(),
                LocalDateTime.now()
        );
        if (d) {
            profile.setStatus(Status.ACTIVE);
            profile.setVisible(Visible.ACTIVE);
            profileRepository.save(profile);
            emailVerificationRepository.updateCodeStatus(request.email(), CodeStatus.CONFIRMED);
            return "Confirm Success";
        }
        emailVerificationRepository.updateCodeStatus(request.email(), CodeStatus.FAILED);
        return "Confirm Failed Code or Email Is not Found! Please try again";
    }

    public String resentCode(String email) {
        Profile profile = profileRepository.findByEmail(email);
        if (profile == null) {
            throw new IllegalArgumentException("Profile not found with email! Please register");
        }
        if (profile.getStatus() == Status.ACTIVE) {
            throw new IllegalArgumentException("Profile is already active");
        }
        boolean b = emailVerifyMethod(email, profile.getUsername());
        if (b) {
            return "Code successfully resent";
        }
        throw new IllegalArgumentException("Email resent failed");
    }
}
