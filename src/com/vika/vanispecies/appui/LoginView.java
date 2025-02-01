package com.vika.vanispecies.appui;

import com.vika.vanispecies.domain.uow.UnitOfWork;
import com.vika.vanispecies.persistence.entitys.User;
import java.io.Console;
import java.util.Scanner;
import org.mindrot.bcrypt.BCrypt;

public class LoginView {
    private final UnitOfWork unitOfWork;

    public LoginView() {
        this.unitOfWork = new UnitOfWork();
    }

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
