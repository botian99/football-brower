package com.oddfar.campus.business.mapper;

import com.oddfar.campus.business.domain.entity.ActivityEntity;
import com.oddfar.campus.business.domain.vo.ActivityVo;
import com.oddfar.campus.common.core.BaseMapperX;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActivityMapper extends BaseMapperX<ActivityEntity> {

    /**
     * 查询活动列表
     *
     * @return 活动列表
     */
    @Select("SELECT * FROM activity")
    List<ActivityEntity> selectList();

    /**
     * 根据ID查询活动
     *
     * @param activityId 活动ID
     * @return 活动实体
     */
    ActivityEntity selectActivityById(@Param("activityId") Long activityId);

    @Override
    int insert(ActivityEntity activity);

    List<ActivityVo> selectActivityList();


    /**
     * 获取活动的用户列表（user_list）
     * @param activityId 活动 ID
     * @return user_list 字段的值（逗号分隔的用户ID字符串）
     */
    @Select("SELECT user_list FROM activity WHERE id = #{activityId}")
    String getUserListByActivityId(@Param("activityId") Long activityId);

    /**
     * 更新活动的用户列表（user_list）
     * @param activityId 活动 ID
     * @param userList 更新后的用户列表（逗号分隔的用户ID字符串）
     */
    @Update("UPDATE activity SET user_list = #{userList} WHERE id = #{activityId}")
    void updateUserList(@Param("activityId") Long activityId, @Param("userList") String userList);


    /**
     * 更新活动
     *
     * @param activity 活动实体
     * @return
     */
    int updateById(ActivityEntity activity);

    /**
     * 删除活动
     *
     * @param activityId 活动ID
     */
    void deleteById(Long activityId);


    // 根据活动 ID 添加参与人
    void addParticipants(@Param("activityId") Long activityId, @Param("userList") String userList);

    // 根据活动 ID 移除参与人
    void removeParticipants(@Param("activityId") Long activityId, @Param("userList")  List<String> userList);

    // 获取活动的创建人 ID
    @Select("SELECT creator_id FROM activity WHERE id = #{activityId}")
    Long getCreatorId(@Param("activityId") Long activityId);

    List<String> getParticipantsByActivityId(Long activityId);
    // 其他方法...
}