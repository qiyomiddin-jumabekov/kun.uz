package dasturlash.uz.dto;

public record RequestForUpdatePassword(
        String oldPassword,
        String newPassword) {
}
