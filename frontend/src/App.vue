<template>
  <div id="app">
    <link
      href="../src/assets/vendor/bootstrap-icons/bootstrap-icons.css"
      rel="stylesheet"
    />
    <!-- <TopHeader /> -->
    <SwBanner />
  </div>
</template>
<script>
import SwBanner from './components/SwBanner.vue';
// import TopHeader from "./components/TopHeader.vue";

export default {
  name: 'App',
  components: {
    SwBanner,
    // TopHeader,
  },
};
</script>

<style>
h1,
h2,
h3,
h4,
h5,
p {
  color: white;
}
@import './assets/css/style.css';
@import './assets/vendor/bootstrap-icons/bootstrap-icons.css';
</style>

<style type="text/css" media="screen, print">
@font-face {
  font-family: 'DungGeunMo';
  src: '../';
}

* {
  font-family: 'DungGeunMo';

}

p {
  font-family: 'DungGeunMo';
  font-style: normal;
  font-weight: normal;
}
em {
  font-family: 'DungGeunMo';
  font-style: normal;
  font-weight: normal;
}
a {
  font-family: 'DungGeunMo';
  font-style: normal;
  font-weight: normal;
}
h1 {
  font-family: 'DungGeunMo';
  font-style: normal;
  font-weight: normal;
}
h2 {
  font-family: 'DungGeunMo';
  font-style: normal;
  font-weight: normal;
}
span{
  color: white !important;
}

hr{
  color: white;
  size: 2px;
}

</style>
