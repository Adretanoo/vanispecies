package com.vika.vanispecies.domain.validators;

import java.util.Scanner;

/**
 * Клас для валідації введення користувача у меню.
 */
public class InputUserMenuValidator {

    /**
     * Сканер для зчитування введених даних.
     */
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Метод отримує та перевіряє введене користувачем число.
     * Число має бути у діапазоні від 1 до 3.
     * Якщо введене значення некоректне, користувач отримує повідомлення про помилку
     * і йому пропонується ввести значення ще раз.
     *
     * @return валідний вибір користувача (число від 1 до 3).
     */
    public static int getValidatedOption() {
        while (true) {
            System.out.print("Виберіть опцію: ");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= 1 && choice <= 3) {
                    return choice;
                } else {
                    System.out.println("Помилка! Введіть число від 1 до 3.");
                }
            } else {
                System.out.println("Помилка! Введіть число.");
                scanner.nextLine();
            }
        }
    }
}