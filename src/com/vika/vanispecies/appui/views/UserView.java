package com.vika.vanispecies.appui.views;

import com.vika.vanispecies.appui.forms.SearchSpeciesHandler;
import com.vika.vanispecies.appui.forms.ViewSpeciesHandler;
import com.vika.vanispecies.domain.validators.InputUserMenuValidator;
import com.vika.vanispecies.persistence.repository.SpeciesViewingRepository;

/**
 * Клас, що представляє меню користувача.
 * Дозволяє переглядати список видів, здійснювати пошук або виходити в головне меню.
 */
public class UserView {

    /**
     * Відображає меню користувача та обробляє введені команди.
     */
    public void display() {

        while (true) {
            System.out.println("\n┌──────────────────────────┐");
            System.out.println("│    Меню користувача      │");
            System.out.println("├──────────────────────────┤");
            System.out.println("│ 1. Перегляд списку       │");
            System.out.println("│ 2. Пошук виду            │");
            System.out.println("│ 3. Вихід в головне меню  │");
            System.out.println("└──────────────────────────┘");

            int choice = InputUserMenuValidator.getValidatedOption();

            switch (choice) {
                case 1:
                    editSpecies();
                    break;
                case 2:
                    searchSpecies();
                    break;
                case 3:
                    System.out.println("Вихід з меню користувача....");
                    new MainMenuView().display();
                    return;
            }
        }
    }

    /**
     * Виконує операцію перегляду списку видів.
     */
    private void editSpecies() {
        SpeciesViewingRepository speciesRepository = new SpeciesViewingRepository();
        ViewSpeciesHandler handler = new ViewSpeciesHandler(speciesRepository);
        handler.execute();
    }

    /**
     * Виконує пошук виду за заданими критеріями.
     */
    private void searchSpecies() {
        SpeciesViewingRepository speciesRepository = new SpeciesViewingRepository();
        SearchSpeciesHandler handler = new SearchSpeciesHandler(speciesRepository);
        handler.execute();
    }

}
