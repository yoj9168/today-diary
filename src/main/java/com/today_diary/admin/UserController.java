package com.today_diary.admin;

import com.today_diary.admin.service.User.UserService;
import com.today_diary.admin.web.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/user")
    public Long save(@RequestBody UserSaveRequestDto dto){
        return service.save(dto);
    }
}
