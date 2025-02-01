package com.vika.vanispecies.domain.uow;

import com.vika.vanispecies.persistence.repository.UserRepository;

public class UnitOfWork {
    private final UserRepository userRepository;

    public UnitOfWork() {
        this.userRepository = new UserRepository();
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void commit() {
        userRepository.saveUsers();
    }
}
