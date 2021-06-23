<template>
  <div>
    <transition>
      <header id="header">
        <div class="container">
          <a>
             <router-link to="/" class="nav-link active" href="#header">
              
          <h1>Steam World!</h1>
          </router-link>
          </a>
          <h1></h1>
          <h2>세상에서 가장 <span>편리한</span> 게임 추천 서비스</h2>
          <nav id="navbarMain" class="navbarMain">
            <i class="bi bi-list mobile-nav-toggle" @click="mobileHandler()"></i>
            <ul>
              <li @click="clickHandler()">
                <router-link to="/survey" class="nav-link" href="#about" id="menu-game"
                  >내게 맞는 게임 찾기</router-link
                >
              </li>
              <li @click="clickHandler()">
                <router-link to="/recommend" class="nav-link" href="#portfolio" id="menu-recomm"
                  >게임 추천</router-link
                >
              </li>
              <div class="menulogin" v-if="!this.token">
                <li @click="clickHandler()">
                  <router-link to="/user/login" class="nav-link" href="#services" id="menu-login"
                    >로그인</router-link
                  >
                </li>
                <li @click="clickHandler2()">
                  <router-link to="/user/signup" class="nav-link" href="#signup" id="menu-signup"
                    >회원가입</router-link
                  >
                </li>
              </div>
              <div class="menulogin" v-else>
                <li @click="clickHandler()">
                  <router-link to="/mypage" class="nav-link" href="#services" id="menu-mypage"
                    >마이 페이지</router-link
                  >
                </li>
                <!-- <li>
                  <a class="nav-link">{{ this.nickname }} 님 환영합니다.</a>
                </li> -->
                <li>
                  <a @click="logoutHandler" class="nav-link">로그아웃</a>
                </li>
              </div>
            </ul>

            <div class="social-links"></div>
          </nav>
          <!-- .navbar -->
          <!-- 
          <div class="social-links">
            <a href="#" class="twitter"><b-icon-twitter></b-icon-twitter></a>

            <a href="#" class="facebook"
              ><img src="../assets/img/steam.svg"
            /></a>
            <a href="#" class="instagram"><i class="bi bi-instagram"></i></a>
            <a href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>
          </div> -->
        </div>
      </header>
      <!-- End Banner -->
    </transition>

    <transition name="slide" mode="out-in">
      <router-view></router-view>
    </transition>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import { mapState } from 'vuex';
