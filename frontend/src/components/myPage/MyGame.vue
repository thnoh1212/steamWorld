<template>
  <div class="container" style="background-color:#00000077; margin-bottom:50px;">
    <div v-if="isLoading == true">
      <img src="../../assets/img/loading-87.gif" style="opacity:0.5;" />
    </div>
    <div v-if="isLoading == false">
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
</template>

<script>
import { mapState } from 'vuex';

const axios = require('axios');
const SERVER_URL = process.env.VUE_APP_API_SERVER_URL;
// const SERVER_URL = process.env.VUE_APP_LOCALHOST_URL;
export default {
  data() {
    return {
      user: {
        userid: '',
        nickname: '',
        steamid: '',
      },
      item: {
        itemid: 0,
        userid: '',
        appid: '',
        playtime_forever: 0,
        playtime_2weeks: 0,
        isSteam: 0,
      },
      itemList: [],
      gameData: [],
      isLoading: true,
    };
  },
  async created() {
    this.user.steamid = localStorage.getItem('steamid');
    this.user.nickname = localStorage.getItem('nickname');
    this.user.userid = localStorage.getItem('userid');
    await this.getItem();
    await this.getGame();
    this.isLoading = false;
  },
  computed: {
    ...mapState(['loginStatus']),
  },
  methods: {
    async getItem() {
      await axios
        .get(`${SERVER_URL}/item/list`, { params: { userid: this.user.userid, issteam: '1' } })
        .then((res) => {
          this.itemList = res.data.data;
          // console.log("itemList @@: ",this.itemList)
        })
        .catch((res) => {
          console.log('err : ' + res);
        });
    },
    async getGame() {
      // console.log("itemList @@: ",this.itemList)
      for (var app of this.itemList) {
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
    },
    goDetail: function(appid) {
      // console.log(appid);
      this.$router.push({ path: '/detail', query: { appId: appid } });
    },
  },
};
</script>
