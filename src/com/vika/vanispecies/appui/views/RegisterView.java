package com.vika.vanispecies.appui.views;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vika.vanispecies.persistence.entitys.User;
import org.mindrot.bcrypt.BCrypt;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Клас, що відповідає за процес реєстрації користувача.
 */
public class RegisterView {
    private static final String FILE_PATH = "data/users.json";

    /**
     * Виводить інтерфейс реєстрації та обробляє введення користувача.
     */
    public void display() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("┌──────────────────────────┐");
        System.out.println("│        Реєстрація        │");
        System.out.println("└──────────────────────────┘");

        String username;
        while (true) {
            System.out.print("Введіть ім'я користувача (або 'меню' для виходу): ");
            username = scanner.nextLine().trim();
            if (username.equalsIgnoreCase("меню")) return;
            if (!username.isEmpty()) break;
            System.out.println("Ім'я користувача не може бути порожнім.");
        }

        String email;
        while (true) {
            System.out.print("Введіть email (або 'меню' для виходу): ");
            email = scanner.nextLine().trim();
            if (email.equalsIgnoreCase("меню")) return;
            if (!isValidEmail(email)) {
                System.out.println("Некоректний формат email.");
                continue;
            }
            if (isEmailExists(email)) {
                System.out.println("Цей email вже зареєстровано.");
                continue;
            }
            break;
        }

        String password;
        while (true) {
            System.out.print("Введіть пароль (не менше 6 символів) або 'меню' для виходу: ");
            password = scanner.nextLine();
            if (password.equalsIgnoreCase("меню")) return;
            if (password.length() >= 6) break;
            System.out.println("Пароль повинен бути не менше 6 символів.");
        }

        String role;
        while (true) {
            System.out.print("Введіть роль (user/admin) або 'меню' для виходу: ");
            role = scanner.nextLine().trim().toLowerCase();
            if (role.equalsIgnoreCase("меню")) return;
            if (role.equals("user") || role.equals("admin")) break;
            System.out.println("Роль повинна бути 'user' або 'admin'.");
        }

        String hashedPassword = hashPassword(password);
        User newUser = new User(username, email, hashedPassword, role);
        saveUser(newUser);
        System.out.println("Реєстрація успішна!");

        if (role.equals("admin")) {
            new AdminView().display();
        } else {
            new UserView().display();
        }
    }

    /**
     * Перевіряє, чи існує користувач з заданим email.
     *
     * @param email адреса електронної пошти, яку потрібно перевірити
     * @return true, якщо користувач з таким email вже існує, false в іншому випадку
     */
    private boolean isEmailExists(String email) {
        List<User> users = loadUsers();
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Хешує пароль за допомогою BCrypt.
     *
     * @param password пароль, який потрібно захешувати
     * @return захешований пароль
     */
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Зберігає нового користувача в файл.
     *
     * @param user користувач, якого потрібно зберегти
     */
    private void saveUser(User user) {
        List<User> users = loadUsers();
        users.add(user);
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            new Gson().toJson(users, writer);
        } catch (IOException e) {
            System.out.println("Помилка при збереженні користувача.");
        }
    }

    /**
     * Завантажує список користувачів з файлу.
     *
     * @return список користувачів
     */
    private List<User> loadUsers() {
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

    /**
     * Перевіряє, чи є email валідним.
     *
     * @param email адреса електронної пошти, яку потрібно перевірити
     * @return true, якщо email валідний, false в іншому випадку
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(emailRegex, email);
    }
}
