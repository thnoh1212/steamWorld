<template>
  <div class="container blueArea">
    <div class="row">
      <vue-typer
        :text="[' Q4. 적들이 마구 몰려온다, 나의 판단은...']"
        :repeat="0"
        initial-action="typing"
        :pre-type-delay="70"
        :type-delay="70"
        caret-animation="blink"
      ></vue-typer>
    </div>
    <hr />
    <div class="text-center">
      <img height="400px" src="../../assets/img/q4.gif" />
      <hr />
    </div>
    <div class="">
      <router-link to="/q5" class="nav-link"
        ><b-button @click="put1">돌격하여 적을 쳐부수자!</b-button></router-link
      >
      <router-link to="/q5" class="nav-link"
        ><b-button @click="put2"
          >일단 안전한 곳으로 숨어 기회를 노린다.</b-button
        ></router-link
      >
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    updated() {
      console.log(this.$store.state.result);
      this.$store.dispatch('slice', 3);
    },

    put1() {
      this.$store.commit('slice', 3);
      this.$store.commit('putE');
    },
    put2() {
      this.$store.commit('slice', 3);
      this.$store.commit('putI');
    },
  },
};
</script>

<style scoped>
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
  animation: cursor 0.3s step-end infinite;
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

h2 {
  font-size: 2vw;
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
