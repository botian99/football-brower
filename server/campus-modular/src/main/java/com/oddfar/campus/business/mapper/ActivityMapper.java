import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityMapper {

    /**
     * 查询活动列表
     *
     * @return 活动列表
     */
    List<ActivityEntity> selectList(ActivityEntity activityEntity);

    /**
     * 根据ID查询活动
     *
     * @param activityId 活动ID
     * @return 活动实体
     */
    ActivityEntity selectById(@Param("activityId") Long activityId);

    /**
     * 插入活动
     *
     * @param activity 活动实体
     */
    void insert(ActivityEntity activity);

    /**
     * 更新活动
     *
     * @param activity 活动实体
     */
    void updateById(ActivityEntity activity);

    /**
     * 删除活动
     *
     * @param activityId 活动ID
     */
    void deleteById(Long activityId);

    // 其他方法...
}