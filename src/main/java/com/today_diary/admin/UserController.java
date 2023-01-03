package com.today_diary.admin;

import com.today_diary.admin.config.BaseException;
import com.today_diary.admin.config.BaseResponse;
import com.today_diary.admin.config.BaseResponseStatus;
import com.today_diary.admin.service.User.UserService;
import com.today_diary.admin.web.dto.UserResponseDto;
import com.today_diary.admin.web.dto.UserSaveRequestDto;
import com.today_diary.admin.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.today_diary.admin.config.BaseResponseStatus.POST_USERS_CONFIRM_PASSWORD;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/user")
    public BaseResponse save(@RequestBody @Valid UserSaveRequestDto dto) {

        if (!dto.getPasswordConfirm().equals(dto.getPassword())) {
            return new BaseResponse(POST_USERS_CONFIRM_PASSWORD);
        }

        Long id = service.save(dto);
        return new BaseResponse(id);
    }

    @GetMapping("/user/{userId}")
    public UserResponseDto findById(@PathVariable Long userId){
        return service.findById(userId);
    }

    @PutMapping("/user/{userId}")
    public BaseResponse<Long> update(@PathVariable Long userId, @RequestBody UserUpdateRequestDto dto){
        Long id = service.update(userId, dto);
        return new BaseResponse<>(id);
    }
}
