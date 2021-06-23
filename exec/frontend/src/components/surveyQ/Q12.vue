<template>

<div class="container blueArea">
    <div clss="row">
                                <vue-typer
          :text="['Q12. 출구를 찾다가 양갈래 길이 나왔다. 어디로 가야하지? ']"
          :repeat="0"
          initial-action="typing"
          :pre-type-delay="70"
          :type-delay="70"
          caret-animation="blink"
        ></vue-typer>
        <hr>
               <div class="text-center">
        <img height=300px src="../../assets/img/q12.gif"/>
        </div>
        <hr>



        <router-link to="/result" class="nav-link"><b-button @click="put1">바람의 방향, 주변 환경, 지금까지의 패턴을 분석하여 결정한다. </b-button></router-link>
        <router-link to="/result" class="nav-link"><b-button @click="put2">복잡한건 생각하지 말고 느낌이 좋은 쪽으로 가자! </b-button></router-link>




</div>
</div>
</template>

<script>


export default {

  methods:{

    put1(){
           this.$store.commit('slice',11);
      this.$store.commit('putS');


    },
    put2(){
           this.$store.commit('slice',11);
     this.$store.commit('putN');
    }

  }
}
</script>

<style scoped>
h2{
  font-size:2vw;
}
h2.typing-txt{
  position:relative;
  display: inline-block;
  height: 40px; overflow: hidden;
  animation: typingAni 2s steps(15, end) 1;
}

h2.typing-txt::after{
    position:absolute; 
  display:block;
  content:""; 
  width: 5px; 
  height: 30px;
  top : 5px;
  right:0px; 
  border-right: 3px solid rgb(252, 252, 252);
  animation: cursor 0.5s step-end infinite;
}
@keyframes typingAni {
  0%{width:0%}
  100%{width: 80%};
}
@keyframes cursor{
  0%{opacity:0;}
  50%{opacity:1;}
  100%{opacity:0;}
}


.vue-typer {
  font-family: "DungGeunMo";
  font-size: 2.2vw;
}


button:hover{
  background-color:#1f813f;
}

button{
  width:100%;
}


</style>

