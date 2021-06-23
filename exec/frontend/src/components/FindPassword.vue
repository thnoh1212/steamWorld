<template>
  <section class="section-show">
    <div class="container" style="background-color:#00000077; margin-bottom:50px; align:center">
      <div class="section-title">
        <h2>Find Password</h2>
        <p>비밀번호 찾기</p>
      </div>

      <div class="signup-container">
        <div class="form-group">
          <label :hidden="flag == true">ID (Email address)</label>
          <input
            type="email"
            class="form-control form-control-lg"
            v-model="user.userid"
            id="email"
            placeholder="이메일을 입력하세요"
            :hidden="flag == true"
          />
        </div>

        <button
          type="submit"
          class="btn btn-dark btn-lg btn-block"
          style="margin-bottom:30px"
          @click="findpasswordHandler()"
          :hidden="flag == true"
        >
          이메일 확인
        </button>
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
          @click="checkid()"
          :hidden="flag == false"
        >
          인증코드 다시받기
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
        userid: '',
      },
      code: '',
      flag: false,
    };
  },
  methods: {
    findpasswordHandler: function() {
      let err = true;
      let msg = '';

      err && !this.user.userid && ((msg = '이메일을 입력해주세요'), (err = false));
      err &&
        !this.validEmail(this.user.userid) &&
        ((msg = '이메일 형식에 맞춰 입력해주세요 ex) steamworld@steamworld.com'), (err = false));

      if (!err) alert(msg);
      else this.checkid();
    },
    validEmail: function(userid) {
      //이메일 형식 체크
      var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(userid);
    },
    checkid: function() {
      axios
        //.post(api_url + `/a105/user/checkid`, this.user)
        .post(`${SERVER_URL}/user/checkid`, this.user)
        .then((res) => {
          if (res.data.success == 'fail') {
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
            alert('가입하지 않은 아이디입니다!');
          }
        })
        .catch((error) => {
          console.dir(error);
        });
    },
    emailConfirm: function() {
      // console.log(this.code)
      // axios.post(api_url + `/a105/mail/verify`, code).then((res) => {
      axios.post(`${SERVER_URL}/mail/verify`, this.code).then((res) => {
        if (res.data.success == 'success') {
          this.$router.push({
            path: '/user/resetpassword',
            query: { userid: this.user.userid },
          });
        } else {
          alert('이메일 인증에 실패했습니다. 다시 시도해주세요.');
        }
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
