<template>
  <div class="body-bg">
    <!-- Header -->
    <div class="header">
      <div style="width: 100%">
        <el-menu class="el-menu-demo" mode="horizontal" router text-color="#000000">
          <el-menu-item><a href="http://fenix-cui.xyz"><el-image src="static/images/bunny.ico"></el-image></a></el-menu-item>
          <el-menu-item index="1">主页</el-menu-item>
          <el-submenu index="2">
            <template slot="title">工作台</template>
            <el-menu-item index="2-1">( •̀ ω •́ )✧</el-menu-item>
            <el-submenu index="2-4">
              <template slot="title">👉</template>
              <el-menu-item index="2-4-1">~(￣▽￣)~*</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-menu-item index="3" disabled>消息中心</el-menu-item>
          <el-menu-item index="/myself">我</el-menu-item>
        </el-menu>
      </div>
    </div>

    <!-- Main -->
    <div class="main">
      <span>{{mainTitle}}<br/></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
      <span v-for="title in mainTitle"><div class="title_list">{{title}}</div></span>
    </div>

    <br/>
    <br/>
    <br/>

    <!-- footer -->
    <div class="footer">
      <p class="copyright">&copy; Created. Design: Fenix.Cui</p>
      <!-- 备案 -->
      <div style="margin:0 auto; padding:20px 0;">
        <img src="static/images/filing-icon.png" /><a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11010502043277" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"><img src="" style="float:left;"/><p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:#939393;">京公网安备 11010502043277号</p></a>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from "axios"

  export default {
    props: [],
    name: "Main",
    data() {
      return {
        activeIndex: '1',
        mainTitle: [],

      };

    },
    mounted() {
      axios.get("/api/http/main/getMainTitle")
        // .then((res)=>{
        // this.mainTitle = res.data
        // })
        .then((res)=>{
          for (let i = 0; i < res.data.length; i++) {
            console.log(res.data[i]["title"])
            this.mainTitle.push(res.data[i]["title"])
          }
        })

    },
    methods: {

    },
    component:{

    }
  }

</script>

<style scoped lang="css">

  /*header*/

  .el-menu-demo, .el-menu-item, el-submenu {
    background-color: rgba(0,0,0,0);
    border-bottom: 0px;
  }

  /*main*/
  .main, .title_list {
    background-color: rgba(255,255,255,0.2);
    color: #000000;  /*文本颜色*/
    text-align: center;
    /*line-height: 160px;*/
    width: 100%;
  }
  .title_list{
    /*background-color: coral;*/
    width: 50%;
    height: auto;
    overflow: hidden;
    float: left;
  }

  /*footer*/
  .footer {
    text-align: center;
    overflow: unset;
    /*position: absolute;*/
    bottom: 0;
    width: 100%;
    /*height: 60px;*/
  }
</style>
