package com.sparta.bizee.entity;

import com.sparta.bizee.dto.RegisterRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@NoArgsConstructor
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String authority;

    public User(RegisterRequestDto req, String password) {
        this.nickname = req.getNickname();
        this.username = req.getUsername();
        this.password = password;
        if (req.isAdmin()) {
            this.authority = UserAuthEnum.ADMIN.getAuthority();
        } else {
            this.authority = UserAuthEnum.USER.getAuthority();
        }
    }
}