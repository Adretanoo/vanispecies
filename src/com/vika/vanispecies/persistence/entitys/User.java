package com.vika.vanispecies.persistence.entitys;

/**
 * Клас, що представляє користувача системи.
 * Містить інформацію про ім'я користувача, електронну пошту, хеш пароля та роль.
 */
public class User {

    /**
     * Ім'я користувача.
     */
    private String username;

    /**
     * Електронна пошта користувача.
     */
    private String email;

    /**
     * Хеш пароля користувача.
     */
    private String passwordHash;

    /**
     * Роль користувача в системі (наприклад, "адміністратор", "користувач").
     */
    private String role;

    /**
     * Конструктор класу, який ініціалізує об'єкт користувача з вказаними значеннями.
     *
     * @param username Ім'я користувача.
     * @param email Електронна пошта користувача.
     * @param password Хеш пароля користувача.
     * @param role Роль користувача.
     */
    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.passwordHash = password;
        this.role = role;
    }

    /**
     * Отримує ім'я користувача.
     *
     * @return Ім'я користувача.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Встановлює ім'я користувача.
     *
     * @param username Нове ім'я користувача.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Отримує електронну пошту користувача.
     *
     * @return Електронна пошта користувача.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Встановлює електронну пошту користувача.
     *
     * @param email Нова електронна пошта користувача.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Отримує хеш пароля користувача.
     *
     * @return Хеш пароля користувача.
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Встановлює хеш пароля користувача.
     *
     * @param passwordHash Новий хеш пароля користувача.
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Отримує роль користувача.
     *
     * @return Роль користувача.
     */
    public String getRole() {
        return role;
    }

    /**
     * Встановлює роль користувача.
     *
     * @param role Нова роль користувача.
     */
    public void setRole(String role) {
        this.role = role;
    }
}
