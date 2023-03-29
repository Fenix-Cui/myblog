<template>
  <el-container class="blog">
    <el-header>
      <MarketHome></MarketHome>
    </el-header>
    <el-container class="main-aside-footer">
      <el-main>
        <div v-for="count in main_data_length">
          <div class="blog-lists">
            <a id="blog-title" href="" :title="count" @click="titleClick(main_data_title[count-1])">
              <i class="el-icon-reading"></i>
              {{main_data_title[count-1]}}
            </a><br/><br/>
            <p id="blog-category"><i class="el-icon-c-scale-to-original"></i>{{main_data_category[count-1]}}</p>
            <p id="blog-author"><i class="el-icon-user"></i>{{main_data_author[count-1]}}</p><br/>
            <!--v-html='main_data[count]'-->
            <p id="blog" v-html="main_data_content[count-1]">{{}}</p><br/>
            <p id="blog-createtime"><i class="el-icon-notebook-1"></i>创建日期：{{main_data_create_time[count-1]}}</p><br/>
            <a id="blog-like-count"><i class="el-icon-thumb"></i>{{main_data_like[count-1]}}</a>
            <a id="blog-comment-count"><i class="el-icon-chat-dot-square"></i>{{100}}</a>
            <a id="blog-click-count"><i class="el-icon-view"></i>{{100}}</a>
            <p id="blog-updatetime"><i class="el-icon-notebook-2"></i>修改日期：{{main_data_update_time[count-1]}}</p>
          </div>
        </div>
      </el-main>
      <el-container class="aside-footer">
        <el-aside width="100%">
          <div class="head-background-image">
            <el-image
              style="width: inherit;
              height: 125px;"
              src="static/images/head-background-image.jpg"
              :fit="fits[2]"></el-image>
          </div>
          <div class="head-image" key="cover">
            <el-image
              style="width: 100px;
              height: 100px;
              border: 5px solid #e8e8e8;
              border-radius: 50%;"
              src="static/images/head-image.gif"></el-image>
          </div>
          <div class="head-words01">
            <p id="head-words01-01">Fenix</p>
            <p id="head-words01-02">执笔如仗剑，挥墨至天涯</p>
          </div>
          <el-divider></el-divider>
          <div>
            <p>联系我：QQ1260928312</p>
          </div>
          <el-divider></el-divider>
          <div class="head-words02">
            <p id="head-words02-01">推荐阅读</p>
            <div id="head-words02-01-01"><i class="el-icon-paperclip"></i><a @click="orderClassification('GUI编程', 'search')">GUI编程</a></div>
            <el-divider></el-divider>
            <p id="head-words02-02">分类</p>
            <div id="head-words02-02-01"><i class="el-icon-paperclip"></i><a @click="orderClassification()">全部</a></div>
            <div id="head-words02-02-02"><i class="el-icon-paperclip"></i><a
              @click="orderClassification('Java')">Java</a></div>
            <div id="head-words02-02-03"><i class="el-icon-paperclip"></i><a @click="orderClassification('Python')">Python</a>
            </div>
            <el-divider></el-divider>
            <p id="head-words02-03">友链</p>
            <div class="links" v-for="count in links_length">
              <p id="links-links-name">{{links_links_name[count-1]}}</p>
              <a id="links-href" :href=links_href[count-1]><i
                class="el-icon-paperclip"></i>{{links_href[count-1]}}</a><br/>
            </div>
            <el-divider></el-divider>
          </div>
        </el-aside>
        <!--footer-->
        <el-footer>
          <p class="copyright">&copy; Created. Design: Fenix.Cui</p>
          <!-- 备案 -->
          <div style="margin:0 auto; padding:20px 0;">
            <img src="static/images/filing-icon.png"/><a target="_blank"
                                                         href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11010502043277"
                                                         style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"><img
            src="" style="float:left;"/>
            <p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:#939393;">京公网安备
              11010502043277号</p></a>
          </div>
        </el-footer>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
  import axios from "axios"
  import MarketHome from "./MarketHome";

  export default {
    name: "Main",
    components: {MarketHome},
    data() {
      return {
        activeIndex: '1',
        main_data_length: 0,
        main_data: [],
        main_data_title: [],
        main_data_category: [],
        main_data_author: [],
        main_data_content: [],
        main_data_update_time: [],
        main_data_create_time: [],
        main_data_like: [],
        main_data_comment: [],
        main_data_click: [],

        links_length: 0,
        links_links_name: [],
        links_href: [],

        fits: ['fill', 'contain', 'cover', 'none', 'scale-down'],

      };
    },
    methods: {
      titleClick(title) {
        console.log(title)
        this.$router.push({
          path: "/blog",  // 将要跳转的页面
          query: {title: title}  // 传递的值
        })
      },
      rTime(date) {
        let json_date = new Date(date).toJSON();
        return new Date(new Date(json_date) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
      },
      orderClassification(data) {
        console.log(data)
        this.$router.push({
          path: "/main",  // 将要跳转的页面
          query: {data: data}  // 传递的值
        })
        location.reload()  // 强制刷新
      },
      orderClassification(data, classification) {
        console.log(data)
        this.$router.push({
          path: "/main",  // 将要跳转的页面
          query: {
            data: data,
            classification: classification,
          }  // 传递的值
        })
        location.reload()  // 强制刷新
      }
    },
    mounted() {
      let formData = new FormData(); //初始化时将form Dom对象传入
      if (this.$route.query.classification == "search") {
        formData.append('input', this.$route.query.data);
        axios.post("http://"+ this.urlIP + ":" + this.urlPort + "/http/main/searchMain", formData)
          .then((res) => {
            for (let i = 0; i < res.data.length; i++) {
              this.main_data.push(res.data[i])
              this.main_data_title.push(res.data[i]["title"])
              this.main_data_category.push(res.data[i]["category"])
              this.main_data_author.push(res.data[i]["author"])
              this.main_data_content.push(res.data[i]["content"].slice(0, 500))
              this.main_data_update_time.push(this.rTime(res.data[i]["update_time"]))
              this.main_data_create_time.push(this.rTime(res.data[i]["create_time"]))
              this.main_data_like.push(res.data[i]["love"])
              this.main_data_comment.push(res.data[i]["comment"])
              this.main_data_click.push(res.data[i]["click"])
            }
            this.main_data_length = res.data.length
          })
        axios.post("http://"+ this.urlIP + ":" + this.urlPort + "/http/main/getFriendLinks")
          .then((res) => {
            for (let i = 0; i < res.data.length; i++) {
              this.links_links_name.push(res.data[i]["links_name"])
              this.links_href.push(res.data[i]["href"])
            }
            this.links_length = res.data.length
          })
      }
      else{
        if (this.$route.query.data == undefined) {
          formData.append('data', '$undefined$')
        } else {
          formData.append('data', this.$route.query.data);
        }
        axios.post("http://"+ this.urlIP + ":" + this.urlPort + "/http/main/getMainTitle", formData)
          // .then((res)=>{
          // this.mainTitle = res.data
          // })
          .then((res) => {
            for (let i = 0; i < res.data.length; i++) {
              this.main_data.push(res.data[i])
              this.main_data_title.push(res.data[i]["title"])
              this.main_data_category.push(res.data[i]["category"])
              this.main_data_author.push(res.data[i]["author"])
              this.main_data_content.push(res.data[i]["content"].slice(0, 500))
              this.main_data_update_time.push(this.rTime(res.data[i]["update_time"]))
              this.main_data_create_time.push(this.rTime(res.data[i]["create_time"]))
              this.main_data_like.push(res.data[i]["love"])
              this.main_data_comment.push(res.data[i]["comment"])
              this.main_data_click.push(res.data[i]["click"])
            }
            this.main_data_length = res.data.length
          })
        axios.post("http://"+ this.urlIP + ":" + this.urlPort + "/http/main/getFriendLinks")
          .then((res) => {
            for (let i = 0; i < res.data.length; i++) {
              this.links_links_name.push(res.data[i]["links_name"])
              this.links_href.push(res.data[i]["href"])
            }
            this.links_length = res.data.length
          })
      }
    }
  }

</script>

<style scoped lang="css">
  /*main*/
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    width: 45%;
    float: left;
    /*text-align: center;*/
    /*line-height: 160px;*/
  }

  .main-aside-footer {
    margin: 0 10% 0 10%;
  }

  .blog-lists {
    /*height: 200px;*/
    margin-bottom: 5px;
    border-style: solid;
    border-width: 2px;
    border-color: rgba(0, 0, 0, 0.3);
    padding: 10px;
  }

  #blog-title {
    float: left;
    font-size: 28px;
    color: #333;
    text-decoration: none;
  }

  #blog-category {
    float: left;
    padding-top: 8px;
    font-size: 20px;
  }

  #blog-author {
    float: right;
    padding-top: 8px;
    font-size: 20px;
  }

  #blog {
    color: #1f1f1f;
    font-size: 15px;
    padding-top: 15px;
  }

  #blog-createtime {
    float: right;
  }

  #blog-updatetime {
    float: right;
  }

  /*aside*/
  .el-aside {
    background-color: rgba(255, 255, 255, 1);
    color: #333;
    text-align: center;
    /*line-height: 200px;*/
    height: auto;
  }

  .head-background-image {
    position: absolute;
    margin-right: 10%;
    z-index: 1;
  }

  .head-image {
    margin-top: 150px;
    z-index: 2;
  }

  .head-words01 {

  }

  #head-words01-01 {
    margin-top: 20px;
    text-align: center;
    font-size: 22px;
    color: #333;
    line-height: 30px;
    font-weight: 700;
  }

  #head-words01-02 {
    font-size: 15px;
    color: #878d99;
    line-height: 30px;
    font-weight: 100;
  }

  .head-words02 {
    text-align: left;
  }

  #head-words02-01, #head-words02-02, #head-words02-03 {
    font-size: 18px;
    font-weight: bolder;
    padding: 13px 20px;
    line-height: 18px;
    text-align: left;
    border-left: 5px solid #409eff;
    margin-bottom: 3%;
  }

  #head-words02-01-01, #head-words02-02-01,
  #head-words02-02-02, #head-words02-02-03,
  #links-links-name, #links-href {
    margin: 0 5% 0 5%;
  }

  #links-links-name {
    float: left;
  }

  #links-href {
    float: left;
  }

  .aside-footer {
    float: right;
    margin-left: 30px;
  }

  /*footer*/
  .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
    width: 100%;

  }


  /*.main-blog{*/
  /*  margin: 0 10% 900px 10%;*/
  /*  !*background-color: #1ad78c;*!*/
  /*}*/
  /*.blogs{*/
  /*  margin: 0 40% 0 10%;*/
  /*  background-color: #1b84e0;*/
  /*  float: left;*/
  /*}*/
  /*.right-others{*/
  /*  !*margin: 0 10% 0 10%;*!*/
  /*  float: right;*/
  /*}*/

  /*!*main*!*/
  /*.main, .title_list {*/
  /*  background-color: rgba(255,255,255,0.2);*/
  /*  color: #000000;  !*文本颜色*!*/
  /*  text-align: center;*/
  /*  !*line-height: 160px;*!*/
  /*  width: 100%;*/
  /*}*/
  /*.title_list{*/
  /*  !*background-color: coral;*!*/
  /*  !*width: 50%;*!*/
  /*  height: auto;*/
  /*  overflow: hidden;*/
  /*  !*float: left;*!*/
  /*}*/

  /*!*footer*!*/
  /*.footer {*/
  /*  text-align: center;*/
  /*  overflow: unset;*/
  /*  !*position: absolute;*!*/
  /*  bottom: 0;*/
  /*  width: 100%;*/
  /*  !*height: 60px;*!*/
  /*}*/
</style>
