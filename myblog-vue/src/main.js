import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';

import router from './router'
import {VueJsonp} from "vue-jsonp";
import Vuex from "vuex";

Vue.use(ElementUI);
Vue.use(VueJsonp);
Vue.use(Vuex);

const store = new Vuex.Store({
  state:{
    username: null,
  }
})

new Vue({
  el: '#app',
  store: store,
  router,
  render: h => h(App)  // ElementUI
});
