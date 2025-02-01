package com.vika.vanispecies.domain.validators;

import java.util.Scanner;

public class InputAdminMenuValidator {
    private static Scanner scanner = new Scanner(System.in);

    public static int getValidatedOption() {
        while (true) {
            System.out.print("Виберіть опцію: ");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= 1 && choice <= 5) {
                    return choice;
                } else {
                    System.out.println("Помилка! Введіть число від 1 до 5.");
                }
            } else {
                System.out.println("Помилка! Введіть число.");
                scanner.nextLine();
            }
        }
    }

}
