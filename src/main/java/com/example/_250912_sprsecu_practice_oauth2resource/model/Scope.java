package com.example._250912_sprsecu_practice_oauth2resource.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Scope {
    READ_USERS("read:users"),
    WRITE_USERS("write:users"),
    READ_POSTS("read:posts"),
    WRITE_POSTS("write:posts"),
    ADMIN("admin");
    private final String value;
}
