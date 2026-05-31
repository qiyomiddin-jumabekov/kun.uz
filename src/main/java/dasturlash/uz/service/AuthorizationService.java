package dasturlash.uz.service;

import dasturlash.uz.dto.auth.RequestForLogin;
import dasturlash.uz.dto.auth.ResponseDtoForLogin;
import dasturlash.uz.dto.profile.RequestDtoForProfile;
import dasturlash.uz.entity.Profile;
import dasturlash.uz.enums.Status;
import dasturlash.uz.enums.Visible;
import dasturlash.uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailVerificationService emailService;

    @Autowired
    private SmsService smsService;


    public String registerUser(RequestDtoForProfile request) {
        if (profileRepository.existsByUsername(request.username())) {
            throw new IllegalArgumentException("Username is already in use");
        }
        boolean exists = profileRepository.existsByEmail(request.email());
        if (exists) {
            throw new IllegalArgumentException("Email is already in use");
        }
        Profile profile = new Profile();
        profile.setName(request.name());
        profile.setSurname(request.surname());
        profile.setUsername(request.username());
        profile.setPassword(bCryptPasswordEncoder.encode(request.password()));
        profile.setEmail(request.email());
        profile.setVisible(Visible.INACTIVE);
        profile.setStatus(Status.NOT_ACTIVE);
        profileRepository.save(profile);

//        boolean emailSend = emailService.emailVerifyMethod(
//                request.email(),
//                request.username());

        boolean smsSend = smsService.sendSms(request.phoneNumber());
        if (!smsSend) {
            throw new IllegalArgumentException("Verification failed");
        }
        return "User registered successfully! Verification code is send";
    }

    public ResponseDtoForLogin userLogin(RequestForLogin request) {
        Profile profile = profileRepository.findByUsername(request.username());
        if (profile == null) {
            throw new IllegalArgumentException("Username or password not found");
        }
        if (!bCryptPasswordEncoder.matches(request.password(), profile.getPassword())) {
            throw new IllegalArgumentException("Username or  password not found");
        }
        if (profile.getStatus() == Status.NOT_ACTIVE) {
            throw new IllegalArgumentException("User is not active! Please confirm your email!");
        }


        return new ResponseDtoForLogin(
                profile.getName(),
                profile.getSurname(),
                profile.getUsername(),
                profile.getPhotoId()
        );
    }
}