export default {
  name: 'SwBanner',
  mounted() {
    const select = (el, all = false) => {
      el = el.trim();
      if (all) {
        return [...document.querySelectorAll(el)];
      } else {
        return document.querySelector(el);
      }
    };

    var header = select('#header');
    if (this.$route.matched[0].path == '') {
      header.classList.remove('header-top');
    } else {
      header.classList.add('header-top');
    }
    let navbarMain = select('#navbarMain');
    if (navbarMain.classList.contains('navbarMain-mobile')) {
      navbarMain.classList.remove('navbarMain-mobile');
      let navbarToggle = select('.mobile-nav-toggle');
      navbarToggle.classList.toggle('bi-list');
      navbarToggle.classList.toggle('bi-x');
      console.log(navbarMain.classList.value);
    }
  },
  created() {
    if (localStorage.getItem('token')) {
      this.token = localStorage.getItem('token');
      this.nickname = localStorage.getItem('nickname');
      this.steamid = localStorage.getItem('steamid');
    }
    const select = (el, all = false) => {
      el = el.trim();
      if (all) {
        return [...document.querySelectorAll(el)];
      } else {
        return document.querySelector(el);
      }
    };
    let navbarMain = select('#navbarMain');
    if (navbarMain.classList.contains('navbarMain-mobile')) {
      navbarMain.classList.remove('navbarMain-mobile');
      let navbarToggle = select('.mobile-nav-toggle');
      navbarToggle.classList.toggle('bi-list');
      navbarToggle.classList.toggle('bi-x');
      console.log(navbarMain.classList.value);
    }
  },
  updated() {
    const select = (el, all = false) => {
      el = el.trim();
      if (all) {
        return [...document.querySelectorAll(el)];
      } else {
        return document.querySelector(el);
      }
    };

    var header = select('#header');
    if (this.$route.matched[0].path == '') {
      header.classList.remove('header-top');
    } else {
      header.classList.add('header-top');
    }
    let navbarMain = select('#navbarMain');
    if (navbarMain.classList.contains('navbarMain-mobile')) {
      navbarMain.classList.remove('navbarMain-mobile');
      let navbarToggle = select('.mobile-nav-toggle');
      navbarToggle.classList.toggle('bi-list');
      navbarToggle.classList.toggle('bi-x');
      console.log(navbarMain.classList.value);
    }
  },

  data() {
    return {
      bannerShow: true,
      headerShow: true,
      show: true,
      lineshow: '',
      token: '',
    };
  },
  computed: {
    ...mapGetters(['getUserid', 'getNickname']),
    ...mapState(['loginStatus']),
  },
  methods: {
    // toggle() {
    //   this.headerShow = true;
    //   this.bannerShow = false;
    // },
    // toggleBanner() {
    //   this.bannerShow = true;
    //   this.headerShow = false;
    // },
    logoutHandler() {
      localStorage.removeItem('token');
      localStorage.removeItem('userid');
      localStorage.removeItem('nickname');
      localStorage.removeItem('steamid');
      // this.$store
      //   .dispatch('logout')
      //   .then(() =>
      //     this.$router.replace({
      //       path: '/',
      //     })
      //   )
      //   .catch(() => {});

      window.location.reload();
      this.$router.replace({ path: '/' });
      window.location.reload();
    },
    clickHandler: function() {
      // setTimeout(function() {
      const scrollto = () => {
        window.scrollTo({
          top: 0,
          behavior: 'smooth',
        });
      };
      const select = (el, all = false) => {
        el = el.trim();
        if (all) {
          return [...document.querySelectorAll(el)];
        } else {
          return document.querySelector(el);
        }
      };
      var header = select('#header');
      console.log(header);
      if (!header.classList.contains('header-top')) {
        header.classList.add('header-top');
      }

      var greenline = select('.nav-link');
      if (!this.lineshow === '') {
        greenline.classList.add('active');
      } else {
        greenline.contains.remove('active');
      }
      //var menu = select('#menu-login');
      var menu = select('.nav-link');
      // console.log(menu.substring('#', -1));
      // console.log(menu.split('#'));
      console.log(menu);

      scrollto();
      // }, 0);
    },
    clickHandler2: function() {
      const scrollto = () => {
        window.scrollTo({
          top: 0,
          behavior: 'smooth',
        });
      };
      const select = (el, all = false) => {
        el = el.trim();
        if (all) {
          return [...document.querySelectorAll(el)];
        } else {
          return document.querySelector(el);
        }
      };
      var header = select('#header');
      if (!header.classList.contains('header-top')) {
        header.classList.add('header-top');
      }
      var menu = select('.nav-link');
      // console.log(menu.substring('#', -1));
      // console.log(menu.split('#'));
      console.log(menu);

      scrollto();
    },
    toggleBanner: function() {
      const select = (el, all = false) => {
        el = el.trim();
        if (all) {
          return [...document.querySelectorAll(el)];
        } else {
          return document.querySelector(el);
        }
      };
      console.log('home');
      var header = select('#header');
      header.classList.remove('header-top');
    },
    mobileHandler: function() {
      console.log('모바일');
      const select = (el, all = false) => {
        el = el.trim();
        if (all) {
          return [...document.querySelectorAll(el)];
        } else {
          return document.querySelector(el);
        }
      };
      console.log('모바일1');
      let navbarMain = select('#navbarMain');
      if (navbarMain.classList.contains('navbarMain-mobile')) {
        // navbarMain.classList.value -= ' navbarMain-mobile';
        navbarMain.classList.remove('navbarMain-mobile');
      } else {
        // navbarMain.classList.value += ' navbarMain-mobile';
        navbarMain.classList.add('navbarMain-mobile');
      }
      console.log(navbarMain.classList.value);
    },
  },
};
</script>

<style>
.slide-leave-active {
  transition: opacity 0.3s ease;
  opacity: 0;
  animation: slide-out 0.3s ease-out forwards;
}

.slide-leave {
  opacity: 1;
  transform: translateX(0);
}

.slide-enter-active {
  animation: slide-in 1s ease-out forwards;
}

@keyframes slide-out {
  0% {
    transform: translateY(0);
  }
  100% {
    transform: translateY(-10px);
  }
}

@keyframes slide-in {
  0% {
    transform: translateY(-10px);
  }
  100% {
    transform: translateY(0);
  }
}

v-enter-active,
v-leave-active {
  transition: 0.5s;
}

v-enter,
v-leave-to {
  opacity: 0;
  transform: translateY(100px);
}

div .menulogin li {
  float: left;
}
div .menulogin {
  margin-left: 30px;
  margin-right: 30px;
}
</style>
