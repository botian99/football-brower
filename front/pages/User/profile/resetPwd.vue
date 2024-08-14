<template>
  <el-form ref="form" :model="user" :rules="rules" label-width="150px">
    <el-form-item label="Old Password" prop="oldPassword">
      <el-input v-model="user.oldPassword" placeholder="Please enter old password" type="password" show-password/>
    </el-form-item>
    <el-form-item label="New Password" prop="newPassword">
      <el-input v-model="user.newPassword" placeholder="Please enter new password" type="password" show-password/>
    </el-form-item>
    <el-form-item label="Confirm Password" prop="confirmPassword">
      <el-input v-model="user.confirmPassword" placeholder="Please confirm new password" type="password" show-password/>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" size="mini" @click="submit">Save</el-button>
      <el-button type="danger" size="mini" @click="close">Close</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import  userInfoApi  from "@/api/userInfo";

export default {
  data() {
    const equalToPassword = (rule, value, callback) => {
      if (this.user.newPassword !== value) {
        callback(new Error("Entered passwords differ!"));
      } else {
        callback();
      }
    };
    return {
      user: {
        oldPassword: undefined,
        newPassword: undefined,
        confirmPassword: undefined
      },
      // 表单校验
      rules: {
        oldPassword: [
          { required: true, message: "Old password cannot be empty", trigger: "blur" }
        ],
        newPassword: [
          { required: true, message: "The new password cannot be empty", trigger: "blur" },
          { min: 6, max: 20, message: "Length ranging from 6 to 20 characters", trigger: "blur" }
        ],
        confirmPassword: [
          { required: true, message: "Confirm password cannot be empty", trigger: "blur" },
          { required: true, validator: equalToPassword, trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    submit() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          userInfoApi.updateUserPwd(this.user.oldPassword, this.user.newPassword).then(response => {
            this.$message.success("Modified successfully");   //调用Element库中的内置对象方法弹出成功的提示框
          });
        }
      });
    },
    close() {
      this.$router.push("/");   //vue-router内置方法跳转
    }
  }
};
</script>
