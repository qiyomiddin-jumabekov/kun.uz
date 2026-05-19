package dasturlash.uz.repository;

import dasturlash.uz.entity.EmailVerification;
import dasturlash.uz.enums.CodeStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface EmailVerificationRepository extends CrudRepository<EmailVerification, Integer> {


    @Query("select case when count(e) > 0 then true else false end" +
            " from EmailVerification e" +
            " where e.email = :email" +
            " and e.code = :code" +
            " and e.expiredTime > :confirmTime" +
            " and e.codeStatus = 'SEND'")
    boolean confirmEmailVerification(
            @Param("email") String email,
            @Param("code") String code,
            @Param("confirmTime") LocalDateTime date
    );


    @Modifying
    @Transactional
    @Query("update EmailVerification e set e.codeStatus = :status" +
            " where e.email =:email" +
            " and e.codeStatus = 'SEND'")
    void updateCodeStatus(@Param("email") String email, @Param("status") CodeStatus status);
}
