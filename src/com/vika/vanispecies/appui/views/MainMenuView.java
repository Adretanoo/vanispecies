package com.vika.vanispecies.appui.views;

import com.vika.vanispecies.domain.validators.InputMainMenuValidator;

/**
 * Клас, що відповідає за відображення головного меню програми
 * та обробку введених користувачем варіантів.
 */
public class MainMenuView {

    /**
     * Виводить головне меню і обробляє вибір користувача.
     * Залежно від вибору, запускає відповідні функції, такі як про програму,
     * авторизацію, реєстрацію або вихід з програми.
     */
    public void display() {
        while (true) {
            System.out.println("┌──────────────────────────┐");
            System.out.println("│      Головне меню        │");
            System.out.println("├──────────────────────────┤");
            System.out.println("│ 1. Про програму          │");
            System.out.println("│ 2. Авторизація           │");
            System.out.println("│ 3. Реєстрація            │");
            System.out.println("│ 4. Вихід                 │");
            System.out.println("└──────────────────────────┘");

            int choice = InputMainMenuValidator.getValidatedOption();

            switch (choice) {
                case 1:
                    new AboutView().display();
                    break;
                case 2:
                    new LoginView().display();
                    break;
                case 3:
                    new RegisterView().display();
                    break;
                case 4:
                    System.out.println("Вихід з програми...");
                    return;
                default:
                    System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        }
    }
}
