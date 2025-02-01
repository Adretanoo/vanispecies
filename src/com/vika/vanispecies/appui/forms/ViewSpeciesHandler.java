package com.vika.vanispecies.appui.forms;

import com.vika.vanispecies.persistence.entitys.Species;
import com.vika.vanispecies.persistence.repository.SpeciesViewingRepository;
import java.util.List;

/**
 * Клас, який відповідає за обробку запиту на перегляд списку видів.
 * Використовує репозиторій для отримання даних і виводить їх у консоль.
 */
public class ViewSpeciesHandler {

    private final SpeciesViewingRepository speciesRepository;

    /**
     * Конструктор класу ViewSpeciesHandler.
     *
     * @param speciesRepository Репозиторій для доступу до видів.
     */
    public ViewSpeciesHandler(SpeciesViewingRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    /**
     * Виконує отримання і виведення списку видів.
     * Виводить інформацію про кожен вид у консоль.
     * Якщо список порожній або не знайдено видів, виводиться повідомлення про помилку.
     */
    public void execute() {
        List<Species> speciesList = speciesRepository.getAllSpecies();

        if (speciesList != null && !speciesList.isEmpty()) {
            System.out.println("\n=== Список видів ===");
            for (Species species : speciesList) {
                System.out.println("Назва: " + species.getName());
                System.out.println("Тип: " + species.getType());
                System.out.println("Рік вимирання: " + species.getExtinctionYear());
                System.out.println("Опис: " + species.getDescription());
                System.out.println("Континент: " + species.getContinent());
                System.out.println("-------------------------");
            }
        } else {
            System.out.println("❌ Немає доступних видів для перегляду.");
        }
    }
}
