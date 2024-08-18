package com.oddfar.campus.business.controller.web;

import com.oddfar.campus.business.domain.entity.ActivityEntity;
import com.oddfar.campus.business.domain.vo.ActivityVo;
import com.oddfar.campus.business.domain.vo.CreateActivityVo;
import com.oddfar.campus.business.service.ActivityService;
import com.oddfar.campus.common.annotation.Anonymous;
import com.oddfar.campus.common.annotation.ApiResource;
import com.oddfar.campus.common.domain.R;
import com.oddfar.campus.common.enums.ResBizTypeEnum;
import com.oddfar.campus.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
@ApiResource(name = "活动模块api", appCode = "activity", resBizType = ResBizTypeEnum.BUSINESS)
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 查询活动列表
     */
    @Anonymous
    @GetMapping(value = "/list", name = "查询活动列表")
    public R activityList() {
        List<ActivityVo> page = activityService.getActivityList();

        return R.ok().put(page);
    }

    /**
     * 查询指定活动
     * @param activityId 活动id
     * @return
     */
    @Anonymous
    @GetMapping(value = "/{activityId}", name = "查询指定活动")
    public R activityDetail(@PathVariable Long activityId) {
        ActivityEntity activityEntity = activityService.selectActivityById(activityId);
        return R.ok(activityEntity);
    }

    /**
     * 创建新活动
     * @param createActivityVo 活动信息
     * @return
     */
    @PostMapping(value = "/create", name = "创建新活动")
    public R createActivity(@Validated  @RequestBody CreateActivityVo createActivityVo) {
        return R.ok(activityService.createActivity(createActivityVo));
    }

    /**
     * 更新活动信息
     * @param activity 活动信息
     * @return
     */
    @PutMapping(value = "/update", name = "更新活动信息")
    public R updateActivity(@RequestBody ActivityEntity activity) {
        activityService.updateActivity(activity);
        return R.ok();
    }

    /**
     * 删除活动
     * @param activityId 活动id
     * @return
     */
    @DeleteMapping(value = "/{activityId}", name = "删除活动")
    public R deleteActivity(@PathVariable Long activityId) {
        activityService.deleteActivity(activityId);
        return R.ok();
    }

    /**
     * 更新活动信息
     * @param id 活动id
     * @return
     */
    @PutMapping(value = "/join", name = "加入活动")
    public R joinActivityByActivityId(@RequestBody Long id) {

        return R.ok(activityService.addUserToActivity(id, SecurityUtils.getUserId()));
    }

}