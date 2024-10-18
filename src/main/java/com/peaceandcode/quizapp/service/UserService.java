package com.peaceandcode.quizapp.service;

import com.peaceandcode.quizapp.entity.User;

public interface UserService {
    User loadUserByUsername(String username);
}
