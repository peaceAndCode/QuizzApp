package com.peaceandcode.quizapp.service;

import com.peaceandcode.quizapp.dto.RequestLoginRegisterDto;
import com.peaceandcode.quizapp.dto.ResponseLoginRegisterDto;
import com.peaceandcode.quizapp.entity.User;
import com.peaceandcode.quizapp.exception.BadRequestException;
import com.peaceandcode.quizapp.exception.InvalidCredentialsException;
import com.peaceandcode.quizapp.mapper.UserMapper;
import com.peaceandcode.quizapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private static Map<String, Object> generateClaims(User user) {
        Map<String,Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("gameCode", user.getGameCode());
        claims.put("role", user.getRole());
        return claims;
    }

    @Override
    public ResponseLoginRegisterDto login(RequestLoginRegisterDto user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        if(!authentication.isAuthenticated()) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        User userDetails = userRepository
                .findByUsername(user.getUsername())
                .orElseThrow(()-> new InvalidCredentialsException("Invalid credentials"));

        Map<String,Object> claims = generateClaims(userDetails);
        String token = jwtService.generateToken(claims,userDetails);

        return ResponseLoginRegisterDto.builder().token(token).build();
    }

    @Override
    public ResponseLoginRegisterDto register(RequestLoginRegisterDto userToRegister) {

        if(userRepository.findByUsername(userToRegister.getUsername()).isPresent()) {
            throw new BadRequestException("Username is already in use");
        }

        User user = userMapper.entity(userToRegister);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try{
            userRepository.save(user);
        }catch(DataIntegrityViolationException e) {
            throw new BadRequestException("Error during user saving: "+e.getMessage());
        }

        Map<String,Object> claims = generateClaims(user);
        String token = jwtService.generateToken(claims,user);

        return ResponseLoginRegisterDto.builder().token(token).build();
    }
}
