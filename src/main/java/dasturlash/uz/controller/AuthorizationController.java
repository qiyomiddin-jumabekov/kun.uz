package dasturlash.uz.controller;

import dasturlash.uz.dto.auth.RequestForLogin;
import dasturlash.uz.dto.profile.RequestDtoForProfile;
import dasturlash.uz.dto.verification.ConfirmDto;
import dasturlash.uz.service.AuthorizationService;
import dasturlash.uz.service.EmailVerificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private EmailVerificationService emailVerificationService;


    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RequestDtoForProfile request) {
        return ResponseEntity.ok(authorizationService.registerUser(request));
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmUser(@RequestBody @Valid ConfirmDto request) {
        return ResponseEntity.ok(emailVerificationService.confirmCode(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid RequestForLogin request) {
        return ResponseEntity.ok(authorizationService.userLogin(request));
    }


}
