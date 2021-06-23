<template>

<div class="container blueArea">
    <div clss="row">
        <vue-typer
  :text='["Q1. 게임을 시작하자! 캐릭터를 선택할 때 나는 "]'
  :repeat='0'
  initial-action='typing'
  :pre-type-delay='70'
  :type-delay='70'
  caret-animation='blink'
></vue-typer>
        
                <hr>
                <div class="text-center">
        <img height=300px src="../../assets/img/q1.gif"/>
        <hr>



        <router-link to="/q2" class="nav-link"><b-button @click="put1">성능보다 내가 좋아하는 캐릭터를 고른다.</b-button></router-link>
        <router-link to="/q2" class="nav-link"><b-button @click="put2">성능이 가장 좋은 캐릭터를 고른다.</b-button></router-link>
        <p id="demo"></p>
        </div>




</div>
</div>
</template>


<script>



export default {
  data(){
    return{
      i: 0,
      txt: "dfdfdf df df d dfd ",
      speed: 50
    }
  },
  created(){
    this.typeWriter();
  },

  methods:{
   typeWriter() {
     var i = 0;
      var txt = 'Lorem ipsum typing effect!'; 
     var speed = 50;
      if (i < txt.length) {
        this.document.getElementById("demo").innerHTML += txt.charAt(i);
        i++;
      setTimeout(this.typeWriter, speed);
     }
},

    put1(){
           this.$store.commit('slice',0);
      this.$store.commit('putF');


    },
    put2(){
           this.$store.commit('slice',0);
     this.$store.commit('putT');
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
