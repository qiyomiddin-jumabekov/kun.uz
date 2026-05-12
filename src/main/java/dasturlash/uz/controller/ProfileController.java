package dasturlash.uz.controller;

import dasturlash.uz.entity.Profile;
import dasturlash.uz.service.ProfileService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
