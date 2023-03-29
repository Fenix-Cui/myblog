<template>
  <div>
    <MarketHome></MarketHome>
    <div class="main">
      <div class='content' v-html="content"></div>
    </div>
    <div class="input">
      <el-input id="comment-input" type="textarea" :rows="2" placeholder="请输入内容" v-model="comment"></el-input>
      <el-button id="comment-button" type="primary" round @click="submit_comment">提交评论</el-button>
    </div>
    <div class="comments">
      <p id="comments-words">评论区：</p><br/>
      <div v-for="count in comments_length">
        <div class="comments-lists">
          <!--        <p id="comment-blog-title">{{comments_blog_title[count-1]}}</p>-->
          <p id="comment-username"><i class="el-icon-user"></i>{{comments_username[count-1]}}</p>
          <p id="comment-create-time"><i class="el-icon-notebook-1"></i>{{comments_create_time[count-1]}}</p><br/>
          <a id="comment-content">{{comments_content[count-1]}}</a>
          <!--        <p id="comment-username-parent">{{comments_username_parent[count-1]}}</p>-->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from "axios"
  import MarketHome from "./MarketHome";

  export default {
    name: "Blog",
    components: {MarketHome},
    data() {
      return {
        author: "",
        category: "",
        content: "",
        id: "",
        love: "",
        message: "",
        title: "",
        create_time: "",
        update_time: "",
        comment: "",

        comments_blog_title: [],
        comments_username: [],
        comments_content: [],
        comments_create_time: [],
        comments_username_parent: [],
        comments_length: 0,
      };
    },
    methods: {
      submit_comment() {
        let formData = new FormData();
        formData.append("blog_title", "vue001");
        formData.append("username", this.$store.state.username);
        formData.append("content", this.comment);
        this.getDate();
        formData.append("create_time", this.date_now);
        formData.append("username_parent", "0001");
        axios.post("http://"+this.urlIP+":9000/http/main/addcomment", formData)
          .then((res)=>{
            console.log(res.data.message)
          })
      },
    },
    mounted() {
      let title = this.$route.query.title; //初始化时将form Dom对象传入
      let formData = new FormData(); //初始化时将form Dom对象传入
      formData.append('title', title);
      axios.post("http://"+ this.urlIP + ":" + this.urlPort + "/http/main/getOrderBlog", formData)
      .then((res)=>{
        this.id = res.data.id;
        this.title = res.data.title;
        this.author = res.data.author;
        this.category = res.data.category;
        this.content = res.data.content;
        this.create_time = res.data.create_time;
        this.update_time = res.data.update_time;
        this.love = res.data.love;
      })
      let formData02 = new FormData(); //初始化时将form Dom对象传入
      formData02.append('title', title);
      axios.post("http://"+ this.urlIP + ":" + this.urlPort + "/http/main/getComments", formData02)
      .then((res)=>{
        for(let i = 0;i < res.data.length; i++){
          this.comments_blog_title.push(res.data[i]["blog_title"]);
          this.comments_username.push(res.data[i]["username"]);
          this.comments_content.push(res.data[i]["content"]);
          this.comments_create_time.push(res.data[i]["create_time"]);
          this.comments_username_parent.push(res.data[i]["username_parent"]);
        }
        this.comments_length = res.data.length;
      })
    }
  }
</script>

<style >
 .content{
   /*text-align: center;*/
   margin: 0 20% 0 20%;
   word-break: break-all;
   white-space: normal;
   border-style: solid;
   border-color: rgba(0,0,0,0.5);
   padding: 1.5% 3%;
   background-color: rgba(255,255,255,1);
 }
 .input{
   margin: 100px 20% 0 20%;
 }

 code {
   white-space: normal;
   word-break: break-all;
 }
 #comment-input{
   margin-top: -55px;
   width: 100%;
   height: 100px;
  }
 #comment-button{
   margin-top: 6px;
   float: right;
 }

 .comments{
   background-color: rgba(255,255,255,1);
   margin: 50px 20% 0px 20%;
   border-style: solid;
   border-color: rgba(0,0,0,0.5);
 }
 #comments-words{
   padding-bottom: 0px;
   padding-left: 3%;
   margin-top: 20px;
   font-size: 22px;
   color: #333;
   line-height: 30px;
   font-weight: 700;
 }
  .comments-lists{
    padding: 1.5% 3%;
    margin-bottom: 10px;
  }
  #comment-username{
    float: left;
  }
  #comment-create-time{
    float: right;
  }

</style>
