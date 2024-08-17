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
        List<ActivityEntity> page = activityService.activitySelect();

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
     * @param activity 活动信息
     * @return
     */
    @PostMapping(value = "/create", name = "创建新活动")
    public R createActivity(@RequestBody ActivityEntity activity) {
        activityService.createActivity(activity);
        return R.ok();
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
}