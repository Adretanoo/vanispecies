package com.vika.vanispecies.persistence.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.vika.vanispecies.persistence.entitys.Species;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторій для додавання видів.
 * Дозволяє завантажувати, додавати та зберігати види у файл.
 */
public class SpeciesAddRepository {

    /**
     * Шлях до файлу, в якому зберігаються види.
     */
    private final String filePath = "data/species.json";

    /**
     * Об'єкт Gson для роботи з JSON та форматуванням.
     */
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Список видів, що зберігається в пам'яті.
     */
    private List<Species> speciesList;

    /**
     * Конструктор класу, ініціалізує список видів, завантажуючи їх з файлу.
     */
    public SpeciesAddRepository() {
        loadSpecies();
    }

    /**
     * Завантажує список видів з файлу, якщо він існує.
     * Якщо файл відсутній або виникає помилка при зчитуванні, створюється новий порожній список.
     */
    private void loadSpecies() {
        File file = new File(filePath);
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                Type listType = new TypeToken<List<Species>>() {}.getType();
                speciesList = gson.fromJson(reader, listType);
                if (speciesList == null) {
                    speciesList = new ArrayList<>();
                }
            } catch (IOException e) {
                speciesList = new ArrayList<>();
            }
        } else {
            speciesList = new ArrayList<>();
        }
    }

    /**
     * Додає новий вид у список та зберігає оновлений список у файл.
     *
     * @param species Вид, який потрібно додати.
     */
    public void addSpecies(Species species) {
        speciesList.add(species);
        saveSpecies();
    }

    /**
     * Зберігає список видів у файл.
     * Якщо виникає помилка при збереженні, виводиться відповідне повідомлення.
     */
    private void saveSpecies() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(speciesList, writer);
        } catch (IOException e) {
            System.out.println("Помилка збереження виду.");
        }
    }
}
