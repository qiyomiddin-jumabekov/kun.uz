package dasturlash.uz.service;

import dasturlash.uz.entity.Profile;
import dasturlash.uz.repository.ProfileRepository;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public Profile getProfileById(Integer id) {
        Optional<Profile> profile = profileRepository.findById(id);
        if (profile.isPresent()) {
            return profile.get();
        }
        throw new IllegalArgumentException("Profile with id " + id + " not found");
    }
}
