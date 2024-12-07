package ru.kpfu.itis.zakirov.dao;

import ru.kpfu.itis.zakirov.entity.User;

import java.util.List;

public interface UserDao {

    User get(Integer id);

    List<User> getAll();

    User getByLogin(String login);

    void save(User user);
}
