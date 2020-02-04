package com.serheev.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private Long id;
    private String login;
    private String password;
    private String role;
    private boolean enabled;
    private String createdDate;
}
