package dasturlash.uz.controller;

import dasturlash.uz.dto.RequestDtoForProfile;
import dasturlash.uz.dto.RequestDtoUpdateProfileByDetails;
import dasturlash.uz.dto.RequestForUpdatePassword;
import dasturlash.uz.entity.Profile;
import dasturlash.uz.projections.ProfileShortInfo;
import dasturlash.uz.service.ProfileService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@Validated
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/by-id/{id}")
    public ResponseEntity<Profile> getProfileById(
            @PathVariable @Min(value = 1, message = "Id is not negative nums") Integer id) {
        return ResponseEntity.ok().body(profileService.getProfileById(id));
    }

    @PutMapping("")
    public ResponseEntity<Profile> updateProfile(
            @RequestParam("id") Integer id,
            @RequestBody @Valid RequestDtoForProfile request) {
        return ResponseEntity.ok().body(profileService.updateProfile(id, request));
    }

    @PutMapping("/update-detail/{id}")
    public ResponseEntity<Profile> updateProfileByProfileDetails(
            @PathVariable Integer id,
            @RequestBody RequestDtoUpdateProfileByDetails request) {
        return ResponseEntity.ok().body(profileService.updateProfileByDetails(id, request));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ProfileShortInfo>> getAllProfiles(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        return ResponseEntity.ok().body(profileService.getAllProfilesByPagination(page, size));
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteProfile(
            @RequestParam("id") Integer id) {
        return ResponseEntity.ok().body(profileService.deleteProfileById(id));
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<String> changePassword(
            @PathVariable Integer id,
            @RequestBody RequestForUpdatePassword request) {
        return ResponseEntity.ok().body(profileService.updatePassword(id, request));
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<ProfileShortInfo>> getAllProfilesByFilter(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "query", required = false) String query) {
        return ResponseEntity.ok().body(profileService.getProfilesByFilter(page, size, query));
    }
}
