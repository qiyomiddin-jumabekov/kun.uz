package dasturlash.uz.service;

import dasturlash.uz.dto.RequestDtoForProfile;
import dasturlash.uz.dto.RequestDtoUpdateProfileByDetails;
import dasturlash.uz.entity.Profile;
import dasturlash.uz.enums.Visible;
import dasturlash.uz.projections.StudentShortInfo;
import dasturlash.uz.repository.ProfileRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Profile getProfileById(Integer id) {
        return profileRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Profile with id " + id + " not found"));
    }

    public Profile updateProfile(Integer id, @Valid RequestDtoForProfile request) {
        Profile oldProfile = getProfileById(id);

        oldProfile.setSurname(request.surname());
        oldProfile.setName(request.name());
        oldProfile.setPassword(bCryptPasswordEncoder.encode(request.password()));
        oldProfile.setUsername(request.username());
        return profileRepository.save(oldProfile);
    }

    public Profile updateProfileByDetails(Integer id, RequestDtoUpdateProfileByDetails request) {
        Profile oldProfile = getProfileById(id);
        if (request.name() != null && !request.name().isBlank()) {
            oldProfile.setName(request.name());
        }
        if (request.surname() != null && !request.surname().isBlank()) {
            oldProfile.setSurname(request.surname());
        }
        if (request.password() != null && !request.password().isBlank()) {
            oldProfile.setPassword(bCryptPasswordEncoder.encode(request.password()));
        }
        if (request.username() != null && !request.username().isBlank()) {
            oldProfile.setUsername(request.username());
        }
        return profileRepository.save(oldProfile);
    }

    public Page<StudentShortInfo> getAllProfilesByPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return profileRepository.getAllProfilesByPagination(pageable);
    }

    public String deleteProfileById(Integer id) {
        Profile profile = getProfileById(id);
        profile.setVisible(Visible.INACTIVE);
        profileRepository.save(profile);
        return "Profile with id " + id + " has been deleted";
    }
}
