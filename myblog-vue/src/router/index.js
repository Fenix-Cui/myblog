import Vue from "vue";
import VueRouter from "vue-router";

import Main from "../views/Main";
import Login from "../views/Login";
import Myself from "../views/Myself";
import Blog from "../views/Blog";
import Classification from "../views/Classification";

Vue.use(VueRouter);

export default new VueRouter({
  // 去除#号
  mode: 'history',
  routes:[
    {
      path: '/main',
      component: Main
    },{
      path: '/login',
      component: Login
    },{
      path: '/myself',
      component: Myself
    },{
      path: '/blog',
      component: Blog
    },{
      path: '/classification',
      component: Classification
    }
  ]
});
