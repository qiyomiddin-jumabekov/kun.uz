package dasturlash.uz.service;

import dasturlash.uz.dto.RequestDtoForUpdateProfile;
import dasturlash.uz.entity.Profile;
import dasturlash.uz.repository.ProfileRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Profile getProfileById(Integer id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Profile with id " + id + " not found"));
    }

    public Profile updateProfile(Integer id, @Valid RequestDtoForUpdateProfile request) {
        Profile oldProfile = getProfileById(id);

        oldProfile.setSurname(request.surname());
        oldProfile.setName(request.name());
        oldProfile.setPassword(bCryptPasswordEncoder.encode(request.password()));
        oldProfile.setUsername(request.username());
        return profileRepository.save(oldProfile);
    }
}
