package dasturlash.uz.service;

import dasturlash.uz.dto.profile.RequestDtoForProfile;
import dasturlash.uz.entity.Profile;
import dasturlash.uz.repository.ProfileRepository;
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
}
