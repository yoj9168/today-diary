package com.today_diary.admin.service;

import com.today_diary.admin.config.BaseException;
import com.today_diary.admin.config.BaseResponse;
import com.today_diary.admin.domain.user.User;
import com.today_diary.admin.domain.user.UserRepository;
import com.today_diary.admin.web.dto.user.UserResponseDto;
import com.today_diary.admin.web.dto.user.UserSaveRequestDto;
import com.today_diary.admin.web.dto.user.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.today_diary.admin.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository repository;

    @Transactional
    public Long save(UserSaveRequestDto dto) throws BaseException {
        Optional<User> emailExisting = repository.findByEmail(dto.getEmail());
        Optional<User> nameExisting = repository.findByName(dto.getName());

        if(emailExisting.isPresent()){
            throw new BaseException(POST_USERS_EMAIL_DUPLICATE);
        }
        if(nameExisting.isPresent()){
            throw new BaseException(POST_USERS_NAME_DUPLICATE);
        }
        return repository.save(dto.toEntity()).getId();
    }

    public UserResponseDto findById(Long userId) throws BaseException {
        User user = repository.findById(userId).orElseThrow(() -> new BaseException(NOT_EXIST_USER));
        return new UserResponseDto(user);
    }
    @Transactional
    public Long update(Long userId, UserUpdateRequestDto dto) throws BaseException {
        User user = repository.findById(userId).orElseThrow(() -> new BaseException(NOT_EXIST_USER));
        user.update(dto.getPhoneNumber(), dto.getAge(), dto.getSex());
        return user.getId();
    }

    public Optional checkEmailDuplicate(String email) {
        Optional<User> emailExisting = repository.findByEmail(email);
        return emailExisting;
    }

    public Optional checkNameDuplicate(String name) {
        Optional<User> nameExisting = repository.findByName(name);
        return nameExisting;
    }
}
