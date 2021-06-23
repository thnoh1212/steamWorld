<template>
  <div class="container blueArea">
    <div clss="row">
      <vue-typer
        :text="['Q6. 내가 더 좋아하는 게임은?']"
        :repeat="0"
        initial-action="typing"
        :pre-type-delay="70"
        :type-delay="70"
        caret-animation="blink"
      ></vue-typer>
      <hr />
      <div class="text-center">
        <img height="450px" src="../../assets/img/q6.gif" />
      </div>
      <hr />

      <router-link to="/q7" class="nav-link"
        ><b-button @click="put1"
          >정해진 룰에 따라서 상대방과 경쟁하는 게임</b-button
        ></router-link
      >
      <router-link to="/q7" class="nav-link"
        ><b-button @click="put2"
          >자유롭게 탐험하며 나만의 시간을 즐기는 게임</b-button
        ></router-link
      >
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    put1() {
      this.$store.commit('slice', 5);
      this.$store.commit('putJ');
    },
    put2() {
      this.$store.commit('slice', 5);
      this.$store.commit('putP');
    },
  },
};
</script>

<style scoped>
h2 {
  font-size: 2vw;
}
h2.typing-txt {
  position: relative;
  display: inline-block;
  height: 40px;
  overflow: hidden;
  animation: typingAni 2s steps(15, end) 1;
}

h2.typing-txt::after {
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
    width: 80%;
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

.vue-typer {
  font-family: 'DungGeunMo';
  font-size: 2.2vw;
}

button:hover {
  background-color: #1f813f;
}

button {
  width: 100%;
}
</style>
