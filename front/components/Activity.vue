<template>
  <div class="container content-box" :style="'width: 605px;'">
    <!-- Header Section: Creator's Information -->

    <div class="content-box-top box--flex">
      <el-avatar
        class="content-box-avatar"
        shape="circle"
        :size="50"
        :src="handleCampusUrl(activity.creatorAvatar)"
      ></el-avatar>
      <div
        class="box--flex"
        style="height: 100%; justify-content: center; flex-direction: column"
      >
        <div class="box--flex" style="margin-bottom: 7px; font-weight: bolder">
          <span class="nickName">
            {{ activity.creatorName }}
          </span>
        </div>
        <div class="box--flex">
          <!--          <div class="content-category-tag">-->
          <!--            <div>{{ contentObj.params.categoryName }}</div>-->
          <!--          </div>-->
          <span class="content-box-time">
            {{ timeSince }}
          </span>
        </div>
      </div>
    </div>
<!--    <div class="header">-->
<!--      <div class="avatar">-->
<!--        <el-avatar-->
<!--          class="content-box-avatar"-->
<!--          shape="circle"-->
<!--          :size="50"-->
<!--          :src="handleCampusUrl(activity.creatorAvatar)"-->
<!--        ></el-avatar>-->
<!--      </div>-->
<!--      <div class="user-info">-->
<!--        <p class="name">{{ activity.creatorName }}</p>-->
<!--        <p class="time-since">Posted {{ timeSince }}</p>-->
<!--      </div>-->
<!--    </div>-->

    <!-- Activity Details Section -->
    <div class="activity-details">
      <p class="title">{{ activity.name }}</p>
      <p class="description">{{ activity.description }}</p>
      <div class="info">
        <span><strong>Start Time:</strong> {{ formatDate(activity.startTime) }}</span>
        <span><strong>End Time:</strong> {{ formatDate(activity.endTime) }}</span>
        <span><strong>Location:</strong> {{ activity.location }}</span>
      </div>
    </div>

    <!-- Participants Section -->
    <div class="participants">
      <p class="title">Current Participants</p>
      <div class="participants-container">
        <el-tooltip v-for="user in activity.participants" :key="user.id" :content="user.userName">
          <el-avatar
            class="content-box-avatar"
            shape="circle"
            :size="50"
            :src="handleCampusUrl(user.avatar)"
          ></el-avatar>
        </el-tooltip>
      </div>
    </div>

    <!-- Join Activity Button -->
    <el-button @click="joinActivity" class="btn" :disabled="btnDisabled">Join Activity</el-button>
  </div>
</template>

<script>
import {diffTime, handleCampusUrl} from "@/utils/campus";
import operateApi from "@/api/operate";

export default {
  name: 'Activity',
  props: {
    activity: {
      type: Object,
      required: true
    },
  },
  data() {
    return {
      error: null
    };
  },
  computed: {
    timeSince() {
      return diffTime(this.activity.createTime, new Date())
    },
    btnDisabled() {
      const userIdList = this.activity.participants.map((item) => item.userId)
      return userIdList.includes(this.globalVariable.userInfoGlobal.userId);
    }
  },
  methods: {
    handleCampusUrl,
    formatDate(date) {
      return new Date(date).toLocaleString(); // Customize the date format as needed
    },
    joinActivity() {
      // Simulate joining activity via AJAX
      operateApi.joinActivity(Number(this.activity.id)).then((res) => {
        this.activity.participants = res.data;
      });
    }
  }
}
</script>

<style scoped>
.container {
  max-width: 605px; /* Set the container width */
  padding: 15px; /* Adjust padding to fit within the width */
  margin: auto; /* Center the container */
  box-sizing: border-box; /* Ensure padding and borders are included in width */
  margin-bottom: 10px;
}

.header {
  display: flex;
  align-items: center;
  margin-bottom: 15px; /* Adjusted margin */
}

.avatar {
  width: 50px; /* Fixed size for avatar container */
  height: 50px;
}

.avatar img {
  width: 100%; /* Use full width of container */
  height: 100%;
  border-radius: 50%; /* Circular avatar */
}

.user-info {
  margin-left: 10px; /* Space between avatar and user info */
}

.name {
  font-size: 1em; /* Consistent with design */
  font-weight: bold;
}

.time-since {
  color: #666;
  font-size: 0.9em; /* Slightly smaller font size */
}

.activity-details {
  margin-bottom: 15px; /* Adjusted margin */
}

.title {
  font-size: 1.2em; /* Title font size */
  font-weight: bold;
  margin-bottom: 8px; /* Space below title */
}

.description {
  margin-bottom: 10px; /* Space below description */
  font-size: 0.9em; /* Smaller font size for description */
}

.info span {
  display: block;
  margin-bottom: 6px; /* Space between info lines */
  font-size: 0.9em; /* Smaller font size */
}

.participants {
  margin-bottom: 15px; /* Adjusted margin */
}

.participants-container {
  display: flex;
  flex-wrap: wrap;
  gap: 8px; /* Space between participant avatars */
}

.btn {
  display: block;
  width: 100%; /* Full width button */
  background-color: #007bff;
  color: white;
  border: none;
  padding: 8px 0; /* Adjusted padding for button */
  border-radius: 4px; /* Slightly rounded corners */
  cursor: pointer;
  font-size: 0.9em; /* Smaller font size for button text */
  text-align: center; /* Center the button text */
}

.btn:hover {
  background-color: #0056b3;
}

</style>
