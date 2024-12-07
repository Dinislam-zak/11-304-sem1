package ru.kpfu.itis.zakirov.service.impl;

import ru.kpfu.itis.zakirov.dao.UserDao;
import ru.kpfu.itis.zakirov.dao.impl.UserDaoImpl;
import ru.kpfu.itis.zakirov.dto.UserDto;
import ru.kpfu.itis.zakirov.dto.UserRegistrationDto;
import ru.kpfu.itis.zakirov.entity.User;
import ru.kpfu.itis.zakirov.service.UserService;
import ru.kpfu.itis.zakirov.util.PasswordUtil;

import java.util.List;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream().map(
                u -> new UserDto(u.getName(), null, u.getLastname())
        ).collect(Collectors.toList());
    }

    @Override
    public UserDto get(Integer id) {
        return null;
    }

    @Override
    public UserDto getByLogin(String login) {
        User u = userDao.getByLogin(login);
        return new UserDto(u.getName(), null, u.getLastname());
    }

    @Override
    public void register(UserRegistrationDto user) {
        userDao.save(new User(
                user.getName(),
                user.getLastname(),
                user.getLogin(),
                PasswordUtil.encrypt(user.getPassword())
        ));
    }
}