<template>
  <div>
    <div class="parent">
      <div class="child1-5">
        <h3>프로필 상세</h3>
      </div>
      <div class="child2">
        <br />
        <div id="all_info">
          <div id="user_info">
            <div id="login_info">
              <span style="font-size: 20px"> 아이디(이메일) </span>
            </div>
          </div>
          <br />
          <div>
            <div style="margin-top:35px">
              <div class="inpbx" style="font-size: large">
                <span style="font-size: 20px ;"> 닉네임 </span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="child2">
        <br />
        <div id="all_info">
          <div id="user_info">
            <div id="login_info">
              <span style="font-size: 20px">{{ user.userid }}</span>
              <!-- <b-input style="font-size: 20px" :value=user.userid> </b-input> -->
            </div>
          </div>
          <br />
          <div>
            <div style="margin-top:35px">
              <div class="inpbx" style="font-size: large">
                <span v-if="!show_user" style="font-size: 20px ;">{{ user.nickname }}</span>
                <b-input
                  v-else
                  style="font-size: 20px"
                  v-bind:value="user.nickname"
                  v-model="user.nickname"
                >
                </b-input>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="child1">
        <b-button type="primary" @click.prevent="goPassword()" v-if="!show">
          비밀번호 변경
        </b-button>
        <br />
        <br />
        <b-button type="primary" @click.prevent="show_update_user()" v-if="!show"
          >프로필 수정</b-button
        >
        <div v-if="show_user == true" style="margin-left:30px">
          <div>
            <b-button type="primary" @click.prevent="updateUser()">
              수정 완료
            </b-button>
          </div>
          <br /><br />
          <div>
            <b-button type="primary" @click.prevent="cancel()">
              취소
            </b-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { mapState } from 'vuex';
// import UpdatePassword from "../UpdatePassword.vue"

const SERVER_URL = process.env.VUE_APP_API_SERVER_URL;
// const SERVER_URL = process.env.VUE_APP_LOCALHOST_URL;
export default {
  data() {
    return {
      show: false,
      show_user: false,
      show_modal: false,
      visible: false,
      modalText: '',
      user: {
        userid: '',
        password: '',
        nickname: '',
        steamid: '',
      },
    };
  },
  // components: {
  //   [Button.name]: Button,
  //   FgInput
  // },
  components: {
    // UpdatePassword,
  },
  computed: {
    ...mapState(['loginStatus']),
  },
  created() {
    this.user.steamid = localStorage.getItem('steamid');
    this.user.nickname = localStorage.getItem('nickname');
    this.user.userid = localStorage.getItem('userid');
  },
  methods: {
    show_update_user() {
      this.show_user = true;
      this.show = true;
    },

    cancel() {
      this.show_user = false;
      this.show = false;
    },

    updateUser() {
      // console.log(localStorage.getItem('token'))
      // console.log(this.user)
      axios
        .put(`${SERVER_URL}/user`, this.user, {
          headers: {
            'x-access-token': localStorage.getItem('token'),
          },
        })
        .then((res) => {
          if (res.data.success == 'fail') {
            alert('유저 정보를 수정하는데 실패 했습니다.');
          } else {
            alert('수정 완료');
            localStorage.setItem('nickname', this.user.nickname);
            window.location.reload();
          }
        })
        .catch((res) => {
          alert('error : ' + res);
        });
    },

    handleClickButton() {
      this.visible = !this.visible;
    },
    clickModal() {
      this.show_modal = true;
      // window.open('/updatepassword')
    },

    goPassword: function(userid) {
      // console.log(userid);
      this.$router.push({
        path: '/user/resetpassword',
        query: { userid: this.user.userid },
      });
    },
  },
};
</script>
<style scoped>
.parent {
  display: flex;
}
.child1 {
  flex: 1;
}
.child1-5 {
  flex: 1.5;
}

.child2 {
  flex: 2;
}
</style>
