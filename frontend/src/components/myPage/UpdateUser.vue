<template>
  <div>
    <div class="container" style="background:none">
      <h3 style=" font-weight:bold color:white;">프로필 상세</h3>

      <div class="row" style="margin-top:100px;">
        <div class="col-md-3 text-right"></div>
        <div class="col-md-2 text-left">
          <h4 style="font-size: 20px ;">닉네임</h4>
          <h4 style="font-size: 20px; color:#ffffff00;">dd</h4>
          <h4 style="font-size: 20px">아이디(이메일)</h4>

          <div stlye="marging-top:50px;"></div>

          <h4 style="font-size: 20px; marging-top:100px; ;">비밀번호</h4>
        </div>

        <div class="col-md-4">
          <div id="user_info"></div>

          <div>
            <div class="inpbx">
              <h4 v-if="!show_user" style="font-size: 20px ;">
                {{ user.nickname }}
              </h4>
              <b-button
                type="primary"
                @click.prevent="show_update_user()"
                style="margin-bottom:65px; margin-left:30"
                v-if="!show"
                >닉네임 수정</b-button
              >

              <b-input
                v-if="show_user"
                style="font-size: 15px; margin-bottom:65px;"
                v-bind:value="user.nickname"
                v-model="user.nickname"
              >
              </b-input>
            </div>
          </div>

          <h4 style="font-size: 20px">{{ user.userid }}</h4>
          <h4 style="font-size: 20px">*******</h4>
          <b-button
            class="btn-md"
            @click.prevent="goPassword()"
            style="margin-bottom:50px;"
          >
            비밀번호 변경
          </b-button>

          <h4></h4>
        </div>
        <div class="col-md-3">
          <div v-if="show_user == true">
            <table>
              <tr>
                <td>
                  <b-button type="primary" @click.prevent="updateUser()">
                    수정 완료
                  </b-button>
                </td>
                <td>
                  <b-button type="primary" @click.prevent="cancel()">
                    취소
                  </b-button>
                </td>
              </tr>
            </table>

            <span> </span>

            <div></div>
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

h4 {
  margin-bottom: 80px;
}
</style>
