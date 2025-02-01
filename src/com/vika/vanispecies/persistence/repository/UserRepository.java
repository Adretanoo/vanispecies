package com.vika.vanispecies.persistence.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vika.vanispecies.persistence.entitys.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторій для роботи з користувачами, який здійснює зберігання і завантаження даних з файлу.
 * Забезпечує доступ до колекції користувачів, а також операції з ними (збереження, пошук, додавання).
 */
public class UserRepository {

    /**
     * Шлях до файлу, в якому зберігаються дані про користувачів.
     */
    private static final String FILE_PATH = "data/users.json";

    /**
     * Список користувачів, що зберігаються в пам'яті.
     */
    private List<User> users;

    /**
     * Конструктор класу, ініціалізує список користувачів.
     * Завантажує користувачів з файлу при створенні репозиторію.
     */
    public UserRepository() {
        this.users = loadUsers();
    }

    /**
     * Завантажує список користувачів із файлу.
     * Якщо файл не існує або виникає помилка при читанні, повертається порожній список.
     *
     * @return Список користувачів, або порожній список у випадку помилки.
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
     * Зберігає список користувачів у файл.
     * Якщо виникає помилка при збереженні, виводиться повідомлення про помилку.
     */
    public void saveUsers() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            new Gson().toJson(users, writer);
        } catch (IOException e) {
            System.out.println("Помилка при збереженні користувачів.");
        }
    }

    /**
     * Шукає користувача за його електронною поштою.
     *
     * @param email Електронна пошта користувача для пошуку.
     * @return Користувач з відповідною електронною поштою, або null, якщо користувача не знайдено.
     */
    public User findByEmail(String email) {
        return users.stream()
            .filter(user -> user.getEmail().equalsIgnoreCase(email))
            .findFirst()
            .orElse(null);
    }

    /**
     * Додає нового користувача до списку.
     *
     * @param user Користувач, якого потрібно додати до списку.
     */
    public void addUser(User user) {
        users.add(user);
    }
}
