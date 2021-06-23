<template>
  <div>
    <b-navbar
      toggleable="lg"
      type="dark"
      style="margin-top:0px; background-color:#00000099; "
    >
      <b-navbar-brand
        href="#"
        style="color:white; font-size:30px; font-weight:bold"
        >Steam World!</b-navbar-brand
      >

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-navbar-nav right>
            <router-link to="/" class="nav-link active" href="#header"
              >Home</router-link
            >
            <router-link to="/survey" class="nav-link" href="#about"
              >내게 맞는 게임 찾기</router-link
            >
            <router-link to="/recommend" class="nav-link" href="#portfolio"
              >게임 추천</router-link
            >
            <div v-if="!getUserid">
              <router-link to="/user/login" class="nav-link" href="#services"
                >로그인</router-link
              >
              <router-link to="/user/signup" class="nav-link" href="#services"
                >회원가입</router-link
              >
            </div>
            <div v-else>
              <router-link to="/mypage" class="nav-link" href="#services"
                >마이 페이지</router-link
              >
              <span class="nav-link">{{ getNickname }} 님 환영합니다.</span>
              <span @click="logoutHandler" class="nav-link">로그아웃</span>
            </div>

            <router-link to="/swiper" class="nav-link" href="#services"
              >스와이퍼</router-link
            >
            <span class="nav-link"
              ><a href="#"
                ><img src="../assets/img/steam.svg" class="steamlogo"/></a
              >스팀 연동하기</span
            >
          </b-navbar-nav>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  name: 'TopHeader',
  data() {
    return {
      bannerShow: true,
      headerShow: true,
      show: true,
    };
  },
  computed: {
    ...mapGetters(['getUserid', 'getNickname']),
  },
  methods: {
    toggle() {
      this.headerShow = true;
      this.bannerShow = false;
    },
    toggleBanner() {
      this.bannerShow = true;
      this.headerShow = false;
    },
    logoutHandler() {
      this.$store
        .dispatch('logout')
        .then(() =>
          this.$router.push({
            path: '/',
          })
        )
        .catch(() => {});
    },
  },
};
</script>

<style>
.nav-link {
  float: left;
}
.steamlogo {
  width: 15%;
}
</style>
