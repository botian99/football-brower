<script>
export default {
  name: "FootballGame",
  methods: {},
  props: ["gameInfo"],
}
</script>

<template>
  <div class="content-box" :style="{width: '605px', 'box-sizing': 'border-box'}">
    <div class="title">
          <div class="title-name">
            {{gameInfo.competition.name || ''}}
          </div>
      <div>
              <div class="content-category-tag">
                {{gameInfo.area.name}}
              </div>
              <span class="content-box-time">
                    {{ gameInfo.utcDate }}
              </span>
      </div>
    </div>
    <div class="team-for-game">
      <div class="team-info home-team">
        <div class="team-logo">
          <img :src="gameInfo.homeTeam.crest" style="width:50px;" alt="Home Team"/>
        </div>
        <div>{{ gameInfo.score.fullTime.home }}</div>
      </div>
      <div>{{ gameInfo.homeTeam.name }}</div>
      <div style="width: 20px;"></div>
      <div>{{ gameInfo.awayTeam.name }}</div>
      <div class="team-info away-team">
        <div class="team-logo">
          <img :src="gameInfo.awayTeam.crest" style="width:50px;" alt="Home Team"/>
        </div>
        <div>{{ gameInfo.score.fullTime.away }}</div>
      </div>
    </div>
  </div>
</template>


<style>
.content-box {
  /* box-shadow: 0 10px 12px 0 rgba(0, 0, 0, 0.2); */
  margin: 0 0 20px 0;
  padding: 5px 20px 0;
  background-color: #fff;
  box-shadow: 0px 0px 25px rgb(0 0 0 / 10%);
  border: 1px solid rgba(255, 255, 255, 0.5);
  border-right: 1px solid rgba(255, 255, 255, 0.2);
  border-bottom: 1px solid rgba(255, 255, 255, 0.2);
  border-radius: 10px;
}

.box--flex {
  display: flex;
}

.content-box-top {
  height: 50px;
}

.content-feed-ogText {
  color: #333;
  font-size: 14px;
  line-height: 24px;
}

.content-footer {
  height: 40px;
  line-height: 40px;
  margin: 0 15px;
}

.nickName {
  font-size: 15px;
  font-weight: bold;
}

.content-box-time {
  font-size: 12px;
  color: darkgrey;
}

.content-category-tag {
  margin-right: 4px;
  font-size: 12px;
  display: inline-block;
  min-width: 26px;
  height: 13px;
  border: 1px solid #ccc;
  border-radius: 2px;
  line-height: 13px;
  color: #939393;
  text-align: center;
}

.content-box-avatar {
  margin-right: 10px;
}

.content-box .title {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  margin-bottom: 10px;
}

.content-box .title .title-name {
  margin-right: 10px;
}

.content-box .team-for-game {
  display: flex;
  justify-content: space-between;
  flex-direction: row;
  align-items: center;
  padding: 0 50px;
}

.content-box .team-for-game .team-info {
  display: flex;
  width: 200px;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.content-box .team-for-game .team-info .team-logo {
  padding: 10px;
  border-radius: 16px;
  border: 1px solid #939393;
}
</style>
