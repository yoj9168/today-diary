package com.today_diary.admin.controller;

import com.today_diary.admin.config.BaseException;
import com.today_diary.admin.config.BaseResponse;
import com.today_diary.admin.service.UserService;
import com.today_diary.admin.utility.ValidationRegex;
import com.today_diary.admin.web.dto.user.UserResponseDto;
import com.today_diary.admin.web.dto.user.UserSaveRequestDto;
import com.today_diary.admin.web.dto.user.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.today_diary.admin.config.BaseResponseStatus.*;
import static com.today_diary.admin.utility.ValidationRegex.isRegexPhoneNumber;
import static com.today_diary.admin.utility.ValidationRegex.isRegexEmail;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/user")
    public BaseResponse save(@RequestBody @Valid UserSaveRequestDto dto) throws BaseException {
        if (!dto.getPasswordConfirm().equals(dto.getPassword())) {
            return new BaseResponse(POST_USERS_CONFIRM_PASSWORD);
        }
        Long id = service.save(dto);
        return new BaseResponse(id);
    }
    @GetMapping("/user-email/{email}/exists")
    public BaseResponse checkEmailDuplicate(@PathVariable String email){
        if(service.checkEmailDuplicate(email).isPresent()){
            return new BaseResponse(POST_USERS_EMAIL_DUPLICATE);
        }
        return new BaseResponse(SUCCESS);
    }
    @GetMapping("/user-name/{name}/exists")
    public BaseResponse checkNameDuplicate(@PathVariable String name){
        if(service.checkNameDuplicate(name).isPresent()){
            return new BaseResponse(POST_USERS_NAME_DUPLICATE);
        }
        return new BaseResponse(SUCCESS);
    }

    @GetMapping("/user/{userId}")
    public UserResponseDto findById(@PathVariable Long userId) throws BaseException {
        return service.findById(userId);
    }

    @PutMapping("/user/{userId}")
    public BaseResponse<Long> update(@PathVariable Long userId, @RequestBody UserUpdateRequestDto dto) throws BaseException {
        Long id = service.update(userId, dto);
        return new BaseResponse<>(id);
    }
}
