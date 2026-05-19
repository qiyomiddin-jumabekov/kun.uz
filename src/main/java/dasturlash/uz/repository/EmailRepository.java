package dasturlash.uz.repository;

import dasturlash.uz.entity.EmailHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailHistory, Integer> {
}
