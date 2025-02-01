package com.vika.vanispecies.appui.forms;

import com.vika.vanispecies.persistence.repository.SpeciesViewingRepository;
import com.vika.vanispecies.persistence.entitys.Species;
import java.util.List;

/**
 * Клас, який обробляє пошук видів за певними критеріями (назва, тип).
 * Взаємодіє з репозиторієм для отримання даних про види та виводить знайдені результати.
 */
public class SearchSpeciesHandler {

    private final SpeciesViewingRepository speciesRepository;

    /**
     * Конструктор класу SearchSpeciesHandler.
     *
     * @param speciesRepository Репозиторій для доступу до видів.
     */
    public SearchSpeciesHandler(SpeciesViewingRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    /**
     * Виконує пошук видів за заданими критеріями (назва чи тип).
     * Виводить знайдені види або повідомлення про відсутність результатів.
     */
    public void execute() {
        List<Species> speciesList = speciesRepository.getAllSpecies();

        if (speciesList == null || speciesList.isEmpty()) {
            System.out.println("❌ Немає доступних видів для пошуку.");
            return;
        }

        System.out.println("=== Пошук виду ===");
        System.out.println("1. Пошук за назвою");
        System.out.println("2. Пошук за типом");
        System.out.print("Виберіть критерій пошуку: ");
        int choice = new java.util.Scanner(System.in).nextInt();
        java.util.Scanner scanner = new java.util.Scanner(System.in); // Для зчитування рядків

        switch (choice) {
            case 1:
                System.out.print("Введіть назву виду для пошуку: ");
                String name = scanner.nextLine();
                searchByName(speciesList, name);
                break;
            case 2:
                System.out.print("Введіть тип виду для пошуку: ");
                String type = scanner.nextLine();
                searchByType(speciesList, type);
                break;
            default:
                System.out.println("Некоректний вибір.");
        }
    }

    /**
     * Шукає види за назвою та виводить інформацію про знайдені види.
     * Якщо вид не знайдений, виводить повідомлення про це.
     *
     * @param speciesList Список всіх видів для пошуку.
     * @param name Назва виду для пошуку.
     */
    private void searchByName(List<Species> speciesList, String name) {
        boolean found = false;
        for (Species species : speciesList) {
            if (species.getName().equalsIgnoreCase(name)) {
                printSpeciesInfo(species);
                found = true;
            }
        }
        if (!found) {
            System.out.println("❌ Вид з назвою '" + name + "' не знайдено.");
        }
    }

    /**
     * Шукає види за типом та виводить інформацію про знайдені види.
     * Якщо вид не знайдений, виводить повідомлення про це.
     *
     * @param speciesList Список всіх видів для пошуку.
     * @param type Тип виду для пошуку.
     */
    private void searchByType(List<Species> speciesList, String type) {
        boolean found = false;
        for (Species species : speciesList) {
            if (species.getType().equalsIgnoreCase(type)) {
                printSpeciesInfo(species);
                found = true;
            }
        }
        if (!found) {
            System.out.println("❌ Вид з типом '" + type + "' не знайдено.");
        }
    }

    /**
     * Виводить інформацію про знайдений вид.
     *
     * @param species Вид, інформацію про який потрібно вивести.
     */
    private void printSpeciesInfo(Species species) {
        System.out.println("\n=== Знайдений вид ===");
        System.out.println("Назва: " + species.getName());
        System.out.println("Тип: " + species.getType());
        System.out.println("Рік вимирання: " + species.getExtinctionYear());
        System.out.println("Опис: " + species.getDescription());
        System.out.println("Континент: " + species.getContinent());
        System.out.println("-------------------------");
    }
}
