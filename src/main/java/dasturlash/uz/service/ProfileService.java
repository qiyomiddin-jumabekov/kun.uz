package dasturlash.uz.service;

import dasturlash.uz.dto.profile.RequestDtoForProfile;
import dasturlash.uz.dto.profile.RequestDtoUpdateProfileByDetails;
import dasturlash.uz.dto.profile.RequestForUpdatePassword;
import dasturlash.uz.entity.Profile;
import dasturlash.uz.enums.Visible;
import dasturlash.uz.projections.ProfileShortInfo;
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

    public Page<ProfileShortInfo> getAllProfilesByPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return profileRepository.getAllProfilesByPagination(pageable);
    }

    public String deleteProfileById(Integer id) {
        Profile profile = getProfileById(id);
        profile.setVisible(Visible.INACTIVE);
        profileRepository.save(profile);
        return "Profile with id " + id + " has been deleted";
    }

    public String updatePassword(Integer profileId, RequestForUpdatePassword request) {
        Profile profile = getProfileById(profileId);
        String oldPassword = profile.getPassword();
        if (bCryptPasswordEncoder.matches(request.oldPassword(), oldPassword)) {
            profile.setPassword(bCryptPasswordEncoder.encode(request.newPassword()));
            profileRepository.save(profile);
            return "Profile with id " + profileId + " has been updated";
        }
        throw new IllegalArgumentException("Passwords do not match");
    }

    public Page<ProfileShortInfo> getProfilesByFilter(int page, int size, String query) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return profileRepository.getProfilesByFilter(pageable, query);
    }
}
