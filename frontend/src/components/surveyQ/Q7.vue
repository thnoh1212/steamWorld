<template>
  <div class="container blueArea">
    <div clss="row">
      <vue-typer
        :text="['Q7.눈앞에 강해보이는 몬스터가 나타났다! 나의 선택은?']"
        :repeat="0"
        initial-action="typing"
        :pre-type-delay="70"
        :type-delay="70"
        caret-animation="blink"
      ></vue-typer>
      <hr />
      <img
        height="400px"
        src="../../assets/img/q7.gif"
        style="padding-left:150px"
      />
      <img height="400px" src="../../assets/img/q72.gif" />
      <hr />

      <router-link to="/q8" class="nav-link"
        ><b-button @click="put1"
          >나보다 압도적으로 강할 것 같다. 자리를 피한다.</b-button
        ></router-link
      >
      <router-link to="/q8" class="nav-link"
        ><b-button @click="put2"
          >내가 이길 수 있다. 좋은 아이템을 위해 사냥한다.</b-button
        ></router-link
      >
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    put1() {
      this.$store.commit('slice', 6);
      this.$store.commit('putJ');
    },
    put2() {
      this.$store.commit('slice', 6);
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
