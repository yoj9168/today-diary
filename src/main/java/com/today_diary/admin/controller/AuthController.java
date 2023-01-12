package com.today_diary.admin.controller;

import com.today_diary.admin.config.BaseException;
import com.today_diary.admin.config.BaseResponse;
import com.today_diary.admin.config.BaseResponseStatus;
import com.today_diary.admin.service.AuthService;
import com.today_diary.admin.web.dto.auth.LoginRequestDto;
import com.today_diary.admin.web.dto.auth.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.today_diary.admin.config.BaseResponseStatus.FAIL_LOGIN;
import static com.today_diary.admin.config.BaseResponseStatus.SUCCESS;
import static com.today_diary.admin.utility.ValidationRegex.*;
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public BaseResponse login(@RequestBody LoginRequestDto dto) throws BaseException {
        LoginResponseDto response = authService.login(dto);
        return new BaseResponse(response);
    }
}
