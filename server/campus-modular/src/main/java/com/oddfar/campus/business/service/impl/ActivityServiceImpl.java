import org.springframework.stereotype.Service;
import org.springframework.transactional.annotation.Transactional;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityMapper activityMapper;

    public ActivityServiceImpl(ActivityMapper activityMapper) {
        this.activityMapper = activityMapper;
    }

    @Transactional
    public List<ActivityEntity> activitySelect() {
        return activityMapper.selectList(null);
    }

    @Transactional
    public ActivityEntity selectActivityById(Long activityId) {
        return activityMapper.selectById(activityId);
    }

    @Transactional
    public void createActivity(ActivityEntity activity) {
        activityMapper.insert(activity);
    }

    @Transactional
    public void updateActivity(ActivityEntity activity) {
        activityMapper.updateById(activity);
    }

    @Transactional
    public void deleteActivity(Long activityId) {
        activityMapper.deleteById(activityId);
    }

    // 其他方法...
}