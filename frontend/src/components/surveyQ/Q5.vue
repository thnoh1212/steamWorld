<template>
  <div class="container blueArea">
    <div clss="row">
      <vue-typer
        :text="['Q5. 모험 중 저 멀리 한 마을이 불타고 있다. 어떻게 할까...?']"
        :repeat="0"
        initial-action="typing"
        :pre-type-delay="70"
        :type-delay="70"
        caret-animation="blink"
      ></vue-typer>
      <hr />
      <div class="text-center">
        <img height="450px" src="../../assets/img/q5.gif" />
      </div>
      <hr />

      <router-link to="/q6" class="nav-link"
        ><b-button @click="put1"
          >이미 저 곳은 틀렸다. 가던 길을 가자.</b-button
        ></router-link
      >
      <router-link to="/q6" class="nav-link"
        ><b-button @click="put2"
          >혹시라도 생존자가 남아 있을 수 있다. 도와주러 간다.</b-button
        ></router-link
      >
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    put1() {
      this.$store.commit('slice', 4);
      this.$store.commit('putT');
    },
    put2() {
      this.$store.commit('slice', 4);
      this.$store.commit('putF');
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
