<template>
  <section class="section-show">
    <div v-if="isLoading == true">
      <img src="../assets/img/loading-87.gif" style="opacity:0.5;" />
    </div>
    <div v-if="isLoading == false">
      <div
        class="container"
        style="background-color:#00000077; margin-bottom:50px; padding-top:10px;"
      >
        <div class="section-title">
          <h2>Game For You</h2>
          <p>게임 추천</p>
        </div>

        <div class="container">
          <div style="">
            <b-card title="Card Title" no-body>
              <b-card-header
                header-tag="nav"
                style="background-color:#00000077"
              >
                <b-nav card-header tabs>
                  <!-- <b-nav-item>'s with child routes. Note the trailing slash on the first <b-nav-item> -->
                  <b-nav-item
                    @click.prevent="loadPopular()"
                    exact
                    exact-active-class="active"
                    style=""
                    >인기 게임</b-nav-item
                  >
                  <b-nav-item
                    v-if="this.user.userid != ''"
                    @click.prevent="loadRecommendGame()"
                    exact
                    exact-active-class="active"
                    >게임 추천</b-nav-item
                  >
                  <b-nav-item
                    @click.prevent="loadSearchGame()"
                    exact
                    exact-active-class="active"
                    >게임 검색</b-nav-item
                  >
                  <!-- <b-nav-item @click.prevent="loadLikeGame()">관심 게임</b-nav-item> -->
                </b-nav>
              </b-card-header>
              <div justify-content style="background-color:#00000077">
                <div id="recommend-contents" style="background-color:#00000077">
                  <component :is="componentLoading()"></component>
                </div>
              </div>
              <div v-if="this.active == 2" style="min-height:400px;">
                <div style="margin-top:30px; background-color:#00000077">
                  <div class="section-title">
                    <h2 style="color:white">스팀 게임 검색하기</h2>
                  </div>
                  <div class="form-group">
                    <input
                      type="search"
                      class="form-control form-control-lg"
                      v-model="searchname"
                      id="searchname"
                      placeholder="게임 검색"
                      @keyup.13="searchData()"
                    />
                  </div>
                </div>
                <div class="row">
                  <div
                    class="col-md-4"
                    v-for="games in this.searchshowdata"
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
                    </b-card>
                  </div>
                </div>
              </div>
              <div class="row">
                <div
                  class="col-md-12 text-center"
                  style="text-align: center; padding-top:30px  "
                >
                  <a
                    class=""
                    href="javascript:void(0)"
                    style="font-size:30px; margin-rignt:20px;"
                    @click="changeShow(false)"
                    v-show="firstpage == false"
                  >
                    <i
                      class="bi bi-chevron-double-left"
                      style="color:rgb(51 255 0)"
                    ></i>
                  </a>
                  <a
                    class=""
                    href="javascript:void(0)"
                    style="font-size:30px; margin-left:30px;"
                    @click="changeShow(true)"
                    v-show="resultleft == true"
                  >
                    <i
                      class="bi bi-chevron-double-right"
                      style="color:rgb(51 255 0)"
                    ></i>
                  </a>
                </div>
              </div>
            </b-card>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Popular from './recommend/Popular';
import RecommendGame from './recommend/RecommendGame';

import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_API_SERVER_URL;

