package dasturlash.uz.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseDtoForLogin(
        String name,
        String surname,
        String username,
//        List<ProfileRoles> roles,
        Integer photoId) {
}
