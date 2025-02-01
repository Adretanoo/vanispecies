package com.vika.vanispecies.persistence.entitys;

/**
 * Клас, що представляє рослину. Наслідується від класу {@link Species}.
 * Додатково містить інформацію про місце існування рослини.
 */
public class Plant extends Species {

    /**
     * Місце існування рослини (наприклад, ліс, пустеля).
     */
    private String habitat;

    /**
     * Конструктор класу рослини.
     * Ініціалізує об'єкт рослини з вказаними параметрами.
     *
     * @param name Ім'я рослини.
     * @param extinctionPeriod Період вимирання (наприклад, "2000-2010").
     * @param extinctionYear Рік вимирання рослини.
     * @param description Опис рослини.
     * @param continent Материк, на якому існувала рослина.
     * @param habitat Місце існування рослини.
     */
    public Plant(String name, String extinctionPeriod, int extinctionYear, String description, String continent, String habitat) {
        super(name, "Рослина", extinctionYear, description, continent);
        this.habitat = habitat;
    }

    /**
     * Отримує місце існування рослини.
     *
     * @return Місце існування рослини.
     */
    public String getHabitat() {
        return habitat;
    }

    /**
     * Повертає строкове представлення рослини, яке включає
     * всі дані з класу {@link Species} та додаткову інформацію
     * про місце існування рослини.
     *
     * @return Строкове представлення рослини.
     */
    @Override
    public String toString() {
        return super.toString() + "\nМісце існування: " + habitat;
    }
}
