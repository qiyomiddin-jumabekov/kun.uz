package dasturlash.uz.repository;

import dasturlash.uz.entity.Sms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsRepository extends JpaRepository<Sms, Integer> {
}
