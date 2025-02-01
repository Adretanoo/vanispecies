package com.vika.vanispecies.appui.views;

import com.vika.vanispecies.domain.uow.UnitOfWork;
import com.vika.vanispecies.persistence.entitys.User;
import java.io.Console;
import java.util.Scanner;
import org.mindrot.bcrypt.BCrypt;

/**
 * Клас, що відповідає за відображення інтерфейсу авторизації користувача.
 * У цьому класі користувач вводить свої облікові дані, які перевіряються
 * на відповідність даним у базі.
 */
public class LoginView {
    private final UnitOfWork unitOfWork;

    /**
     * Конструктор класу LoginView, який ініціалізує UnitOfWork для доступу до репозиторіїв.
     */
    public LoginView() {
        this.unitOfWork = new UnitOfWork();
    }

    /**
     * Виводить інтерфейс авторизації, де користувач вводить email і пароль.
     * Перевіряє правильність введених даних і надає доступ до програми для
     * користувачів або адміністраторів залежно від ролі.
     */
    public void display() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("┌──────────────────────────┐");
        System.out.println("│       Авторизація        │");
        System.out.println("└──────────────────────────┘");
        while (true) {
            System.out.print("Введіть email (або 'меню' для виходу): ");
            String email = scanner.nextLine().trim();
            if (email.equalsIgnoreCase("меню")) return;

            System.out.print("Введіть пароль: ");
            String password = readPassword(scanner);

            User user = unitOfWork.getUserRepository().findByEmail(email);
            if (user != null && BCrypt.checkpw(password, user.getPasswordHash())) {
                System.out.println("Вхід успішний! Ласкаво просимо, " + user.getUsername());
                if ("admin".equalsIgnoreCase(user.getRole())) {
                    new AdminView().display();
                } else {
                    new UserView().display();
                }
                return;
            } else {
                System.out.println("Неправильний email або пароль. Спробуйте ще раз.");
            }
        }
    }

    /**
     * Зчитує пароль, використовуючи консоль для безпечного введення або
     * стандартний сканер у випадку відсутності доступу до консолі.
     *
     * @param scanner об'єкт для зчитування вводу
     * @return зчитаний пароль у вигляді рядка
     */
    private String readPassword(Scanner scanner) {
        Console console = System.console();
        if (console != null) {
            char[] passwordArray = console.readPassword();
            return new String(passwordArray);
        } else {
            return scanner.nextLine();
        }
    }
}
