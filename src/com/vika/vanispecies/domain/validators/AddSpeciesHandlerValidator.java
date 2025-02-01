package com.vika.vanispecies.domain.validators;

/**
 * Клас для валідації введених даних при додаванні нового виду.
 */
public class AddSpeciesHandlerValidator {

    /**
     * Перевіряє, чи є назва виду валідною (не порожня і не null).
     *
     * @param name назва виду
     * @return true, якщо назва валідна, інакше false
     */
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    /**
     * Перевіряє, чи є вибраний тип виду коректним (1 або 2).
     *
     * @param typeChoice вибір типу виду
     * @return true, якщо тип валідний, інакше false
     */
    public static boolean isValidType(int typeChoice) {
        return typeChoice == 1 || typeChoice == 2;
    }

    /**
     * Перевіряє, чи є рік вимирання валідним.
     * Порожнє значення вважається валідним.
     *
     * @param extinctionYearInput рік вимирання у вигляді рядка
     * @return true, якщо рік валідний, інакше false
     */
    public static boolean isValidExtinctionYear(String extinctionYearInput) {
        if (extinctionYearInput.isEmpty()) {
            return true;
        }
        try {
            int year = Integer.parseInt(extinctionYearInput);
            return year >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Перевіряє, чи є опис виду валідним (не порожній і не null).
     *
     * @param description опис виду
     * @return true, якщо опис валідний, інакше false
     */
    public static boolean isValidDescription(String description) {
        return description != null && !description.trim().isEmpty();
    }

    /**
     * Перевіряє, чи є введений континент коректним.
     * Якщо континент не входить у список допустимих значень або порожній, повертає false.
     *
     * @param continent назва континенту
     * @return true, якщо континент валідний або порожній, інакше false
     */
    public static boolean isValidContinent(String continent) {
        String[] validContinents = {"Африка", "Євразія", "Північна Америка", "Південна Америка", "Австралія", "Антарктида"};
        for (String valid : validContinents) {
            if (valid.equalsIgnoreCase(continent.trim())) {
                return true;
            }
        }
        return continent.isEmpty();
    }
}
