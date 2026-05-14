package dasturlash.uz.dto.profile;

public record RequestForUpdatePassword(
        String oldPassword,
        String newPassword) {
}
