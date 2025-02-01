package com.vika.vanispecies.appui.views;

import com.vika.vanispecies.appui.forms.AddSpeciesHandler;
import com.vika.vanispecies.appui.forms.DeleteSpeciesHandler;
import com.vika.vanispecies.appui.forms.SearchSpeciesHandler;
import com.vika.vanispecies.appui.forms.ViewSpeciesHandler;
import com.vika.vanispecies.domain.validators.InputAdminMenuValidator;
import com.vika.vanispecies.persistence.repository.SpeciesDeleteRepository;
import com.vika.vanispecies.persistence.repository.SpeciesViewingRepository;
import com.vika.vanispecies.persistence.repository.SpeciesAddRepository;

/**
 * Клас, що відповідає за відображення та обробку меню адміністратора.
 * Адміністратор може додавати, видаляти, переглядати та шукати види.
 */
public class AdminView {

    /**
     * Виводить меню адміністратора та обробляє вибір користувача.
     * Виконується в циклі, поки не вибрано опцію для виходу.
     */
    public void display() {

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
                    searchSpecies();
                    break;
                case 5:
                    System.out.println("Вихід з меню адміністратора....");
                    new MainMenuView().display();
                    return;
            }
        }
    }

    /**
     * Додає новий вид до бази даних через обробник.
     */
    private void addSpecies() {
        SpeciesAddRepository speciesRepository = new SpeciesAddRepository();
        AddSpeciesHandler handler = new AddSpeciesHandler(speciesRepository);
        handler.execute();
    }

    /**
     * Видаляє вид з бази даних через обробник.
     */
    private void deleteSpecies() {
        SpeciesDeleteRepository speciesRepository = new SpeciesDeleteRepository();
        DeleteSpeciesHandler handler = new DeleteSpeciesHandler(speciesRepository);
        handler.execute();
    }

    /**
     * Переглядає список видів у базі даних через обробник.
     */
    private void editSpecies() {
        SpeciesViewingRepository speciesRepository = new SpeciesViewingRepository();
        ViewSpeciesHandler handler = new ViewSpeciesHandler(speciesRepository);
        handler.execute();
    }

    /**
     * Шукає вид за певними критеріями через обробник.
     */
    private void searchSpecies() {
        SpeciesViewingRepository speciesRepository = new SpeciesViewingRepository();
        SearchSpeciesHandler handler = new SearchSpeciesHandler(speciesRepository);
        handler.execute();
    }
}
