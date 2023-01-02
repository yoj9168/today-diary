package com.today_diary.admin.web;

import com.today_diary.admin.domain.user.User;
import com.today_diary.admin.domain.user.UserRepository;
import com.today_diary.admin.web.dto.UserSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository repository;

    @After
    public void tearDown() throws Exception{
        repository.deleteAll();
    }

    @Test
    public void 회원가입() throws Exception{
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String name = "유오준";
        String email = "yoj9168@gmail.com";
        String password = "test1234";
        String phoneNumber = "010-4059-9168";
        UserSaveRequestDto dto = UserSaveRequestDto.builder()
                .name(name)
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .build();
        String url = "http://localhost:"+port+"/user";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, dto, Long.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<User> all = repository.findAll();

        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getEmail()).isEqualTo(email);
        assertThat(Boolean.TRUE).isEqualTo(encoder.matches(password, all.get(0).getPassword()));
        assertThat(all.get(0).getPhoneNumber()).isEqualTo(phoneNumber);

    }
}
