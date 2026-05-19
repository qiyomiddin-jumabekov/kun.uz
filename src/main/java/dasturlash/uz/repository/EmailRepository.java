package dasturlash.uz.repository;

import dasturlash.uz.dto.email.EmailHistoryDto;
import dasturlash.uz.entity.EmailHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EmailRepository extends JpaRepository<EmailHistory, Integer> {


    @Query("select new " +
            " dasturlash.uz.dto.email.EmailHistoryDto(e.email,e.message,e.createdDate) " +
            " from EmailHistory e " +
            " where e.email= :email")
    List<EmailHistoryDto> findAllByEmail(@Param("email") String email);


    @Query("select new" +
            " dasturlash.uz.dto.email.EmailHistoryDto(e.email,e.message,e.createdDate)" +
            " from EmailHistory e" +
            " where e.createdDate between :start and :end ")
    List<EmailHistoryDto> findAllByCreatedDateBetween(LocalDateTime start, LocalDateTime end);



    @Query("select new " +
            " dasturlash.uz.dto.email.EmailHistoryDto(e.email,e.message,e.createdDate)" +
            " from EmailHistory e")
    Page<EmailHistoryDto> getHistoryByPagination(Pageable pageable);

}

