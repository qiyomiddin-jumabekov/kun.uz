package dasturlash.uz.service;

import dasturlash.uz.dto.email.EmailHistoryDto;
import dasturlash.uz.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public Page<EmailHistoryDto> getHistoryByPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return emailRepository.getHistoryByPagination(pageable);
    }
}
