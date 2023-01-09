package com.today_diary.admin.service;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.today_diary.admin.config.BaseResponse;
import com.today_diary.admin.domain.auth.AuthRepository;
import com.today_diary.admin.domain.user.User;
import com.today_diary.admin.domain.user.UserRepository;
import com.today_diary.admin.web.dto.auth.LoginResponse;
import com.today_diary.admin.web.dto.auth.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository repository;
    private final BCryptPasswordEncoder encoder;

    public LoginResponse login(LoginRequest dto) {
        User user = repository.findByEmail(dto.getEmail()).get();
        if(!encoder.matches(dto.getPassword(), user.getPassword())){
            return new LoginResponse(-1L);
        }
        return new LoginResponse(user.getId());
    }
}
