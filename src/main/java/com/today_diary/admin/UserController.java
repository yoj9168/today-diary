package com.today_diary.admin;

import com.today_diary.admin.service.User.UserService;
import com.today_diary.admin.web.dto.UserResponseDto;
import com.today_diary.admin.web.dto.UserSaveRequestDto;
import com.today_diary.admin.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/user")
    public Long save(@RequestBody @Valid UserSaveRequestDto dto){
        return service.save(dto);
    }

    @GetMapping("/user/{userId}")
    public UserResponseDto findById(@PathVariable Long userId){
        return service.findById(userId);
    }

    @PutMapping("/user/{userId}")
    public Long update(@PathVariable Long userId, @RequestBody UserUpdateRequestDto dto){
        return service.update(userId, dto);
    }
}
