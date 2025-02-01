package com.vika.vanispecies.persistence.entitys;

/**
 * Клас, що представляє вид тварини або рослини.
 * Містить інформацію про ім'я виду, тип (тварина чи рослина), рік вимирання, опис та материк, де існував вид.
 */
public class Species {

    /**
     * Ім'я виду.
     */
    private String name;

    /**
     * Тип виду: "Рослина" або "Тварина".
     */
    private String type;

    /**
     * Рік вимирання виду. Якщо вид не вимер, значення буде 0.
     */
    private int extinctionYear;

    /**
     * Короткий опис виду.
     */
    private String description;

    /**
     * Материк, де існував вид.
     */
    private String continent;

    /**
     * Конструктор класу, який ініціалізує об'єкт виду з вказаними значеннями.
     *
     * @param name Ім'я виду.
     * @param type Тип виду ("Рослина" або "Тварина").
     * @param extinctionYear Рік вимирання виду (0, якщо вид не вимер).
     * @param description Опис виду.
     * @param continent Материк, де існував вид.
     */
    public Species(String name, String type, int extinctionYear, String description, String continent) {
        this.name = name;
        this.type = type;
        this.extinctionYear = extinctionYear;
        this.description = description;
        this.continent = continent;
    }

    /**
     * Повертає строкове представлення об'єкта виду.
     *
     * @return Строкове представлення виду у вигляді:
     *         "Вид: ім'я (тип)\nРік вимирання: рік\nОпис: опис\nМатерик: материк"
     */
    @Override
    public String toString() {
        return String.format("Вид: %s (%s)\nРік вимирання: %d\nОпис: %s\nМатерик: %s",
            name, type, extinctionYear, description, continent);
    }

    /**
     * Отримує ім'я виду.
     *
     * @return Ім'я виду.
     */
    public String getName() {
        return name;
    }

    /**
     * Отримує тип виду.
     *
     * @return Тип виду ("Рослина" або "Тварина").
     */
    public String getType() {
        return type;
    }

    /**
     * Отримує рік вимирання виду.
     *
     * @return Рік вимирання виду. Якщо вид не вимер, повертається 0.
     */
    public int getExtinctionYear() {
        return extinctionYear;
    }

    /**
     * Отримує опис виду.
     *
     * @return Опис виду.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Отримує материк, де існував вид.
     *
     * @return Материк, де існував вид.
     */
    public String getContinent() {
        return continent;
    }
}
