package dasturlash.uz.service;

import dasturlash.uz.dto.email.EmailHistoryDto;
import dasturlash.uz.entity.EmailHistory;
import dasturlash.uz.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    @Autowired
    private EmailRepository emailRepository;

    public List<EmailHistoryDto> getHistoryByEmail(String email) {
        return emailRepository.findAllByEmail(email);
    }
}
