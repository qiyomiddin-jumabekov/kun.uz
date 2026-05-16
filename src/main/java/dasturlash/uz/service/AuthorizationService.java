package dasturlash.uz.service;

import dasturlash.uz.dto.auth.RequestForLogin;
import dasturlash.uz.dto.auth.ResponseDtoForLogin;
import dasturlash.uz.dto.profile.RequestDtoForProfile;
import dasturlash.uz.entity.Profile;
import dasturlash.uz.repository.ProfileRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;


    public ResponseEntity<String> registerUser(RequestDtoForProfile request) {
        if (profileRepository.existsByUsername(request.username())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        Profile profile = new Profile();
        profile.setName(request.name());
        profile.setSurname(request.surname());
        profile.setUsername(request.username());
        profile.setPassword(bCryptPasswordEncoder.encode(request.password()));
        profileRepository.save(profile);
        return ResponseEntity.ok("User registered successfully");
    }

    public ResponseDtoForLogin userLogin(@Valid RequestForLogin request) {
        Profile profile = profileRepository.findByUsername(request.username());
        if (profile == null) {
            throw new IllegalArgumentException("Username or password not found");
        }
        if (!bCryptPasswordEncoder.matches(request.password(), profile.getPassword())) {
            throw new IllegalArgumentException("Username or  password not found");
        }
//        String code = String.valueOf((int) (Math.random() * 900000) + 100000);
//        emailService.sendCode("qiyomiddinjumabekov1@gmail.com", code);

        return new ResponseDtoForLogin(
                profile.getName(),
                profile.getSurname(),
                profile.getUsername(),
                profile.getPhotoId()
        );
    }
}
