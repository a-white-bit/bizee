package com.sparta.bizee.service;

import com.sparta.bizee.dto.RegisterRequestDto;
import com.sparta.bizee.entity.User;
import com.sparta.bizee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void register(RegisterRequestDto req) throws Exception {
        User user = new User(
                req.getUsername(),
                req.getPassword()
        );

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new Exception("이미 중복된 사용자 이름입니다.");
        }
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !Objects.equals(user.getPassword(), password)) {
            throw new IllegalArgumentException("유효하지 않은 사용자 이름 혹은 잘못된 비밀번호");
        }
        return user;
    }
}
