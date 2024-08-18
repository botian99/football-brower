package com.oddfar.campus.business.domain.entity;

import lombok.Data;

@Data
public class UserEntity {
    private Long userId;      // 用户ID
    private String userName;  // 用户名称
    private String nickName;  // 用户昵称
    private String avatar;    // 用户头像URL
}
