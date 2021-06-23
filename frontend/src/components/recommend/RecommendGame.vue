<template>
  <div class="section-show">
    <div v-if="isLoading == true">
      <img src="../../assets/img/loading-87.gif" style="opacity:0.5;" />
    </div>
    <div v-if="isLoading == false">
      <div class="container">
        <div style="margin-top: 30px; margin-bottom:50px;">
          <h5>
            스팀 보유 게임과 관심 게임을 기반으로 새로운 게임을 추천합니다.
          </h5>
        </div>
        <b-button @click.prevent="recommendWish()">
          추천 시작!
        </b-button>
      </div>

      <div v-if="this.resultShow == true">
        <div class="container">
          <div style="margin-top: 30px">
            <h3>추천 결과</h3>
          </div>
        </div>
        <div style="background-color: none">
          <div id="row">
            <div class="items">
              <div class="row">
                <div
                  class="col-md-4"
                  v-for="games in this.gameData"
                  :key="games.name"
                  style="margin-bottom: 20px"
                >
                  <b-card
                    v-if="games.name != ''"
                    :title="games.name"
                    :img-src="games.imgsrc"
                    img-alt="Image"
                    img-top
                    tag="article"
                    style="width: 100%; text-align:center; background-image: url('../../src/assets/img/gameboy.png');background-color:#00000077; height: 100%"
                    @click="goDetail(games.appid)"
                  >
                    <div style="padding-bottom: 20px">
                      <!-- <b-card-text>
            </b-card-text> -->

                      <!--  <b-button href="#" variant="primary">상세보기</b-button> -->
                    </div>
                  </b-card>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div v-if="this.gameList.length != 0">
        <div class="container">
          <div style="margin-top: 30px">
            <h3>보유 게임</h3>
          </div>
        </div>
        <div style="background-color: none">
          <div id="scrollRow">
            <div class="items">
              <my-game></my-game>
            </div>
          </div>
        </div>
      </div>

      <div v-if="this.itemList.length != 0">
        <div class="container">
          <div style="margin-top: 30px">
            <h3>관심 게임</h3>
          </div>
        </div>
        <div style="background-color: none">
          <div id="scrollRow">
            <div class="items">
              <like-game></like-game>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import MyGame from '../myPage/MyGame';
import LikeGame from '../myPage/LikeGame';

const axios = require('axios');
const SERVER_URL = process.env.VUE_APP_API_SERVER_URL;
const REC_SERVER_URL = process.env.VUE_APP_REC_SERVER_URL;

