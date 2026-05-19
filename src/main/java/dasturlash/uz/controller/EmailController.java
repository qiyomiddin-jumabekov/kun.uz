package dasturlash.uz.controller;

import dasturlash.uz.dto.email.EmailHistoryDto;
import dasturlash.uz.entity.EmailHistory;
import dasturlash.uz.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/by-email/{email}")
    public ResponseEntity<List<EmailHistoryDto>> getEmailHistoryByEmail(@PathVariable String email) {
        return ResponseEntity.ok(emailService.getHistoryByEmail(email));
    }
}
