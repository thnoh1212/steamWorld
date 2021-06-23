<template>
  <section class="section-show">
    <div class="row" style="width: 100%">
      <div v-if="isLoading == true">
      <img src="../assets/img/loading-87.gif" style="opacity:0.5;" />
    </div>
    <div v-if="isLoading == false">
      <div class="col-md-1"></div>
      <div class="col-md-10 box" style="background-color: #00000077">
        <div class="section-title">
          <h2>마이페이지</h2>
          <p>My Page</p>

          <h1 class="typing-txt" style="margin-bottom: 100px">
            {{ user.nickname }} 님의 마이페이지
          </h1>

          <br />

          <div class="profile-row row">
            <div></div>
            <div class="col-md-6">
              <div
              >
              <h5>스팀 프로필, 게임 세부 정부 공개 후 스팀 로그인을 해주세요!</h5>
                <img
                  src="../assets/img/poster.png"
                  style="display: block; max-width: 100%; min-width: 100%"
                />
              </div>
            </div>
            <div class="col-md-6" style="">
              <div class="section-title">
                <h2>닉네임</h2>
              </div>
              <h3>{{ user.nickname }}</h3>
              <div class="section-title">
                <h2>아이디(이메일)</h2>
              </div>
              <h3>{{ user.userid }}</h3>
              <div class="section-title">
                <h2>스팀 아이디</h2>
              </div>
              <h3 v-if="user.steamid != '0'">{{ user.steamid }}</h3>
              <form action="https://steamcommunity.com/openid/login" method="post">
                <input
                  type="hidden"
                  name="openid.identity"
                  value="http://specs.openid.net/auth/2.0/identifier_select"
                />
                <input
                  type="hidden"
                  name="openid.claimed_id"
                  value="http://specs.openid.net/auth/2.0/identifier_select"
                />
                <input type="hidden" name="openid.ns" value="http://specs.openid.net/auth/2.0" />
                <input type="hidden" name="openid.mode" value="checkid_setup" />
                <!-- <input type="hidden" name="openid.realm" value="http://localhost:8081" />
            <input type="hidden" name="openid.return_to" value="http://localhost:8081/mypage" /> -->
                <!-- server side -->
                <input type="hidden" name="openid.realm" value="http://j4a105.p.ssafy.io" />
                <input
                  type="hidden"
                  name="openid.return_to"
                  value="http://j4a105.p.ssafy.io/mypage"
                />
                <b-button type="submit btn-large" style="border-radius: 10rem"
                  ><img src="../assets/img/steam.svg" class="steamlogo" />스팀 로그인</b-button
                >
              </form>
            </div>
          </div>

          <div style="background-color: none">
            <b-card title="Card Title" no-body>
              <b-card-header header-tag="nav" style="background-color: none">
                <b-nav card-header tabs>
                  <b-nav-item
                    @click.prevent="loadUpdate()"
                    exact
                    exact-active-class="active"
                    style=""
                    >프로필 상세</b-nav-item
                  >
                  <b-nav-item @click.prevent="loadMyGame()" exact exact-active-class="active"
                    >보유 게임</b-nav-item
                  >
                  <b-nav-item @click.prevent="loadLikeGame()">관심 게임</b-nav-item>
                </b-nav>
              </b-card-header>

              <b-card-body justify-content style="background-color: none">
                <div id="mypage-contents">
                  <component :is="componentLoading()"></component>
                </div>
              </b-card-body>
            </b-card>
          </div>
        </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script src="../assets/js/crawl.js"></script>
<script>
// axios를 활용해 AJAX로 HTML 문서를 가져오는 함수 구현
async function getHTML() {
  try {
    return await axios.get('https://chaewonkong.github.io');
  } catch (error) {
    console.error(error);
  }
}

