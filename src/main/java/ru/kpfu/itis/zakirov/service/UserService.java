package ru.kpfu.itis.zakirov.service;

import java.util.List;
import ru.kpfu.itis.zakirov.dto.UserDto;
import ru.kpfu.itis.zakirov.dto.UserRegistrationDto;

public interface UserService {

    List<UserDto> getAll();

    UserDto get(Integer id);

    UserDto getByLogin(String login);

    void register(UserRegistrationDto user);
}