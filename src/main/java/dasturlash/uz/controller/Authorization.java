package dasturlash.uz.controller;

import dasturlash.uz.dto.profile.RequestDtoForProfile;
import dasturlash.uz.service.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class Authorization {
    private AuthorizationService authorizationService;

    public Authorization(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RequestDtoForProfile request) {
        return authorizationService.registerUser(request);
    }
}
