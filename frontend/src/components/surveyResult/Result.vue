<template>
  <div class="container blueArea">
    <h1>당신의 게임 성향을 분석하는 중입니다... 유저</h1>
    <div class="row">
        <img src="../../assets/img/human.png" height="400px" />
          <button style="width:100%; height:80px; font-size:40px;" @click="showA">결과 확인하기</button>

    </div>

  </div>
</template>

<script>
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_API_SERVER_URL;
export default {
  data() {
    return { 
        result: "" ,
        resultUrl: ""};
  },
  methods: {
    showA() {
      this.e = 0;
      this.i = 0;
      this.n = 0;
      this.s = 0;
      this.t = 0;
      this.f = 0;
      this.j = 0;
      this.p = 0;
      this.result = "";
      this.answer = this.$store.state.result;
      for (this.step = 0; this.step < 12; this.step++) {
        if (this.answer[this.step] == "E") {
          this.e++;
        } else if (this.answer[this.step] == "I") {
          this.i++;
        } else if (this.answer[this.step] == "N") {
          this.n++;
        } else if (this.answer[this.step] == "S") {
          this.s++;
        } else if (this.answer[this.step] == "T") {
          this.t++;
        } else if (this.answer[this.step] == "F") {
          this.f++;
        } else if (this.answer[this.step] == "J") {
          this.j++;
        } else if (this.answer[this.step] == "P") {
          this.p++;
        }
      }
      if (this.e >= 2) {
        this.result += "E";
      } else {
        this.result += "I";
      }

      if (this.n >= 2) {
        this.result += "N";
      } else {
        this.result += "S";
      }

      if (this.t >= 2) {
        this.result += "T";
      } else {
        this.result += "F";
      }

      if (this.j >= 2) {
        this.result += "J";
      } else {
        this.result += "P";
      }

      this.$store.commit("putPage", this.result);
      this.resultUrl='/result'+this.result;
      this.goResult()
    },
    getGameInfo(appid) {
      axios
        .get(`${SERVER_URL}/game/info/search`, { params: { appid: appid } })
        .then((res) => {
          const gameData = res.data;
          if (gameData == null) {
            this.gameData =
              "https://images.unsplash.com/photo-1519400197429-404ae1a1e184?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=700&q=80";
          } else {
            this.gameData = gameData;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    goResult: function() {
      this.$router.push({ path: "/result/"+this.$store.state.result});
    },
    goDetail: function() {
      this.$router.push({ path: "/detail" , query: { appId: 550 } });
    },
  },
};
</script>
