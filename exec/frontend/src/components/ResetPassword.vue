<template>
  <section class="section-show">
    <div
      class="container"
      style="background-color:#00000077; margin-bottom:50px; align:center"
    >
      <div class="section-title">
        <h2>Reset Password</h2>
        <p>비밀번호 재설정</p>
      </div>

      <div class="signup-container">
        <div class="form-group">
          <label>ID (Email address)</label>
          <input
            type="email"
            class="form-control form-control-lg"
            v-model="user.userid"
            id="email"
            readonly
          />
        </div>
        <div class="form-group">
          <label>Password</label>
          <input
            type="password"
            class="form-control form-control-lg"
            v-model="user.newpassword"
            id="newpassword"
            placeholder="새로운 비밀번호를 입력하세요"
          />
        </div>
        <div class="form-group">
          <label>Password check</label>
          <input
            type="password"
            class="form-control form-control-lg"
            v-model="passwordCheck"
            id="password-check"
            placeholder="비밀번호를 한 번 더 입력하세요"
          />
        </div>

        <button
          type="submit"
          class="btn btn-dark btn-lg btn-block"
          style="margin-bottom:30px"
          @click="signupHandler()"
        >
          비밀번호 재설정
        </button>
      </div>
    </div>
  </section>
</template>

<script>
import axios from 'axios';
import { api_url } from '../main.js';
// import { SERVER_URL, LOCALHOST_URL } from '../main.js';
const SERVER_URL = process.env.VUE_APP_API_SERVER_URL;
const LOCALHOST_URL = process.env.VUE_APP_LOCALHOST_URL;

export default {
  data() {
    return {
      user: {
        userid: this.$route.query.userid,
        newpassword: '',
      },
      passwordCheck: '',
    };
  },
  methods: {
    signupHandler: function() {
      let err = true;
      let msg = '';

      err &&
        !this.user.newpassword &&
        ((msg = '비밀번호를 입력해주세요'), (err = false));
      err &&
        !this.passwordCheck &&
        ((msg = '비밀번호를 확인란을 입력해주세요'), (err = false));
      err &&
        !this.validPassword(this.user.newpassword) &&
        ((msg =
          '비밀번호 형식에 맞춰 입력해주세요 (영문, 숫자 포함 8글자 이상'),
        (err = false));
      err &&
        !(this.user.newpassword == this.passwordCheck) &&
        ((msg = '비밀번호를 다시 확인해주세요!'), (err = false));

      if (!err) alert(msg);
      else this.modifyPassword();
    },
    validPassword: function(newpassword) {
      //비밀번호 형식 체크
      var re = /^[a-zA-z0-9]{8,}$/;
      return re.test(newpassword);
    },
    modifyPassword: function() {
        console.log(this.user)
      axios
        .put(`${SERVER_URL}/user/password`, this.user)
        .then((res) => {
          if (res.data.success == 'success') {
            alert(
              '비밀번호 변경에 성공했습니다! 로그인 페이지로 돌아갑니다.'
            );
            this.$router.push({
                path: '/user/login',
            });
          } else {
            alert('비밀번호 변경에 실패했습니다. 다시 시도해주세요.');
          }
        })
        .catch((error) => {
          console.dir(error);
        });
    },
  },
};
</script>

<style>
.signup-container {
  padding-left: 300px;
  padding-right: 300px;
  padding-top: 100px;
  padding-bottom: 100px;
}
</style>
