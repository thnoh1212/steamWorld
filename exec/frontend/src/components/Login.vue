<template>
  <section class="section-show">
    <!-- ======= About Section ======= -->
    <div class="container" style="background-color:#00000077; margin-bottom:50px;">
      <div class="section-title">
        <h2>Login</h2>
        <p>로그인</p>
      </div>
      <div class="text-container">
        <!-- <div>
        <b-form-group
          id="fieldset-1"
          label="아이디"
          label-for="input-1"
          valid-feedback="Thank you!"
          :invalid-feedback="invalidFeedback"
          :state="state"
          class="col-md-6"
        >
          <b-form-input
            class="input"
            id="input-1"
            v-model="id"
            :state="state"
            trim
          ></b-form-input>
        </b-form-group>
      </div>

      <div>
        <b-form-group
          id="fieldset-2"
          label="비밀번호"
          label-for="input-2"
          valid-feedback="Thank you!"
          :invalid-feedback="invalidFeedbackPassword"
          :state="statePassword"
          class="col-md-6"
        >
          <b-form-input
            class="input"
            id="input-2"
            v-model="password"
            :state="statePassword"
            trim
          ></b-form-input>
        </b-form-group>
      </div> -->

        <!-- ======= Login Section ======= -->

        <div class="form-group">
          <label :invalid-feedback="invalidFeedback">User ID (Email address)</label>
          <b-form-input
            type="email"
            class="form-control form-control-lg"
            v-model="user.userid"
            :state="state"
            id="feedback-user"
          />
          <b-form-invalid-feedback :state="validation">
            Your user ID must be 5-12 characters long.
          </b-form-invalid-feedback>
          <b-form-valid-feedback :state="validation">
            Looks Good.
          </b-form-valid-feedback>
        </div>

        <div class="form-group">
          <label>Password</label>
          <b-form-input
            type="password"
            class="form-control form-control-lg"
            id="text-password"
            aria-describedby="password-help-block"
            v-model="user.password"
            :state="statePassword"
          />
          <b-form-text id="password-help-block">
            Your password must be 8-20 characters long, contain letters and numbers, and must not
            contain spaces, special characters, or emoji.
          </b-form-text>
        </div>

        <button type="submit" class="btn btn-dark btn-lg btn-block" @click="loginHandler()">
          로그인
        </button>

        <p class="forgot-password text-right mt-2 mb-4">
          <router-link to="/user/findpassword">Forgot password ?</router-link>
        </p>
      </div>
    </div>
  </section>
</template>

<script>
const axios = require('axios');
const SERVER_URL = process.env.VUE_APP_API_SERVER_URL;
// const SERVER_URL = process.env.VUE_APP_LOCALHOST_URL;
export default {
  created() {},
  computed: {
    // state() {
    //   return this.user.userid.length >= 4;
    // },
    // statePassword() {
    //   return this.user.password.length >= 4;
    // },
    // invalidFeedback() {
    //   if (this.user.userid.length > 0) {
    //     return 'Enter at least 4 characters.';
    //   }
    //   return 'Please enter something.';
    // },
    // invalidFeedbackPassword() {
    //   if (this.user.password.length > 0) {
    //     return 'Enter at least 4 characters.';
    //   }
    //   return 'Please enter something.';
    // },
  },
  data() {
    return {
      user: {
        userid: '',
        password: '',
      },
    };
  },
  methods: {
    loginHandler: function() {
      // console.log(this.user.userid);
      let err = true;
      let msg = '';

      //에러메세지 출력 - 이메일, 패스워드 미입력시
      !this.user.userid && ((msg = '이메일을 입력해주세요.'), (err = false));
      err && !this.user.password && ((msg = '비밀번호를 입력해주세요.'), (err = false));
      err &&
        !this.validEmail(this.user.userid) &&
        ((msg = '이메일 형식에 맞게 입력해주세요. ex) steamworld@steamworld.com'), (err = false));

      if (!err) alert(msg);
      else this.login(); //에러 안나면 login()실행
    },

    //이메일 형식 체크
    validEmail: function(email) {
      var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
    login: function() {
      axios.post(`${SERVER_URL}/user/login`, this.user).then((res) => {
        // console.log(SERVER_URL);
        if (res.data.success == 'success') {
          localStorage.setItem('token', res.data['x-access-token']);
          localStorage.setItem('userid', res.data.data.userid);
          localStorage.setItem('nickname', res.data.data.nickname);
          localStorage.setItem('steamid', res.data.data.steamid);

          window.location.reload();
          this.$router.replace(`/`);
          window.location.reload();
        } else {
          alert('ID 또는 비밀번호를 확인해주세요');
          this.user.password = '';
        }
      });
    },
  },
};
</script>

<style>
span {
  color: white;
}
label {
  color: white;
}

.text-container {
  padding-left: 300px;
  padding-right: 300px;
  padding-top: 100px;
  padding-bottom: 100px;
}
</style>
