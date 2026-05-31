package dasturlash.uz.dto.sms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDtoForSms {
    private Integer clientId;
    private String phoneNumber;
    private String message;
}
