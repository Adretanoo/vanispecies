package com.vika.vanispecies.appui.forms;

import com.vika.vanispecies.domain.validators.AddSpeciesHandlerValidator;
import com.vika.vanispecies.persistence.entitys.Species;
import com.vika.vanispecies.persistence.repository.SpeciesAddRepository;
import java.util.Scanner;

/**
 * Клас для обробки додавання нового виду в базу даних.
 * Проводить перевірку введених даних та додає новий вид до репозиторію.
 */
public class AddSpeciesHandler {

    private final SpeciesAddRepository speciesRepository;

    /**
     * Конструктор класу AddSpeciesHandler.
     *
     * @param speciesRepository Репозиторій для додавання нових видів.
     */
    public AddSpeciesHandler(SpeciesAddRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    /**
     * Виконує процес додавання нового виду. Запитує користувача про різні атрибути виду, перевіряє їх і додає новий вид.
     * Повертається в меню адміністратора, якщо введено "меню".
     */
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("=== Додавання нового виду ===");
        System.out.println("Для повернення в меню адміністратора введіть 'меню' у будь-який момент.");

        // Запит на введення назви виду
        String name;
        do {
            System.out.println("Введіть назву виду:");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("меню")) return;
            name = input;
        } while (!AddSpeciesHandlerValidator.isValidName(name));

        // Запит на вибір типу виду (Рослина чи Тварина)
        int typeChoice;
        do {
            System.out.println("Виберіть тип: (1 - Рослина, 2 - Тварина)");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("меню")) return;
            try {
                typeChoice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                typeChoice = -1;
            }
        } while (!AddSpeciesHandlerValidator.isValidType(typeChoice));

        String type = (typeChoice == 1) ? "Рослина" : "Тварина";

        // Запит на введення року вимирання
        int extinctionYear;
        do {
            System.out.println("Введіть рік вимирання:");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("меню")) return;
            try {
                extinctionYear = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                extinctionYear = -1;
            }
        } while (!AddSpeciesHandlerValidator.isValidExtinctionYear(input));

        // Запит на введення опису виду
        String description;
        do {
            System.out.println("Введіть короткий опис:");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("меню")) return;
            description = input;
        } while (!AddSpeciesHandlerValidator.isValidDescription(description));

        // Запит на вибір континенту
        String continent = "";
        String[] continents = {"Африка", "Євразія", "Північна Америка", "Південна Америка", "Австралія", "Антарктида"};
        do {
            System.out.println("Виберіть материк зародження (виберіть один з наведених):");
            for (int i = 0; i < continents.length; i++) {
                System.out.println((i + 1) + ". " + continents[i]);
            }
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("меню")) return;

            if (input.isEmpty()) {
                System.out.println("Поле 'Материк зародження' не може бути порожнім!");
                continue;
            }

            boolean validContinent = false;
            for (String validContinentOption : continents) {
                if (input.equalsIgnoreCase(validContinentOption)) {
                    continent = input;
                    validContinent = true;
                    break;
                }
            }
            if (!validContinent) {
                System.out.println("Невірний материк. Спробуйте ще раз.");
            }
        } while (continent.isEmpty());

        // Створення нового виду та додавання його в репозиторій
        Species newSpecies = new Species(name, type, extinctionYear, description, continent);
        speciesRepository.addSpecies(newSpecies);

        System.out.println("Вид успішно додано:\n" + newSpecies);
    }
}
