<template>
  <div class="header">
    <div style="width: 100%">
      <el-menu class="el-menu-demo" mode="horizontal" router text-color="#000000">
        <el-menu-item><a href="http://localhost:9000">
          <el-image src="static/images/bunny.ico"></el-image>
        </a></el-menu-item>
        <el-menu-item index="/main" @click="reload()">主页</el-menu-item>
        <el-submenu index="2">
          <template slot="title">博客分类</template>
          <el-menu-item index="/main" @click="reload()">( •̀ ω •́ )✧全部博客</el-menu-item>
          <el-menu-item index="/main?data=Java" @click="reload()">( •̀ ω •́ )✧Java</el-menu-item>
          <el-menu-item index="/main?data=Python" @click="reload()">( •̀ ω •́ )✧Python</el-menu-item>
          <el-submenu index="2-1">
            <template slot="title">其他分类</template>
            <el-menu-item index="2-1-1" disabled>~(￣▽￣)~*待开放</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-menu-item index="3" disabled>消息中心</el-menu-item>
        <el-menu-item index="/myself">我</el-menu-item>

        <div id="search">
          <el-input id="search-input" @keydown.enter.native="search(input)" v-model="input" size="medium" placeholder="请输入内容"></el-input>
          <el-button id="search-button" type="primary" icon="el-icon-search" @click="search(input)">搜索</el-button>
        </div>

      </el-menu>
    </div>
  </div>
</template>

<script>
  import axios from "axios"
  export default {
    name: "MarketHome",
    props: ['secondTile'],
    data() {
      return {
        input: ""
      }
    },
    methods: {
      search(input) {
        this.$router.push({
          path: "/main",  // 将要跳转的页面
          query: {
            data: input,
            classification: "search",
          }  // 传递的值
        })
        location.reload()  // 强制刷新
      },
      reload(){
        location.reload()  // 强制刷新
      }
    },
    mounted() {
      this.input = this.$route.query.data
      // console.log(returnCitySN["cip"])  // 显示ip
      // console.log(returnCitySN["cname"])  // 显示ip所处城市
      let formData = new FormData(); //初始化时将form Dom对象传入
      formData.append('ip', returnCitySN["cip"]);
      formData.append('city', returnCitySN["cname"]);
      axios.post("http://"+ this.urlIP + ":" + this.urlPort + "/http/main/saveIP", formData)
      .then((res)=>{
        // console.log(res.data)
      })


    }
  }
</script>

<style scoped>
  #search {
    /*position: relative;*/
    float: right;
    margin-top: 12px;
    margin-right: 10%;
    display: flex;
  }

  #search-input {
    float: right;
    width: 200px;
  }

  #search-button {
    float: right;
    margin-left: 1px;
    height: 37px;
  }
</style>
