package com.vika.vanispecies.domain.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mindrot.bcrypt.BCrypt;

/**
 * Клас для валідації даних при реєстрації користувача.
 * Перевіряє коректність введених даних, таких як ім'я, email, пароль та роль.
 */
public class RegisterValidator {

    /**
     * Регулярний вираз для перевірки коректності email адреси.
     */
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    /**
     * Перевіряє правильність введених даних користувача.
     *
     * @param username Ім'я користувача.
     * @param email Адреса електронної пошти користувача.
     * @param password Пароль користувача.
     * @param role Роль користувача (може бути "user" або "admin").
     * @return true, якщо всі дані валідні, інакше false.
     */
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

    /**
     * Перевіряє, чи є email коректним за допомогою регулярного виразу.
     *
     * @param email Адреса електронної пошти.
     * @return true, якщо email коректний, інакше false.
     */
    private static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Хешує пароль користувача за допомогою BCrypt.
     *
     * @param password Пароль користувача.
     * @return хешований пароль.
     */
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Перевіряє, чи співпадає введений пароль з збереженим хешем.
     *
     * @param password Введений пароль користувача.
     * @param storedHash Збережений хеш пароля.
     * @return true, якщо паролі співпадають, інакше false.
     */
    public static boolean checkPassword(String password, String storedHash) {
        return BCrypt.checkpw(password, storedHash);
    }
}
