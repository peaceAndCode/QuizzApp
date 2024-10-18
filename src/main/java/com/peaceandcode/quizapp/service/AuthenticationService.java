package com.peaceandcode.quizapp.service;

import com.peaceandcode.quizapp.dto.RequestLoginRegisterDto;
import com.peaceandcode.quizapp.dto.ResponseLoginRegisterDto;

public interface AuthenticationService {
    public ResponseLoginRegisterDto login(RequestLoginRegisterDto user);
    public ResponseLoginRegisterDto register(RequestLoginRegisterDto userToRegister);
}
