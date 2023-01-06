package com.today_diary.admin;

import com.today_diary.admin.config.BaseException;
import com.today_diary.admin.config.BaseResponse;
import com.today_diary.admin.config.BaseResponseStatus;
import com.today_diary.admin.service.User.UserService;
import com.today_diary.admin.web.dto.UserResponseDto;
import com.today_diary.admin.web.dto.UserSaveRequestDto;
import com.today_diary.admin.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.today_diary.admin.config.BaseResponseStatus.*;

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
        if(id < 0L){
            if(id == -1L) {
                return new BaseResponse(POST_USERS_NAME_DUPLICATE);
            }
            else{
                return new BaseResponse(POST_USERS_EMAIL_DUPLICATE);
            }
        }
        return new BaseResponse(id);
    }
    @GetMapping("/user-email/{email}/exists")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email){
        return ResponseEntity.ok(service.checkEmailDuplicate(email));
    }
    @GetMapping("/user-name/{name}/exists")
    public ResponseEntity<Boolean> checkNameDuplicate(@PathVariable String name){
        return ResponseEntity.ok(service.checkNameDuplicate(name));
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
