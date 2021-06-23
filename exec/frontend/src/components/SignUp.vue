<template>
  <section class="section-show">
    <div
      class="container"
      style="background-color:#00000077; margin-bottom:50px; align:center"
    >
      <div class="section-title">
        <h2>Sign Up</h2>
        <p>회원가입</p>
      </div>

      <div class="signup-container">
        <div class="form-group">
          <label>NickName</label>
          <input
            type="text"
            class="form-control form-control-lg"
            v-model="user.nickname"
            id="nickname"
            placeholder="닉네임을 입력하세요"
          />
        </div>

        <div class="form-group">
          <label>ID (Email address)</label>
          <input
            type="email"
            class="form-control form-control-lg"
            v-model="user.userid"
            id="email"
            placeholder="이메일을 입력하세요"
          />
        </div>

        <div class="form-group">
          <label>Password</label>
          <input
            type="password"
            class="form-control form-control-lg"
            v-model="user.password"
            id="password"
            placeholder="비밀번호를 입력하세요"
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
        <div class="form-group">
        <label :hidden="flag == false">인증 코드 (Verification code)</label>
          <input
            type="text"
            class="form-control form-control-lg"
            v-model="code"
            id="code"
            placeholder="이메일 인증 코드를 입력하세요"
            :hidden="flag == false"
          />
        </div>
        <button
          type="submit"
          class="btn btn-dark btn-lg btn-block"
          style="margin-bottom:30px"
          @click="emailConfirm()"
          :hidden="flag == false"
        >
        이메일 인증
        </button>
        <button
          type="submit"
          class="btn btn-dark btn-lg btn-block"
          style="margin-bottom:30px"
          @click="signupHandler()"
        >
          회원가입
        </button>

        <p class="forgot-password text-right">
          이미 계정이 있나요?
          <router-link to="/user/login"> 로그인하러 가기</router-link>
        </p>
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
        userid: '',
        nickname: '',
        password: '',
      },
      passwordCheck: '',
      code: '',
      flag: false,
    };
  },
  methods: {
    signupHandler: function() {
      let err = true;
      let msg = '';

      !this.user.nickname && ((msg = '닉네임을 입력해주세요'), (err = false));
      err &&
        !this.user.userid &&
        ((msg = '이메일을 입력해주세요'), (err = false));
      err &&
        !this.user.password &&
        ((msg = '비밀번호를 입력해주세요'), (err = false));
      err &&
        !this.passwordCheck &&
        ((msg = '비밀번호를 확인란을 입력해주세요'), (err = false));
      err &&
        !this.validEmail(this.user.userid) &&
        ((msg =
          '이메일 형식에 맞춰 입력해주세요 ex) steamworld@steamworld.com'),
        (err = false));
      err &&
        !this.validPassword(this.user.password) &&
        ((msg =
          '비밀번호 형식에 맞춰 입력해주세요 (영문, 숫자 포함 8글자 이상'),
        (err = false));
      err &&
        !(this.user.password == this.passwordCheck) &&
        ((msg = '비밀번호를 다시 확인해주세요!'), (err = false));

      if (!err) alert(msg);
      else this.checkid();
    },
    validEmail: function(userid) {
      //이메일 형식 체크
      var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(userid);
    },
    validPassword: function(password) {
      //비밀번호 형식 체크
      var re = /^[a-zA-z0-9]{8,}$/;
      return re.test(password);
    },
    checkid: function() {
      axios
        //.post(api_url + `/a105/user/checkid`, this.user)
        .post(`${SERVER_URL}/user/checkid`, this.user)
        .then((res) => {
          if (res.data.success == 'success') {
            axios
              // .post(api_url + `/a105/mail/*`, '"' + this.user.userid + '"')
              .post(`${SERVER_URL}/mail/*`, '"' + this.user.userid + '"')
              .then(() => {
                alert('입력하신 메일로 인증코드가 발송되었습니다. 인증코드를 입력해주세요.');
                this.flag = true;
              })
              .catch((err) => {
                console.dir(err);
              });
          } else {
            alert('이미 등록된 아이디입니다!');
          }
        })
        .catch((error) => {
          console.dir(error);
        });
    },
    emailConfirm: function() {
      // axios.post(api_url + `/a105/mail/verify`, code).then((res) => {
      axios.post(`${SERVER_URL}/mail/verify`, this.code).then((res) => {
        if (res.data.success == 'success') {
          this.signUp();
        } else {
          console.log(res.data);
        }
      });
    },
    signUp: function() {
      axios
        // .post(api_url + `/a105/user/join`, this.user)
        .post(`${SERVER_URL}/user/join`, this.user)
        .then((res) => {
          if (res.data.success == 'success') {
            alert(
              'Steam World! 에 가입해주셔서 감사합니다! 로그인 페이지로 이동합니다.'
            );
          } else {
            alert('회원가입에 실패하였습니다. 다시 시도해주세요.');
          }
        })
        .catch((error) => {
          console.dir(error);
        });

      this.$router.push({
        path: '/user/login',
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
