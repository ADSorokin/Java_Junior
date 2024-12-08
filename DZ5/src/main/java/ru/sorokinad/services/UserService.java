package ru.sorokinad.services;

import ru.sorokinad.dao.UserDao;
import ru.sorokinad.dao.UserDaoImpl;
import ru.sorokinad.models.User;

public class UserService {
    private final UserDao userDao = new UserDaoImpl();

    public void createUserIfNotExists(String username) {
        if (!userDao.existsByUsername(username)) {
            User user = new User(username);
            userDao.saveUser(user);
        }
    }

    public boolean userExists(String username) {
        return userDao.existsByUsername(username);
    }
}