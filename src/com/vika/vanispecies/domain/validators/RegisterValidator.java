package com.vika.vanispecies.domain.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mindrot.bcrypt.BCrypt;

public class RegisterValidator {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public static boolean validateUser(String username, String email, String password, String role) {
        if (username == null || username.isEmpty()) {
            System.out.println("Ім'я користувача не може бути порожнім.");
            return false;
        }

        if (email == null || !isValidEmail(email)) {
            System.out.println("Некоректний email.");
            return false;
        }

        if (password == null || password.length() < 6) {
            System.out.println("Пароль повинен бути не менше 6 символів.");
            return false;
        }

        if (!role.equals("user") && !role.equals("admin")) {
            System.out.println("Роль має бути 'user' або 'admin'.");
            return false;
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String storedHash) {
        return BCrypt.checkpw(password, storedHash);
    }
}
