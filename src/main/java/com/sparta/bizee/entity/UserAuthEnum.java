package com.sparta.bizee.entity;

public enum UserAuthEnum {
    USER(Authority.USER),  // 사용자 권한
    ADMIN(Authority.ADMIN);  // 관리자 권한

    private final String authority;

    UserAuthEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String USER = "AUTH_USER";
        public static final String ADMIN = "AUTH_ADMIN";
    }
}