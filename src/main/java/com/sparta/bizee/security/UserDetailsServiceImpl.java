package com.sparta.bizee.security;

import com.sparta.bizee.dto.response.ResponseCodeEnum;
import com.sparta.bizee.entity.User;
import com.sparta.bizee.exception.UserLoginException;
import com.sparta.bizee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserLoginException(ResponseCodeEnum.USER_NOT_FOUND));

        return new UserDetailsImpl(user);
    }
}