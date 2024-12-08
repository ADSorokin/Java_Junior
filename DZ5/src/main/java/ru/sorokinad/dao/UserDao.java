package ru.sorokinad.dao;

import ru.sorokinad.models.User;

public interface UserDao {
    void saveUser(User user);

    User getUserByUsername(String username);

    boolean existsByUsername(String username);
}
