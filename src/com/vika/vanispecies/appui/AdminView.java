package com.vika.vanispecies.appui;

import com.vika.vanispecies.domain.validators.InputAdminMenuValidator;
import java.util.Scanner;

public class AdminView {
    public void display() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n┌──────────────────────────┐");
            System.out.println("│   Меню адміністратора    │");
            System.out.println("├──────────────────────────┤");
            System.out.println("│ 1. Додати новий вид      │");
            System.out.println("│ 2. Видалити вид          │");
            System.out.println("│ 3. Перегляд списку       │");
            System.out.println("│ 4. Пошук виду            │");
            System.out.println("│ 5. Вихід в головне меню  │");
            System.out.println("└──────────────────────────┘");

            int choice = InputAdminMenuValidator.getValidatedOption();

            switch (choice) {
                case 1:
                    addSpecies();
                    break;
                case 2:
                    deleteSpecies();
                    break;
                case 3:
                    editSpecies();
                    break;
                case 4:
                    viewExtinctSpecies();
                    break;
                case 5:
                    System.out.println("Вихід з меню адміністратора....");
                    new MainMenuView().display();
                    return;
                default:
                    System.out.println("Некоректний вибір. Спробуйте ще раз.");
            }
        }
    }

    private void addSpecies() {
        System.out.println("Функція додавання нового виду ще не реалізована.");
    }

    private void deleteSpecies() {
        System.out.println("Функція видалення виду ще не реалізована.");
    }

    private void editSpecies() {
        System.out.println("Функція редагування виду ще не реалізована.");
    }

    private void viewExtinctSpecies() {
        System.out.println("Функція перегляду вимерлих видів ще не реалізована.");
    }

    private void searchSpecies() {
        System.out.println("Функція пошуку виду ще не реалізована.");
    }
}