package com.oddfar.campus.business.domain.vo;

import lombok.Data;
import com.oddfar.campus.business.domain.entity.UserEntity;

import java.util.Date;
import java.util.List;

@Data
public class ActivityVo {

    private Long id;
    private String name;
    private String description;
    private Date startTime;
    private Date endTime;
    private String location;
    private Date createTime;
    private Date updateTime;
    private Integer status;
    private Long creatorId;
    private String creatorName;
    private String creatorAvatar;
    private String userList; // Comma-separated user IDs
    private List<UserEntity> participants; // Key is userId, value is UserEntity
}
