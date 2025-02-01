package com.vika.vanispecies.persistence.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vika.vanispecies.persistence.entitys.Species;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Репозиторій для видалення видів.
 * Забезпечує можливість отримання всіх видів, а також видалення конкретного виду з файлу.
 */
public class SpeciesDeleteRepository {

    /**
     * Шлях до файлу, в якому зберігається інформація про види.
     */
    private static final String FILE_PATH = "data/species.json";

    /**
     * Об'єкт для роботи з JSON через бібліотеку Gson.
     */
    private Gson gson;

    /**
     * Конструктор класу, ініціалізує об'єкт Gson.
     */
    public SpeciesDeleteRepository() {
        gson = new Gson();
    }

    /**
     * Завантажує список всіх видів з файлу.
     * У випадку помилки при зчитуванні файлу, виводиться повідомлення про помилку, і метод повертає null.
     *
     * @return Список видів, або null у разі помилки при зчитуванні.
     */
    public List<Species> getAllSpecies() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type speciesListType = new TypeToken<List<Species>>() {}.getType();
            return gson.fromJson(reader, speciesListType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Видаляє вказаний вид із списку видів та зберігає оновлений список у файл.
     *
     * @param species Вид, який необхідно видалити з файлу.
     */
    public void deleteSpecies(Species species) {
        List<Species> speciesList = getAllSpecies();
        if (speciesList != null) {
            // Видалення виду з колекції за назвою
            speciesList.removeIf(s -> s.getName().equalsIgnoreCase(species.getName()));
            // Запис оновленого списку в файл
            writeToFile(speciesList);
        }
    }

    /**
     * Записує оновлений список видів у файл.
     *
     * @param speciesList Список видів, який потрібно записати в файл.
     */
    private void writeToFile(List<Species> speciesList) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(speciesList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
