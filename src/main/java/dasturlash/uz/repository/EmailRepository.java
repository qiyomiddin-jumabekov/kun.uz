package dasturlash.uz.repository;

import dasturlash.uz.dto.email.EmailHistoryDto;
import dasturlash.uz.entity.EmailHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmailRepository extends JpaRepository<EmailHistory, Integer> {


    @Query("select new " +
            " dasturlash.uz.dto.email.EmailHistoryDto(e.email,e.message,e.createdDate) " +
            " from EmailHistory e " +
            " where e.email= :email")
    List<EmailHistoryDto> findAllByEmail(@Param("email") String email);
}