export default {
  components: {
    MyGame,
    LikeGame,
  },
  data() {
    return {
      inputList: {},
      appids: [],
      resultids: [],
      user: {
        userid: '',
        nickname: '',
        steamid: 0,
      },
      resultList: [],
      itemList: [],
      gameList: [],
      gameData: [],
      resultData: [],
      resultShow: false,
      isLoading: true,
    };
  },
  async created() {
    if (localStorage.getItem('token')) {
      this.user.token = localStorage.getItem('token');
      this.user.nickname = localStorage.getItem('nickname');
      this.user.steamid = localStorage.getItem('steamid');
      this.user.userid = localStorage.getItem('userid');
      await this.getResult();
      if (this.resultList.length != 0) {
        await this.getGame();
        console.log('resultList :', this.resultList);
      }
      await this.getItem();
      await this.getMyGame();
      this.isLoading = false;
    }
  },
  methods: {
    async recommendWish() {
      alert('추천 결과가 나올 때 까지 잠시 기다려주세요!');
      this.isLoading = true;

      for (var item of this.itemList) {
        this.appids.push(item.appid);
      }
      var sid = this.user.steamid;
      if (this.gameList.length == 0) {
        sid = 0;
      }

      this.inputList = {
        appids: this.appids,
        steamid: sid,
      };
      console.log('inputlist : ', this.inputList);
      if (this.appids.length != 0) {
        await axios
          .post(`${REC_SERVER_URL}/wish/`, this.inputList)
          .then((res) => {
            console.log('wish res: ', res);
            this.resultList = res.data.data;
          })
          .catch((res) => {
            alert('error : ' + res);
          });
      } else if (sid != 0) {
        await axios
          .get(`${REC_SERVER_URL}/cf/` + sid)
          .then((res) => {
            this.resultList = res.data.data;
            console.log('cf res: ', res.data.data);
          })
          .catch((res) => {
            alert('error : ' + res);
          });
      } else {
        alert('스팀 연동 혹은 관심 게임을 추가해주세요');
      }
      this.gameData = [];
      this.resultData = [];
      await this.getGame();
      console.log('gd: ', this.gameData);
      for (var game of this.gameData) {
        this.resultData.push({
          appid: game.appid,
          name: game.name,
          score: game.score,
          vote: game.vote,
          storelink: game.storelink,
          imgsrc: game.imgsrc,
          shortDes: game.shortDes,
          dev: game.dev,
          fullDes: game.fullDes,
          userid: this.user.userid,
        });
      }
      console.log('rd: ', this.resultData);
      alert('추천이 완료되었습니다!');
      this.isLoading = false;
      this.delResult();
      this.addResult();
    },
    async addResult() {
      await axios
        .post(`${SERVER_URL}/result/add`, this.resultData)
        .then((res) => {
          if (res.data.success == 'fail') {
            console.log('err : ' + res);
          }
        })
        .catch((res) => {
          console.log('err : ' + res);
        });
    },
    async getResult() {
      await axios
        .get(`${SERVER_URL}/result/list`, {
          params: {
            userid: this.user.userid,
          },
        })
        .then((res) => {
          this.resultList = res.data.data;
        })
        .catch((res) => {
          console.log('err : ' + res);
        });
    },
    async getMyGame() {
      console.log('userid : ', this.user.userid);
      await axios
        .get(`${SERVER_URL}/item/list`, {
          params: { userid: this.user.userid, issteam: '1' },
        })
        .then((res) => {
          console.log('res :', res);
          this.gameList = res.data.data;
          console.log('itemList @@: ', this.gameList);
        })
        .catch((res) => {
          console.log('err : ' + res);
        });
    },
    async getItem() {
      console.log('userid : ', this.user.userid);
      await axios
        .get(`${SERVER_URL}/item/list`, {
          params: { userid: this.user.userid, issteam: '0' },
        })
        .then((res) => {
          console.log('res :', res);
          this.itemList = res.data.data;
          console.log('itemList @@: ', this.itemList);
        })
        .catch((res) => {
          console.log('err : ' + res);
        });
    },
    async getGame() {
      //   console.log("itemList @@: ",this.itemList)
      for (var app of this.resultList) {
        await axios
          .get(`${SERVER_URL}/game/search`, { params: { appid: app.appid } })
          .then((res) => {
            if (res.data.data != null) {
              // console.log("data : ", res.data.data);
              this.gameData.push(res.data.data);
            }
          })
          .catch((res) => {
            console.log('err : ' + res);
          });
      }
      this.resultShow = true;
      console.log('getGame : ', this.gameData);
    },
    goDetail: function(appid) {
      console.log(appid);
      this.$router.push({ path: '/detail', query: { appId: appid } });
    },
    async delResult() {
      await axios
        .delete(`${SERVER_URL}/result/delete`, {
          headers: {
            userid: this.user.userid,
          },
        })
        .then((res) => {
          console.log('err : ' + res);
        })
        .catch((res) => {
          console.log('err : ' + res);
        });
    },
  },
};
</script>
<style scoped>
body {
  margin: 50px auto;
  width: 600px;
}
#scrollRow {
  /* white-space: nowrap;  */
  /* ?가로스크롤시 중요한 속성  */
  overflow-x: auto;
  overflow-y: hidden;
  /* padding: 10px 10px 5px; */
  /* background: #f7be98; */
  /* width: 600px; */
}

/* #row .items {
  display: inline-block;
  margin-left: 10px;
  width: 1100px;
  max-width: 100%;
} */

/* #row .items:first-child {
  margin-left: 0;
} */

/* #row .items p {
  margin-bottom: 8px;
  text-indent: 7px;
} */

#scrollRow .items div {
  height: 500px;
  overflow-y: scroll;
  /* text-align: left; */
}
</style>
