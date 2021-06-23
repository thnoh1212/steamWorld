<template>
  <section class="section-show" style="margin-bottom:100px">
    <div class="row">
      <div class="col-md-1"></div>
      <div class="col-md-10 box" style="background-color:#00000099; padding-top:20px">
        <div class="section-title">
          <h2>Portfolio</h2>
          <p>나의 게임 성향 알아보기</p>
          <div class="container">
          <div class="row">
          <div class="col-md-6">
            <img width=100% src="../assets/img/qBanner.gif" />
            <div id="surveyBtn" style="background-color:#11c15c;  margin-top:30px; border-radius:20px; width:100%; height:50px; font-size:20px; margin-bottom:100px">
          <router-link
            to="/q1"
            style="color:white; text-align:center;"
            class="nav-link"
            >테스트 시작!</router-link
          >
        </div>
          </div>
          <div class="col-md-6"> 
             <div class="section-title">
          <h2>테스트를 시작하기 전에</h2>
             </div>
            <h5>나의 게임 성향을 간단한 테스트로 알아봅시다.</h5>         
            <h5>시작 버튼을 누르면 테스트를 시작합니다.</h5>
          <h5>판타지 RPG를 플레이하는 유저라고 생각하고</h5>
            <h5>각 상황에 어떻게 할 것인지 선택해주세요!</h5></div>
          

          </div>
        </div>
        </div>


        

        <router-view> </router-view>
      </div>
    </div>
  </section>
</template>

<script></script>

<style scoped>
h5{
  font-size:1.5vw !important;
}
</style>
