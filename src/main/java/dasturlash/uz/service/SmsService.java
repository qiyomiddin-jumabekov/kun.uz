package dasturlash.uz.service;

import dasturlash.uz.dto.sms.RequestDtoForSms;
import dasturlash.uz.dto.sms.RequestForGetClientId;
import dasturlash.uz.entity.Sms;
import dasturlash.uz.repository.SmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class SmsService {
    @Value("${sms.provider.url}")
    private String provUrl;

    @Value("${auth.provider.url}")
    private String authUrl;

    @Value("${eskiz.client.login}")
    private String clientLogin;

    @Value("${eskiz.client.password}")
    private String clientPassword;

    @Autowired
    private SmsRepository smsRepository;

    @Autowired
    private RestTemplate restTemplate;

    public boolean sendSms(String phoneNumber) {
        String code = String.format("%06d", new Random().nextInt(999999));
        Integer clientId = getClientId();
        RequestDtoForSms dto = new RequestDtoForSms();
        dto.setClientId(clientId);
        dto.setMessage(code);
        dto.setPhoneNumber(phoneNumber);
        try {
            ResponseEntity<String> booleanResponseEntity = restTemplate.postForEntity(provUrl + "/send", dto, String.class);
            System.out.println("URL response : " + booleanResponseEntity.getBody());
            Sms sms = new Sms();
            sms.setPhone(phoneNumber);
            sms.setMessage(code);
            sms.setSmsProviderId(clientId);
            smsRepository.save(sms);
            return true;
        } catch (RuntimeException e) {
            System.out.println("Sms provider ulanishda Xatolik: " + e.getMessage());
            throw new RuntimeException("Sms provider ulanishda Xatolik");
        }
    }

    public Integer getClientId() {
        RequestForGetClientId dto = new RequestForGetClientId(clientLogin, clientPassword);
        ResponseEntity<Integer> response = restTemplate.postForEntity(authUrl + "/client-login", dto, Integer.class);
        return response.getBody();
    }
}
