package com.oddfar.campus.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oddfar.campus.business.domain.entity.ActivityEntity;
import com.oddfar.campus.business.domain.entity.UserEntity;
import com.oddfar.campus.business.domain.vo.ActivityVo;
import com.oddfar.campus.business.domain.vo.CreateActivityVo;
import com.oddfar.campus.business.mapper.ActivityMapper;
import com.oddfar.campus.business.mapper.UserMapper;
import com.oddfar.campus.business.service.ActivityService;
import com.oddfar.campus.common.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, ActivityEntity> implements ActivityService {
    private static final Logger logger = LoggerFactory.getLogger("activity");

    @Resource
    private ActivityMapper activityMapper;


    public ActivityServiceImpl(ActivityMapper activityMapper) {
        this.activityMapper = activityMapper;
    }

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public List<ActivityVo> getActivityList() {
        // 获取活动列表
        List<ActivityVo> activities = activityMapper.selectActivityList();

        // 获取所有活动参与者ID
        Set<Long> userIds = activities.stream()
                .flatMap(activity -> Arrays.stream(activity.getUserList().split(",")))
                .map(Long::parseLong)
                .collect(Collectors.toSet());

        // 获取用户信息
        List<UserEntity> users = userMapper.selectUsersByIds(new ArrayList<>(userIds));

        // 创建一个映射，方便后续查找
        Map<Long, UserEntity> userMap = users.stream()
                .collect(Collectors.toMap(UserEntity::getUserId, user -> user));

        // 将用户信息设置到每个活动中
        for (ActivityVo activity : activities) {
            List<UserEntity> participants = new ArrayList<>();
            String[] ids = activity.getUserList().split(",");
            for (String id : ids) {
                Long userId = Long.parseLong(id);
                if (userMap.containsKey(userId)) {
                    participants.add(userMap.get(userId));
                }
            }
            activity.setParticipants(participants);
        }
        return activities;
    }

    public List<UserEntity> addUserToActivity(Long activityId, Long userId) {
        // 1. 获取当前的user_list
        String currentUserList = activityMapper.getUserListByActivityId(activityId);

        // 2. 更新user_list
        String newUserList;
        if (currentUserList == null || currentUserList.isEmpty()) {
            newUserList = String.valueOf(userId);
        } else {
            // 确保不会重复添加用户ID
            Set<String> userIdSet = new HashSet<>(Arrays.asList(currentUserList.split(",")));
            userIdSet.add(String.valueOf(userId));
            newUserList = String.join(",", userIdSet);
        }

        // 3. 保存更新
        activityMapper.updateUserList(activityId, newUserList);
        // 4. 获取更新后的 user_list 中的所有用户 IDs
        Set<Long> userIds = new HashSet<>(Arrays.asList(newUserList.split(",")))
                .stream()
                .map(Long::parseLong)
                .collect(Collectors.toSet());

        // 5. 查询用户信息
        List<UserEntity> users = userMapper.selectUsersByIds(new ArrayList<>(userIds));

        // 6. 创建用户信息的映射
        Map<Long, UserEntity> userMap = users.stream()
                .collect(Collectors.toMap(UserEntity::getUserId, user -> user));

        // 7. 返回用户信息映射
        return users;
    }


    @Transactional
    public List<ActivityEntity> activitySelect() {
        return activityMapper.selectList(null);
    }

    @Transactional
    public ActivityEntity selectActivityById(Long activityId) {
        return activityMapper.selectById(activityId);
    }

    @Override
    @Transactional
    public int createActivity(CreateActivityVo createActivityVo) {
        logger.debug(createActivityVo.toString());
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setName(createActivityVo.getName());
        activityEntity.setDescription(createActivityVo.getDescription());
        activityEntity.setLocation(createActivityVo.getLocation());
        activityEntity.setStartTime(createActivityVo.getStartTime());
        activityEntity.setEndTime(createActivityVo.getEndTime());
        // 设置创建时间、更新时间和创建者 ID
        activityEntity.setCreateTime(new Date());
        activityEntity.setUpdateTime(new Date());
        activityEntity.setCreatorId(SecurityUtils.getUserId());
        activityEntity.setStatus(1);
//        activityEntity.setUserList(new ArrayList<>().toString());

        int insert = activityMapper.insert(activityEntity);
        return insert;
    }

    @Transactional
    public void updateActivity(ActivityEntity activity) {
        activityMapper.updateById(activity);
    }

    @Transactional
    public void deleteActivity(Long activityId) {
        activityMapper.deleteById(activityId);
    }

    @Override
    public boolean save(ActivityEntity entity) {
        return super.save(entity);
    }

    @Override
    public boolean saveOrUpdate(ActivityEntity entity, Wrapper<ActivityEntity> updateWrapper) {
        return super.saveOrUpdate(entity, updateWrapper);
    }


    @Override
    public void addParticipants(Long activityId, String userList) {
        // 根据活动 ID 和参与人列表，更新数据库
        // 调用 Mapper 方法
        activityMapper.addParticipants(activityId, userList);
    }

    @Override
    public void removeParticipants(Long activityId, List<String> userList) {
        // 根据活动 ID 和参与人列表，更新数据库
        // 实际实现可能涉及到维护一个关联表
        // 调用 Mapper 方法移除参与者
        activityMapper.removeParticipants(activityId, userList);
    }

    @Override
    public Long getCreatorId(Long activityId) {
        ActivityEntity activity = activityMapper.selectById(activityId);
        return activity != null ? activity.getCreatorId() : null;
    }

    @Override
    public void setCreatorId(Long activityId, Long creatorId) {
        ActivityEntity activity = activityMapper.selectById(activityId);
        if (activity != null) {
            activity.setCreatorId(creatorId);
            activityMapper.updateById(activity);
        }
    }

    @Override
    public List<String> getParticipantsByActivityId(Long activityId) {
        return activityMapper.getParticipantsByActivityId(activityId);
    }

    // 其他方法...
}