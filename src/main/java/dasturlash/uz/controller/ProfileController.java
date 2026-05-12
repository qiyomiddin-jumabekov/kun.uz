package dasturlash.uz.controller;

import dasturlash.uz.dto.RequestDtoForUpdateProfile;
import dasturlash.uz.dto.RequestDtoUpdateProfileByDetails;
import dasturlash.uz.entity.Profile;
import dasturlash.uz.projections.StudentShortInfo;
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
            @RequestBody @Valid RequestDtoForUpdateProfile request) {
        return ResponseEntity.ok().body(profileService.updateProfile(id, request));
    }

    @PutMapping("/update-detail/{id}")
    public ResponseEntity<Profile> updateProfileByProfileDetails(
            @PathVariable Integer id,
            @RequestBody RequestDtoUpdateProfileByDetails request) {
        return ResponseEntity.ok().body(profileService.updateProfileByDetails(id, request));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<StudentShortInfo>> getAllProfiles(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        return ResponseEntity.ok().body(profileService.getAllProfilesByPagination(page, size));
    }
}
