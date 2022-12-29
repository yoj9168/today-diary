package com.today_diary.admin.service.User;

import com.today_diary.admin.domain.user.UserRepository;
import com.today_diary.admin.web.dto.UserSaveRequestDto;
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
}
