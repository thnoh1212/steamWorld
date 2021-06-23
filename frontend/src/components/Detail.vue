<template>
  <section class="section-show">
    <div v-if="isLoading == true">
      <img src="../assets/img/loading-87.gif" style="opacity:0.5;" />
    </div>
    <div v-if="isLoading == false">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="section-title">
              <h2>Game Detail</h2>
              <p>게임 상세</p>
            </div>
            <!-- <h1>{{$route.query.appId}}</h1> -->
            <h1 style="margin-left:15px;">{{ gameInfo.data.game.name }}</h1>
          </div>

          <div class="col-md-8">
            <img :src="headerUrl" style="width:100%" />
          </div>

          <div class="col-md-4 col-sm-12">
            <span>tags : </span>
            <span style="margin-right:10px;" v-for="(genre, idx) in genreList" :key="idx">
              <b-badge
                style="height:30px; font-size:1rem; font-weight:normal; border-radius:10px;"
                variant="primary"
                >{{ genre.genre }}</b-badge
              ></span
            >
            <table style="width:100%; ">
              <tr>
                <td><h4>제작사:</h4></td>
                <td style="text-align:right; width:60%">
                  <h5>{{ gameInfo.data.game.dev }}</h5>
                </td>
              </tr>
              <tr></tr>
              <tr>
                <td><h4>평가:</h4></td>
                <td style="text-align:right; width:60%">
                  <h5>{{ gameInfo.data.game.score }} 점</h5>
                </td>
              </tr>
            </table>

            <!--
              <i class="bi bi-star"></i>
              <i class="bi bi-star-fill"></i>
              -->
            <button
              v-if="this.userid"
              type="button"
              class="btn btn-secondary"
              style="width:100%; height: 50px; margin-bottom:20px;"
              @click="LikeHandler()"
            >
              <i class="bi bi-star" style="color:yellow; font-size: 1.3rem" v-if="like == 0"></i>
              <i
                class="bi bi-star-fill"
                style="color:yellow; font-size: 1.3rem"
                v-if="like == 1"
              ></i>
              <!--컬러 회색 #6c757d하고 노란색 #ffc107-->
              관심 게임 추가하기
            </button>
            <button
              v-else-if="this.issteam == 1"
              type="button"
              class="btn btn-secondary"
              style="width:100%; height: 50px; margin-bottom:20px;"
              @click="LikeHandlerHave()"
            >
              <i class="bi bi-star" style="color:yellow; font-size: 1.3rem" v-if="like == 0"></i>
              <i
                class="bi bi-star-fill"
                style="color:yellow; font-size: 1.3rem"
                v-if="like == 1"
              ></i>
              <!--컬러 회색 #6c757d하고 노란색 #ffc107-->
              관심 게임 추가하기
            </button>
            <button
              v-else
              type="button"
              class="btn btn-secondary"
              style="width:100%; height: 50px; margin-bottom:20px;"
              @click="LikeHandlerNo()"
            >
              <i class="bi bi-star" style="color:yellow; font-size: 1.3rem" v-if="like == 0"></i>
              <i
                class="bi bi-star-fill"
                style="color:yellow; font-size: 1.3rem"
                v-if="like == 1"
              ></i>
              <!--컬러 회색 #6c757d하고 노란색 #ffc107-->
              관심 게임 추가하기
            </button>

            <a :href="gameInfo.data.game.storelink" target="_blank"
              ><button style="width:100%; height: 50px;" type="button" class="btn btn-primary">
                <img src="../assets/img/steam.svg" style="width:25px" />
                스팀 상점 바로가기
              </button></a
            >
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 section-title">
            <h2>게임 간단 설명</h2>
            <h5>{{ gameInfo.data.game.shortDes }}</h5>
          </div>
          <div class="row"></div>

          <div class="col-md-12 section-title">
            <h2>관련 영상</h2>
            <video :src="videoUrl" controls/>
            <!--
          <div class="col-12 col-sm-6 col-md-4 col-lg-4"
           v-for="(gamelink, idx) in gameData.data.gamelinks"
          :key="idx"
        ><video :src="gamelink.url" controls />
        <img :src="gamelink.url"/>
        
        </div>
   -->
          </div>
        </div>

        <div class="row">
          <div class="col-md-12 section-title">
            <h2>게임 상세 설명</h2>
            <div v-html="this.gameDes" style="width:100%; padding: 5px;"></div>
          </div>
        </div>
        <div style="text-align: center; padding: 25px;">
          <button class="col-md-10" style="background-color:black; color:#18d26e;" @click="getSimilar()">
            {{ gameInfo.data.game.name }}와 유사한 게임을 확인해보세요
          </button>
        </div>
        <div class="row">
          <div
            class="col-md-4"
            v-for="games in this.gamedatas"
            :key="games.title"
            style="margin-bottom: 20px"
          >
            <b-card
              v-if="games.title != ''"
              :title="games.title"
              :img-src="games.imgsrc"
              img-alt="Image"
              @click="goDetail(games.appid)"
              img-top
              tag="article"
          style="width: 100%; margin-left:10px; margin-right:10px; padding:0 0 0 0; text-align:center;  background-color:#00000077; height: 100%"
          >
            </b-card>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import axios from 'axios';
import { SERVER_URL, LOCALHOST_URL } from '../main.js';
// const SERVER_URL = process.env.VUE_APP_API_SERVER_URL;
const REC_SERVER_URL = process.env.VUE_APP_REC_SERVER_URL;

