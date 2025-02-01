package com.vika.vanispecies.domain.validators;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vika.vanispecies.persistence.entitys.User;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.mindrot.bcrypt.BCrypt;

/**
 * Клас для валідації і аутентифікації користувачів.
 * Перевіряє правильність email та пароля, а також виконує аутентифікацію користувача.
 */
public class LoginValidator {

    /**
     * Шлях до файлу з даними користувачів.
     */
    private static final String FILE_PATH = "data/users.json";

    /**
     * Перевіряє, чи є email коректним за допомогою регулярного виразу.
     *
     * @param email Адреса електронної пошти.
     * @return true, якщо email коректний, інакше false.
     */
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(emailRegex, email);
    }

    /**
     * Перевіряє, чи є пароль валідним.
     * Пароль вважається валідним, якщо він не є порожнім.
     *
     * @param password Пароль користувача.
     * @return true, якщо пароль валідний, інакше false.
     */
    public static boolean isValidPassword(String password) {
        return password != null && !password.isEmpty();
    }

    /**
     * Аутентифікує користувача за email та паролем.
     * Перевіряє, чи існує користувач з відповідним email і чи співпадає введений пароль з хешем.
     *
     * @param email Адреса електронної пошти користувача.
     * @param password Пароль користувача.
     * @return користувача, якщо аутентифікація пройшла успішно, інакше null.
     */
    public static User authenticateUser(String email, String password) {
        List<User> users = loadUsers();
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && BCrypt.checkpw(password, user.getPasswordHash())) {
                return user;
            }
        }
        return null;
    }

    /**
     * Завантажує список користувачів з файлу.
     *
     * @return список користувачів.
     */
    private static List<User> loadUsers() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();

        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<User>>() {}.getType();
            List<User> users = new Gson().fromJson(reader, listType);
            return users != null ? users : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
