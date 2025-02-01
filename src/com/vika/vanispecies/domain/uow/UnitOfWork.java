package com.vika.vanispecies.domain.uow;

import com.vika.vanispecies.persistence.repository.UserRepository;

/**
 * Клас, що реалізує патерн "Одиниця роботи" (Unit of Work).
 * Відповідає за управління транзакціями та збереженням змін у базі даних.
 */
public class UnitOfWork {

    /**
     * Репозиторій користувачів, який використовується для управління даними користувачів.
     */
    private final UserRepository userRepository;

    /**
     * Конструктор класу, що ініціалізує репозиторій користувачів.
     */
    public UnitOfWork() {
        this.userRepository = new UserRepository();
    }

    /**
     * Повертає екземпляр {@link UserRepository}.
     *
     * @return об'єкт {@link UserRepository}
     */
    public UserRepository getUserRepository() {
        return userRepository;
    }

    /**
     * Фіксує (зберігає) зміни в репозиторії користувачів.
     */
    public void commit() {
        userRepository.saveUsers();
    }
}
