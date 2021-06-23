import Vue from 'vue'
import App from './App.vue'
import { router } from './router.js'
import store from './store/store.js'
import axios from 'axios'
import { BootstrapVue,  CarouselPlugin, BootstrapVueIcons  } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'bootstrap-slider/dist/css/bootstrap-slider.css'
import BootstrapIcons from './assets/vendor/bootstrap-icons//bootstrap-icons.json'
import 'aos'
import Aos from 'aos'
import VueSimpleAccordion from 'vue-simple-accordion';
import 'vue-simple-accordion/dist/vue-simple-accordion.css';
import VueTyperPlugin from 'vue-typer'
import Loading from 'vue-loading-overlay';
// Import stylesheet
import 'vue-loading-overlay/dist/vue-loading.css';
// Init plugin
Vue.use(Loading);

Vue.use(VueSimpleAccordion);
// import Lightbox from 'vue-my-photos'
// Vue.component('lightbox', Lightbox);

export const api_url = "http://localhost:8080"
// export const api_url = "http://j4a105.p.ssafy.io:8080/a105/"

export const SERVER_URL = process.env.VUE_APP_API_SERVER_URL;
export const LOCALHOST_URL = process.env.VUE_APP_LOCALHOST_URL;

Vue.prototype.$api_url = api_url

Vue.prototype.$SERVER_URL = SERVER_URL
Vue.prototype.$LOCALHOST_URL = LOCALHOST_URL


// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin

Vue.use(BootstrapVueIcons)
Vue.use(CarouselPlugin)

Vue.use(BootstrapIcons)
Vue.use(Aos)
Vue.use(axios)
Vue.use(VueTyperPlugin)

// Vue.use(Lightbox)



Vue.config.productionTip = false
Vue.prototype.$Axios = axios



new Vue({
  render: h => h(App),
  router,
  store,
  scrollBehavior (to, from, savedPosition) {
    if (to.hash) {
      return {
        selector: to.hash
        // , offset: { x: 0, y: 10 }
      }
    }
  }
}).$mount('#app')