export default {
  data() {
    return {
      result: '',
      headerUrl: '',
      linkUrls: [],
      videoUrl: '',
      gameDes: '',
      genreList: [],
      gameInfo: null,
      appId: 0,
      like: '',
      userid: '',
      itemlist: [],
      issteam: '',
      isLoading: true,
      gamedatas:[],
    };
  },
  created() {
    this.userid = localStorage.getItem('userid');
    const appId = this.$route.query.appId;
    this.getGameInfo(appId);
    var img = document.getElementById('headerImg');

    // console.log(this.headerUrl);
    document.getElementById('headerImg').innerHTML = '<img src=' + this.headerUrl + '/>';
    img.src = this.headerUrl;
  },
  mounted() {
    this.isLoading = false;
    axios
      .get(
        `${SERVER_URL}/item/search?appid=${this.$route.query.appId}&userid=${this.userid}`,
        // `${SERVER_URL}/item/search?appid=${this.$route.query.appId}&userid=${this.userid}`,
        {
          appid: this.$route.query.appId,
          userid: this.userid,
        }
      )
      .then((res) => {
        // console.log(res.data.data);
        if (res.data.success == 'success') {
          this.like = 1;
        } else {
          this.like = 0;
        }
      });
  },
  methods: {
    getGameInfo(appid) {
      axios
        .get(`${SERVER_URL}/game/info/search`, { params: { appid: appid } })
        .then((res) => {
          const gameData = res.data;

          this.gameData = gameData;
          this.gameInfo = gameData;
          this.headerUrl =
            'https://cdn.cloudflare.steamstatic.com/steam/apps/' +
            gameData.data.appid +
            '/header.jpg';

          // console.log(gameData.data);
          // console.log(this.headerUrl);

          this.linkUrls = gameData.data.gamelinks;
          // console.log(this.linkUrls[0].url);

          // for(gameData.data.gamelinks)
          const gameDes = '';

          this.videoUrl = this.linkUrls[0].url;
          this.gameDes = this.gameData.data.game.fullDes;

          for (var i = 0; i < 3; ++i) {
            this.genreList.push(this.gameData.data.gametags[i]);
          }
          // console.log(this.genreList);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    LikeHandler: async function() {
      if (this.like == 0) {
        // 관심리스트에 게임 추가
        this.itemlist.push({
          appid: this.$route.query.appId,
          issteam: '0',
          itemid: 0,
          playtime_2weeks: 0,
          playtime_forever: 0,
          userid: this.userid,
        });
        // console.log(this.itemlist);
        await axios
          .post(`${SERVER_URL}/item/add`, this.itemlist)
          // .post(`${LOCALHOST_URL}/item/add`, this.itemlist)
          .then((res) => {
            if (res.data.success == 'success') {
              alert('관심목록에 추가되었습니다.');
              this.like = 1;
            }
          })
          .catch((err) => {
            alert('관심목록 추가에 실패했습니다.');
            this.like = 0;
            console.log(err);
          });
      } else {
        // 관심 게임일 때
        axios
          .get(
            `${SERVER_URL}/item/search?appid=${this.$route.query.appId}&userid=${this.userid}`,
            {
              appid: this.$route.query.appId,
              userid: this.userid,
            }
          )
          .then((res) => {
            // console.log('res');
            // console.log(res);
            // console.log(res.data.issteam);
            if (res.data.data.issteam == '1') {
              alert('이미 보유한 게임입니다!');
            } else {
              var userinfo = {
                headers: {
                  userid: this.userid,
                  appid: this.$route.query.appId,
                },
              };
              axios
                .delete(`${SERVER_URL}/item`, userinfo)
                // .delete(`${LOCALHOST_URL}/item`, userinfo)
                .then(() => {
                  alert('관심목록에서 삭제되었습니다.');
                  this.like = 0;
                })
                .catch((err) => {
                  alert('관심목록 삭제가 실패했습니다.');
                  this.like = 1;
                  console.log(err);
                });
            }
          });
      }
    },
    LikeHandlerNo: function() {
      alert('로그인 후 이용 가능합니다!');
    },
    LikeHandlerHave: function() {
      alert('이미 보유한 게임입니다!');
    },
    getSimilar: function(){
      this.gamedatas=[];
       axios.get(`${REC_SERVER_URL}/cbf/`+ this.$route.query.appId)
      .then((res) => {
        var i = 0;
        for (i = 0; i < 9; i++) {
          // this.gamedata.title = res.data.data[i].name;
          // this.gamedata.thumnail = res.data.data[i].imgsrc;
          this.gamedatas = [
            ...this.gamedatas,
            ...[
              {
                title: res.data.data[i].name,
                appid: res.data.data[i].appid,
                imgsrc: "https://cdn.cloudflare.steamstatic.com/steam/apps/"+res.data.data[i].appid+"/header.jpg"
              },
            ],
          ];
        }
        // console.log(this.gamedata);
      })
      .catch((err) => {
        console.log(err);
      });
    },
     goDetail: function(appid) {
      this.$router.push({ path: "/detail", query: { appId: appid } });
      this.$router.go()
    },
  },
};
</script>

<style scoped>
.section-title {
  padding-top: 20px;
}

h2 {
  color: white;
  font-weight: bold;
}

.game_area_description {
  color: white;
}

#text {
  color: white;
}

body {
  color: white !important;
}

.game_area_description #game_area_description {
  color: white;
}

.bb_tag {
  margin-top: 30px;
}
* >>> img,
video {
  width: 100%;
}
* >>> {
  color: white;
}
</style>
