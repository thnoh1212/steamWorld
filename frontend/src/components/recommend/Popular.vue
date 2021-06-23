<template>
  <div class="section-show" style="background-color:#00000077">
    <div v-if="isLoading == true">
      <img src="../../assets/img/loading-87.gif" style="opacity:0.5;" />
    </div>
    <div v-if="isLoading == false">
      <div class="row">
        <div
          class="col-md-4"
          v-for="games in this.gamedata"
          :key="games.title"
          style="margin-bottom: 20px"
        >
          <b-card
            v-if="games.title != ''"
            :title="games.title"
            :img-src="games.thumnail"
            img-alt="Image"
            img-top
            tag="article"
            style="width: 100%; text-align:center; background-image: url('../../src/assets/img/gameboy.png'); background-color:#00000077; height: 100%"
            @click="goDetail(games.appid)"
          >
            <div
              v-if="isLogin"
              style="position: static; right: 0px; bottom:0px; padding-bottom: 20px; padding-right: 20px; text-align: right;"
            ></div>
          </b-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
// import { api_url } from '../main';
const SERVER_URL = process.env.VUE_APP_API_SERVER_URL;

export default {
  components: {},
  data() {
    return {
      gamedata: [{ appid: '', title: '', thumnail: '' }],
      select: (el, all = false) => {
        el = el.trim();
        if (all) {
          return [...document.querySelectorAll(el)];
        } else {
          return document.querySelector(el);
        }
      },
      like: '',
      user: {
        token: '',
        nickname: '',
        steamid: '',
        userid: '',
      },
      isLoading: true,
    };
  },
  created() {
    if (localStorage.getItem('token')) {
      this.token = localStorage.getItem('token');
      this.nickname = localStorage.getItem('nickname');
      this.steamid = localStorage.getItem('steamid');
      this.userid = localStorage.getItem('userid');
    }
  },
  mounted() {
    axios
      .get(
        `${SERVER_URL}/game/popular?size=12&start=0` //top10 고정값으로 받아옴
        // `/a105/game/popular?size=${this.gamelist.size}&start=${this.gamelist.start}`
      )
      .then((res) => {
        var i = 0;
        for (i = 0; i < 12; i++) {
          // this.gamedata.title = res.data.data[i].name;
          // this.gamedata.thumnail = res.data.data[i].imgsrc;
          this.gamedata = [
            ...this.gamedata,
            ...[
              {
                appid: res.data.data[i].appid,
                title: res.data.data[i].name,
                thumnail: res.data.data[i].imgsrc,
              },
            ],
          ];
        }
        this.gamedata.splice(0, 1);
        // console.log(this.gamedata);
      })
      .catch((err) => {
        console.log(err);
      });

    this.isLoading = false;
  },
  methods: {
    isLogin: function() {
      if (!this.token) {
        alert('로그인 후 이용 가능합니다!');
      } else {
        console.log('로그인중');
      }
    },
    LikeHandler: function(name) {
      if (this.like == 0) {
        this.like = 1;
        // var game = this.select('.col-md-4');
        // console.log(name);
      } else {
        this.like = 0;
      }
    },
    goDetail: function(appid) {
      // console.log(appid);
      this.$router.push({ path: '/detail', query: { appId: appid } });
    },
  },
};
</script>

<!--  <script src="../assets/js/main.js"></script>-->
<style scoped>
.card-body {
  padding: 0px;
}
/* card {
  margin-left: 20px;
  margin-right: 20px;
  border-bottom-color: red;
  background-color: #000000;
} */

.modal-content h1 {
  color: #000000;
}

.modal-content h2 {
  color: #000000;
}
</style>
