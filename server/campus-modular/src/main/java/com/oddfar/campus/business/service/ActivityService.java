package com.oddfar.campus.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oddfar.campus.business.domain.entity.ActivityEntity;
import com.oddfar.campus.business.domain.entity.UserEntity;
import com.oddfar.campus.business.domain.vo.ActivityVo;
import com.oddfar.campus.business.domain.vo.CreateActivityVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ActivityService extends IService<ActivityEntity> {
    /**
     *
     * @return
     */
    List<ActivityEntity> activitySelect();

    List<ActivityVo> getActivityList();

    /**
     *
     * @param activityId
     * @return
     */
    ActivityEntity selectActivityById(Long activityId);

    List<UserEntity> addUserToActivity(Long activityId, Long userId);

    int createActivity(CreateActivityVo createActivityVo);

    void updateActivity(ActivityEntity activity);

    void deleteActivity(Long activityId);

    // 添加参与人
    void addParticipants(Long activityId, String userList);

    // 删除参与人
    void removeParticipants(Long activityId, List<String> userList);

    // 获取创建人 ID
    Long getCreatorId(Long activityId);

    // 设置创建人 ID
    void setCreatorId(Long activityId, Long creatorId);

    // 获取参与人列表
    List<String> getParticipantsByActivityId(Long activityId);


}