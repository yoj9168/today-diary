package com.today_diary.admin.service.User;

import com.today_diary.admin.domain.user.User;
import com.today_diary.admin.domain.user.UserRepository;
import com.today_diary.admin.web.dto.UserResponseDto;
import com.today_diary.admin.web.dto.UserSaveRequestDto;
import com.today_diary.admin.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository repository;

    @Transactional
    public Long save(UserSaveRequestDto dto) {
        return repository.save(dto.toEntity()).getId();
    }

    public UserResponseDto findById(Long userId) {
        User user = repository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지않음" + userId));
        return new UserResponseDto(user);
    }
    @Transactional
    public Long update(Long userId, UserUpdateRequestDto dto) {
        User user = repository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지않음" + userId));
        user.update(dto.getPhoneNumber(), dto.getAge(), dto.getSex());
        return user.getId();
    }
}
