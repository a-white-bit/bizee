package com.sparta.bizee.service;

import com.sparta.bizee.dto.request.RegisterRequestDto;
import com.sparta.bizee.dto.response.ResponseCodeEnum;
import com.sparta.bizee.entity.User;
import com.sparta.bizee.entity.UserRoleEnum;
import com.sparta.bizee.exception.UserRegistrationException;
import com.sparta.bizee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${AUTH_ADMIN_TOKEN}")
    private String ADMIN_TOKEN;

    @Transactional
    public void register(RegisterRequestDto req) {
        // 회원 중복 확인
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new UserRegistrationException(ResponseCodeEnum.DUPLICATED_USER);
        }

        // 사용자 권한 확인
        String userAuthority;
        if (req.isAdmin()) {
            if (!ADMIN_TOKEN.equals(req.getAdminToken())) {
                throw new UserRegistrationException(ResponseCodeEnum.NOT_ADMIN);
            }
            userAuthority = UserRoleEnum.ADMIN.getAuthority();
        } else {
            userAuthority = UserRoleEnum.USER.getAuthority();
        }

        // 빌더 패턴
        User user = User.builder()
                .nickname(req.getNickname())
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .authority(userAuthority)
                .build();

        userRepository.save(user);
    }
}
