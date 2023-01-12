package com.today_diary.admin.service;

import com.today_diary.admin.config.BaseException;
import com.today_diary.admin.config.BaseResponseStatus;
import com.today_diary.admin.domain.auth.AuthRepository;
import com.today_diary.admin.domain.user.User;
import com.today_diary.admin.web.dto.auth.LoginResponseDto;
import com.today_diary.admin.web.dto.auth.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository repository;
    private final BCryptPasswordEncoder encoder;

    public LoginResponseDto login(LoginRequestDto dto) throws BaseException {
        User user = repository.findByEmail(dto.getEmail()).orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_EXIST_USER));
        if(!encoder.matches(dto.getPassword(), user.getPassword())){
            throw new BaseException(BaseResponseStatus.FAIL_LOGIN);
        }
        return new LoginResponseDto(user.getId());
    }
}
