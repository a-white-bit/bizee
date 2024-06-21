package com.sparta.bizee.service;

import com.sparta.bizee.dto.RegisterRequestDto;
import com.sparta.bizee.entity.User;
import com.sparta.bizee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public void register(RegisterRequestDto req) {
        // 회원 중복 확인
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 권한 확인
        if (req.isAdmin()) {
            if (!ADMIN_TOKEN.equals(req.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
        }

        User user = new User(req, passwordEncoder.encode(req.getPassword()));
        userRepository.save(user);
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("유효하지 않은 사용자 이름입니다.")
        );
        if (!Objects.equals(user.getPassword(), password)) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return user;
    }
}
