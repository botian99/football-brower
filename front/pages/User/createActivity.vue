<template>
  <div class="publish-content">
    <el-row>
      <el-col :span="8" :xs="20">
        <el-dialog title="Quick Create" :visible.sync="dialog" width="600px;">
          <div>
            <div class="activity-item">
              <div class="activity-item-label">Title</div>
              <el-input label="Title" v-model="contentParam.title" placeholder="Please enter the title"></el-input>
            </div>
            <div class="activity-item">
              <div class="activity-item-label">Location</div>
              <el-input label="Location" v-model="contentParam.location" placeholder="Please enter the Location"></el-input>
            </div>
            <div class="activity-item">
              <div class="activity-item-label">Activity Description</div>
              <el-input
                type="textarea"
                :autosize="{ minRows: 3, maxRows: 10 }"
                placeholder="Please enter the content"
                v-model="contentParam.content"
                maxlength="500"
                show-word-limit
              >
              </el-input>
            </div>
            <div class="activity-item">
              <div class="activity-item-label">Activity Time</div>
              <el-date-picker style="width: 100%;"
                              v-model="contentParam.time"
                              start-placeholder="Start DateTime"
                              end-placeholder="End DateTime"
                              type="datetimerange">
              </el-date-picker>
            </div>
            <div style="display: flex; justify-content: right;">
              <el-button
                type="primary"
                round
                style="margin-top: 15px;"
                :disabled="checkBtn()"
                @click="publishContent()"
              >
                Create
              </el-button>
            </div>
          </div>
        </el-dialog>
      </el-col>
    </el-row>
  </div>
</template>

<script>
//引入接口定义的js文件
import operateApi from "@/api/operate";
import userInfoApi from "@/api/userInfo";
import {getToken} from "@/utils/auth";

export default {
  data() {
    return {
      //分类
      props: {
        expandTrigger: "hover",
        value: "categoryId",
        label: "categoryName",
        children: "children",
      },
      //请求参数
      contentParam: {
        title: "",
        content: "",
        location: "",
        time: [],
      },
      dialog: true,
    };
  },
  created() {
    if (getToken() === undefined) {
      this.$router.push({path: "/userlogin"});
    }
    this.haveMail();
  },

  methods: {
    //初始化数据
    haveMail() {
      userInfoApi
        .haveMail()
        .then((response) => {
        })
        .catch((response) => {
          var count = 3; //赋值多少秒
          var times = setInterval(() => {
            count--; //递减
            if (count <= 0) {
              clearInterval(times);
              this.$router.push({path: "/user/profile"});
            } else {
              this.$message.warning(
                "Will be redirected to the bound email page after" + count + "seconds"
              );
            }
          }, 1000); //1000毫秒后执行
        });
    },
    checkBtn() {
      return !(this.contentParam.title.length && this.contentParam.content.length && this.contentParam.time.length === 2);
    },
    //发布
    publishContent() {
      //请求
      const params = {
        name: this.contentParam.title,
        description: this.contentParam.content,
        location: this.contentParam.location,
        startTime: this.contentParam.time[0],
        endTime: this.contentParam.time[1],
        userList: '',
      }
      operateApi.createActivity(params).then((response) => {
        this.$message.success("Create Success");
        this.$router.push({path: "/User/management?types=1"});
      });
    },
  },
};
</script>
<style scoped>
.iconbed-text {
  margin: 0 8px 0 2px;
  font-size: 13px;
  /* color: rgb(131, 131, 131); */
}

.c-cascader {
  border-radius: 0;
}

.activity-item {
  width: 100%;
  margin-bottom: 10px;
  display: flex;
  align-items: flex-start;
  flex-direction: column;
}

.activity-item-label {
  margin-left: 10px;
  margin-bottom: 10px;
}
</style>
