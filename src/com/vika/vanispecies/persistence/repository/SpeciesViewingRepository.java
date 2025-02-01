package com.vika.vanispecies.persistence.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vika.vanispecies.persistence.entitys.Species;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Репозиторій для перегляду інформації про види.
 * Здійснює завантаження всіх видів з файлу.
 */
public class SpeciesViewingRepository {

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
    public SpeciesViewingRepository() {
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
}
