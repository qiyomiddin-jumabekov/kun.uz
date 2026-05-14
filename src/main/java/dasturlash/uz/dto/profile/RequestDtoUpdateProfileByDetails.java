package dasturlash.uz.dto.profile;

public record RequestDtoUpdateProfileByDetails(
        String name,
        String surname,
        String username,
        String password) {
}
