package dasturlash.uz.dto.email;

import java.time.LocalDateTime;

public record EmailHistoryDto(
        String email,
        String message,
        LocalDateTime createdDate) {
}
