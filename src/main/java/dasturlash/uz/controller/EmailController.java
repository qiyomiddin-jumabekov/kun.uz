package dasturlash.uz.controller;

import dasturlash.uz.dto.email.EmailHistoryDto;
import dasturlash.uz.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/by-date")
    public ResponseEntity<List<EmailHistoryDto>> getEmailHistoryByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(emailService.getHistoryByDate(date));
    }
}
