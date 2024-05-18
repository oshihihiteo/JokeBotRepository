package com.mybot.labs.javabot.service;

import com.mybot.labs.javabot.model.User;
import com.mybot.labs.javabot.model.UserAuthority;

import java.util.List;

public interface UserService {

    void registration(String username, String password);

    User getUserById(Long userId);

    void updateUserRoles(Long userId, List<UserAuthority> authorities);
}
