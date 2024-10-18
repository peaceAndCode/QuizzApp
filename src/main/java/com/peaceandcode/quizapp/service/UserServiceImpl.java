package com.peaceandcode.quizapp.service;

import com.peaceandcode.quizapp.entity.User;
import com.peaceandcode.quizapp.exception.InvalidCredentialsException;
import com.peaceandcode.quizapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(()->new InvalidCredentialsException("User with username: "+username+" not found"));
    }
}
