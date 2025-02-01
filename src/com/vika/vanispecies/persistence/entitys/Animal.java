package com.vika.vanispecies.persistence.entitys;

/**
 * Клас, що представляє тварину. Наслідується від класу {@link Species}.
 * Додатково містить інформацію про тип харчування тварини.
 */
public class Animal extends Species {

    /**
     * Тип харчування тварини (наприклад, травоїдне, хижак).
     */
    private String diet;

    /**
     * Конструктор класу тварини.
     * Ініціалізує об'єкт тварини з вказаними параметрами.
     *
     * @param name Ім'я тварини.
     * @param extinctionPeriod Період вимирання (наприклад, "2000-2010").
     * @param extinctionYear Рік вимирання тварини.
     * @param description Опис тварини.
     * @param continent Материк, на якому існувала тварина.
     * @param diet Тип харчування тварини.
     */
    public Animal(String name, String extinctionPeriod, int extinctionYear, String description, String continent, String diet) {
        super(name, "Тварина", extinctionYear, description, continent);
        this.diet = diet;
    }

    /**
     * Отримує тип харчування тварини.
     *
     * @return Тип харчування тварини.
     */
    public String getDiet() {
        return diet;
    }

    /**
     * Повертає строкове представлення тварини, яке включає
     * всі дані з класу {@link Species} та додаткову інформацію
     * про тип харчування тварини.
     *
     * @return Строкове представлення тварини.
     */
    @Override
    public String toString() {
        return super.toString() + "\nТип харчування: " + diet;
    }
}
