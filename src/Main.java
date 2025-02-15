import com.vika.vanispecies.appui.views.MainMenuView;

/**
 * Головний клас програми, який містить метод {@link #main(String[])} для запуску додатка.
 * Відповідає за ініціалізацію та відображення головного меню.
 */
public class Main {

    /**
     * Головний метод програми. Запускає додаток і відображає головне меню.
     *
     * @param args Масив рядків, який може містити параметри командного рядка (не використовується в даному випадку).
     */
    public static void main(String[] args) {
        // Створення об'єкта головного меню та його відображення
        new MainMenuView().display();
    }
}