// getHTML 함수 실행 후 데이터에서
// body > main > div > section > ul > li > article > h2 > a
// 에 속하는 제목을 titleList에 저장
getHTML()
  .then((html) => {
    let titleList = [];
    const $ = cheerio.load(html.data);
    // ul.list--posts를 찾고 그 children 노드를 bodyList에 저장
    const bodyList = $('ul.list--posts').children('li.item--post');

    // bodyList를 순회하며 titleList에 h2 > a의 내용을 저장
    bodyList.each(function(i, elem) {
      titleList[i] = {
        title: $(this)
          .find('h2 a')
          .text(),
      };
    });
    return titleList;
  })
  .then((res) => console.log(res)); // 저장된 결과를 출력
</script>
<script>
import UpdateUser from './myPage/UpdateUser';
import MyGame from './myPage/MyGame';
import LikeGame from './myPage/LikeGame';
// import { SERVER_URL } from '../main';
import { mapState } from 'vuex';

const axios = require('axios');
const cheerio = require('cheerio');
const SERVER_URL = process.env.VUE_APP_API_SERVER_URL;
const REC_SERVER_URL = process.env.VUE_APP_REC_SERVER_URL;
// const SERVER_URL = process.env.VUE_APP_LOCALHOST_URL;
export default {
  data() {
    return {
      user: {
        userid: '',
        nickname: '',
        steamid: '',
      },
      sid: '',
      active: 0,
      slide: 0,
      sliding: null,
      itemOut: {
        itemid: 0,
        userid: '',
        appid: '',
        playtime_forever: 0,
        playtime_2weeks: 0,
        isSteam: 0,
      },
      itemOutList: [],
      
      isLoading: true,
    };
  },
  components: {
    MyGame,
    LikeGame,
    UpdateUser,
  },
  computed: {
    ...mapState(['loginStatus']),
  },
  async created() {
    this.user.steamid = localStorage.getItem('steamid');
    this.user.nickname = localStorage.getItem('nickname');
    this.user.userid = localStorage.getItem('userid');
    // console.log('steamid: ', this.user.steamid);
    if (!this.user.steamid || this.user.steamid == 0) {
      await this.getUrl();
      // console.log('localstorage steamid : ', localStorage.getItem('steamid'));
      this.user.steamid = await localStorage.getItem('steamid');
    } else {
      if (this.itemOutList.length == 0) {
        await this.getUrl();
      }
    }
    
    this.isLoading = false;
  },
  async mounted() {
    // console.log('mounted steamid : ', localStorage.getItem('steamid'));
    this.user.steamid = localStorage.getItem('steamid');
  },
  methods: {
    onSlideStart(slide) {
      this.sliding = true;
    },
    onSlideEnd(slide) {
      this.sliding = false;
    },
    async getUrl() {
      if (!localStorage.getItem('steamid') || localStorage.getItem('steamid') == '0') {
        var link = document.location.href.split('&');
        // console.log(link[3]);
        if (link[3]) {
          this.user.steamid = link[3].slice(67, link[3].length);
          localStorage.setItem('steamid', this.user.steamid);
          // console.log('this.user.steamid : ', this.user.steamid);
          // console.log('update steamid start');
          await this.updateSteamid();
          await this.getItem();
          // console.log(this.itemOutList);
          // window.location.reload();
        }
      }
      // console.log('getUrl.steamid : ', localStorage.getItem('steamid'));
    },
    getUserInfo() {
      axios
        .get(`${SERVER_URL}/user`, {
          headers: {
            'x-access-token': localStorage.getItem('token'),
          },
          params: {
            userid: this.loginStatus.userid,
          },
        })
        .then((res) => {
          if (res.data.success == 'fail') {
            alert('유저 정보를 불러오는데 실패 했습니다.');
          } else {
            this.user.userid = res.data.data.userid;
            this.user.nickname = res.data.data.nickname;
            this.user.steamid = res.data.data.steamid;
          }
        })
        .catch((res) => {
          alert('error : ' + res);
        });
    },
    async updateSteamid() {
      this.form = {
        userid: this.user.userid,
        steamid: this.user.steamid,
      };
      // console.log('updateSteamid');
      await axios
        .post(`${SERVER_URL}/user/steam`, this.form, {
          headers: {
            'x-access-token': localStorage.getItem('token'),
          },
        })
        .then((res) => {
          if (res.data.success == 'fail') {
            alert('steamid 연동에 실패했습니다.');
          } else {
            localStorage.setItem('steamid', this.form.steamid);
          }
        })
        .catch((res) => {
          alert('error : ' + res);
        });
    },
    async getItem() {
      await axios.get(`${REC_SERVER_URL}/steamid/` + this.user.steamid).then((res) => {
        // console.log('response', res);
        // console.log("response", res.data.data.response);
        for (var app of res.data.data) {
          this.itemOutList.push({
            itemid: 0,
            userid: this.user.userid,
            appid: app.appid,
            playtime_forever: app.playtime_forever,
            playtime_2weeks: app.playtime_2weeks,
            issteam: '1',
          });
        }
        // console.log(this.itemOutList);
      });
      await this.additem();
    },
    async additem() {
      
    this.isLoading = true;
      // console.log('outList: ', this.itemOutList);
      await axios
        .post(`${SERVER_URL}/item/add`, this.itemOutList)
        .then((res) => {
          if (res.data.success == 'fail') {
            alert('스팀에 게임이 없습니다.');
          }
        })
        .catch((res) => {
          alert('error : ', res);
        });
        
    this.isLoading = false;
    },
    async updateItem() {},
    componentLoading() {
      switch (this.active) {
        case 0:
          return 'UpdateUser';
        case 1:
          return 'MyGame';
        case 2:
          return 'LikeGame';
      }
    },

    loadUpdate() {
      this.active = 0;
    },
    loadMyGame() {
      this.active = 1;
    },
    loadLikeGame() {
      this.active = 2;
    },
    getHTML(url) {
      var optionAxios = {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,OPTIONS',
          'Access-Control-Allow-Headers':
            'Content-Type, Authorization, Content-Length, X-Requested-With',
        },
      };
      try {
        // console.log(axios.get('https://cors-anywhere.herokuapp.com/' + url, optionAxios));
        return axios
          .get('https://cors-anywhere.herokuapp.com/' + url, optionAxios)
          .then((html) => {
            let titleList = [];
            const $ = cheerio.load(html.data);
            // ul.list--posts를 찾고 그 children 노드를 bodyList에 저장
            const bodyList = $('ul.list--posts').children('li.item--post');
            // console.log(bodyList);

            // bodyList를 순회하며 titleList에 h2 > a의 내용을 저장
            bodyList.each(function(i, elem) {
              titleList[i] = {
                title: $(this)
                  .find('h2 a')
                  .text(),
              };
            });
            return titleList;
          })
          .then((res) => console.log(res));
      } catch (error) {
        console.error(error);
      }
    },
    test(url) {
      var optionAxios = {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,OPTIONS',
          'Access-Control-Allow-Headers':
            'Content-Type, Authorization, Content-Length, X-Requested-With',
        },
      };
      axios
        .get('https://cors-anywhere.herokuapp.com/' + url, optionAxios)
        .then((response) => {
          var htmlText = response.data;

          let titleList = [];
          const $ = cheerio.load(htmlText);

          const bodyList = $('div.game_description_snippet');
          // console.log('1' + bodyList);

          // bodyList를 순회하며 titleList에 h2 > a의 내용을 저장
          bodyList.each(function(i, elem) {
            titleList[i] = {
              title: $(this)
                .find('div')
                .text(),
            };
          });
          return titleList;
        })
        .then((titleList) => console.log(titleList));
    },
  },
};
</script>

<style scoped>
.typing-txt {
  position: relative;
  display: inline-block;
  height: 40px;
  overflow: hidden;
  animation: typingAni 1s steps(15, end) 1;
}

.typing-txt::after {
  position: absolute;
  display: block;
  content: '';
  width: 5px;
  height: 30px;
  top: 5px;
  right: 0px;
  border-right: 3px solid rgb(252, 252, 252);
  animation: cursor 0.5s step-end infinite;
}
@keyframes typingAni {
  0% {
    width: 0%;
  }
  100% {
    width: 30%;
  }
}
@keyframes cursor {
  0% {
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
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
