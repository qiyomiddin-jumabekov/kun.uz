package dasturlash.uz.service;

import dasturlash.uz.dto.email.EmailHistoryDto;
import dasturlash.uz.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class EmailService {
    @Autowired
    private EmailRepository emailRepository;

    public List<EmailHistoryDto> getHistoryByEmail(String email) {
        return emailRepository.findAllByEmail(email);
    }

    public List<EmailHistoryDto> getHistoryByDate(LocalDate date) {
        LocalDateTime startDate = date.atStartOfDay();
        LocalDateTime endDate = date.atTime(23, 59, 59);
        return emailRepository.findAllByCreatedDateBetween(startDate, endDate);
    }
}
