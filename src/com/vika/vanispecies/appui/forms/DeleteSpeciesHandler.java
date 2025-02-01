package com.vika.vanispecies.appui.forms;

import com.vika.vanispecies.persistence.repository.SpeciesDeleteRepository;
import com.vika.vanispecies.persistence.entitys.Species;
import java.util.Scanner;

/**
 * Клас для обробки видалення виду тварини або рослини.
 * Використовується для пошуку та видалення виду з бази даних.
 */
public class DeleteSpeciesHandler {

    private final SpeciesDeleteRepository speciesRepository;

    /**
     * Конструктор класу DeleteSpeciesHandler.
     *
     * @param speciesRepository Репозиторій для видалення видів.
     */
    public DeleteSpeciesHandler(SpeciesDeleteRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    /**
     * Виконує видалення виду з бази даних. Користувач може ввести назву виду для видалення.
     * Якщо введено неправильну назву виду або команду "меню", операція буде скасована.
     */
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("=== Видалення виду ===");
        System.out.println("Для повернення в меню адміністратора введіть 'меню' у будь-який момент.");

        String name;
        do {
            System.out.println("Введіть назву виду для видалення:");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("меню")) return; // Повернення в меню
            name = input;
        } while (name.isEmpty()); // Перевірка, що ім'я не порожнє

        Species speciesToDelete = null;
        // Пошук виду за назвою у списку всіх видів
        for (Species species : speciesRepository.getAllSpecies()) {
            if (species.getName().equalsIgnoreCase(name)) {
                speciesToDelete = species;
                break;
            }
        }

        // Видалення виду, якщо знайдено
        if (speciesToDelete != null) {
            speciesRepository.deleteSpecies(speciesToDelete);
            System.out.println("✅ Вид '" + name + "' успішно видалено.");
        } else {
            System.out.println("❌ Вид з назвою '" + name + "' не знайдено.");
        }
    }
}