export default {
  data() {
    return {
      user: {
        token: '',
        nickname: '',
        steamid: '',
        userid: '',
      },
      active: 0,
      like: '',
      token: '',
      searchname: '',
      searchtotaldata: [{ appid: ' ', title: '', thumnail: '' }],
      searchshowdata: [{ appid: ' ', title: '', thumnail: '' }],
      startindex: 0,
      endindex: 0,
      resultleft: false,
      firstpage: true,
      isLoading: true,
    };
  },
  components: {
    Popular,
    RecommendGame,
  },
  created() {
    if (localStorage.getItem('token')) {
      this.user.token = localStorage.getItem('token');
      this.user.nickname = localStorage.getItem('nickname');
      this.user.steamid = localStorage.getItem('steamid');
      this.user.userid = localStorage.getItem('userid');
    }
    this.isLoading = false;
  },
  mounted() {},
  methods: {
    componentLoading() {
      switch (this.active) {
        case 0:
          return 'Popular';
        case 1:
          return 'RecommendGame';
        // case 2:
        //   return 'LikeGame';
      }
    },
    loadPopular() {
      this.active = 0;
    },
    loadRecommendGame() {
      this.active = 1;
    },
    loadSearchGame() {
      this.active = 2;
    },
    searchData: function() {
      this.isLoading = true;
      if (this.searchname.length < 2) {
        alert('최소 2글자 이상 검색이 가능합니다.');
        return;
      }
      this.startindex = 0;
      this.endindex = 0;
      this.firstpage = true;
      this.resultleft = false;
      axios
        .get(`${SERVER_URL}/game/search/name?word=` + this.searchname)
        .then((res) => {
          this.searchtotaldata = res.data.data;
          this.searchshowdata = '';
          if (this.searchtotaldata.length == 0) {
            alert('검색 결과가 없습니다.');
          } else {
            if (this.searchtotaldata.length >= this.startindex + 9) {
              this.endindex += 9;
              this.resultleft = true;
            } else this.endindex = this.searchtotaldata.length;

            for (var i = 0; i < this.endindex; i++) {
              this.searchshowdata = [
                ...this.searchshowdata,
                ...[
                  {
                    appid: this.searchtotaldata[i].appid,
                    title: this.searchtotaldata[i].name,
                    thumnail: this.searchtotaldata[i].imgsrc,
                  },
                ],
              ];
            }
          }
        })
        .catch((err) => {
          console.log(err);
        });
      this.isLoading = false;
    },
    changeShow: function(flag) {
      this.searchshowdata = [];
      if (flag) {
        if (this.searchtotaldata.length > this.endindex + 9) {
          this.endindex += 9;
          this.startindex += 9;
          this.resultleft = true;
        } else {
          this.startindex += 9;
          this.endindex = this.searchtotaldata.length;
          this.resultleft = false;
        }
        this.firstpage = false;
      } else {
        if (this.endindex == this.searchtotaldata.length) {
          var temp = this.searchtotaldata.length;
          if (temp == 0) temp = 9;
          this.endindex = this.startindex;
          this.startindex -= 9;
        } else {
          if (this.startindex - 9 >= 0) {
            this.endindex = this.startindex;
            this.startindex -= 9;
            if (this.startindex == 0) this.firstpage = true;
          }
        }
        this.resultleft = true;
      }
      for (var i = this.startindex; i < this.endindex; i++) {
        this.searchshowdata = [
          ...this.searchshowdata,
          ...[
            {
              appid: this.searchtotaldata[i].appid,
              title: this.searchtotaldata[i].name,
              thumnail: this.searchtotaldata[i].imgsrc,
            },
          ],
        ];
      }
    },
    goDetail: function(appid) {
      console.log(appid);
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
.profile_wrapper {
  float: left;
  width: 100px;
  height: 100px;
  margin: 0 15px 0 36px;
  position: relative;
}
.gradation_animate {
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100px;
  height: 100px;
  border-radius: 50%;
  background: url(https://k.kakaocdn.net/dn/BQFZF/btqzu7xZvy9/p3qATq2IbK4edXbUm76pTK/img.png)
    no-repeat;
  /*  background: url("../assets/img/colorGra.png") no-repeat; */
  animation: spin 1s linear infinite;
}
.image_wrapper {
  position: relative;
  overflow: hidden;
  width: 94px;
  height: 94px;
  border-radius: 50%;
  top: 3px;
  left: 3px;
}
.image {
  position: absolute;
  top: -100%;
  left: -100%;
  right: -100%;
  bottom: -100%;
  margin: auto;
  height: 92px;
  min-width: 100%;
  min-height: 100%;
}

nav.card-header {
  background-color: none;
}

a.nav-link {
  color: rgba(255, 255, 255, 0.945);
  border-color: none;
  border-width: 3px;
  border-bottom: none;
  border-radius: 12px 12px 0px 0px;
  background-color: rgba(88, 88, 88, 0.678);
}

a.nav-link:hover {
  color: white;
  border-color: white;
  border-width: 3px;
  border-bottom: none;
  border-radius: 12px 12px 0px 0px;
}

a.nav-link:focus {
  color: #35e888;
  border-color: #35e888;
  border-width: 3px;
  border-bottom: none;
  border-radius: 12px 12px 0px 0px;
}

div.card {
  background-color: #00000000;
}

div.card-body {
  background-color: rgba(255, 255, 255, 0.08);
  border-width: 15px;
  border-color: #35e888;
}

h2.section-title {
  line-height: 0px;
  margin: 0 0 10px 0;
}

div.profile-container {
  background-color: rgba(255, 255, 255, 0.08);
}

.section-show {
  margin-top: 40px;
}

.section-title {
  margin-top: 20px;
}
</style>
