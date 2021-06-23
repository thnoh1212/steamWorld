<template>

<div class="container blueArea">
    <div clss="row">
                        <vue-typer
          :text="['Q11. 강력하기로 유명한 길드에서 퀘스트에 참여할 용병을 모집한다.']"
          :repeat="0"
          initial-action="typing"
          :pre-type-delay="70"
          :type-delay="70"
          caret-animation="blink"
        ></vue-typer>
        <hr>
               <div class="text-center">
        <img height=300px src="../../assets/img/q11.gif"/>
        </div>
        <hr>



        <router-link to="/q12" class="nav-link"><b-button @click="put1">어떤 퀘스트인지 상세하게 알아보고 결정한다. </b-button></router-link>
        <router-link to="/q12" class="nav-link"><b-button @click="put2">길드의 유명세에 편승하자. 일단 참여한다!</b-button></router-link>




</div>
</div>
</template>

<script>


export default {

  methods:{

    put1(){
           this.$store.commit('slice',10);
      this.$store.commit('putJ');


    },
    put2(){
           this.$store.commit('slice',10);
     this.$store.commit('putP');
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

