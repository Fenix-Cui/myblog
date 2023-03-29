<template>
  <div>
    <MarketHome></MarketHome>
    <div class="block">
      <p class="tips">xxxxxxxxxxxxxxxxxxx</p>

      <el-carousel indicator-position="outside" v-bind:height="this.h_px+'px'" :style="{width: w_px()+'px'}">
        <el-carousel-item v-for="img_list in img_lists" :key="img_list">
          <el-image :src="img_list" @click="img_url(img_list)"></el-image>
        </el-carousel-item>
      </el-carousel>

      <div class="container">
        <div class="comments-box" v-show="isComments" v-for="comment of comments">
          <ul>
            <li>
              <p>111</p>
              <p>111</p>
              <p>111</p>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    import axios from "axios";
    import MarketHome from "./MarketHome";

    export default {
        name: "Classification",
        components: {MarketHome},
        data() {
            return {
                img_lists: [
                    "static/images/java.png",
                    "static/images/python.png"
                ],
                comment: '',
                comments: [],
                h_px: 400,
            }
        },
        mounted(){
            this.getDate();
        },
        methods: {
            img_url(value) {
                // 获取图片对应网址的参数，如java python...
                let index = value.lastIndexOf("\/");
                let data = value.substring(index + 1, value.length - 4);

                this.$router.push({
                    path: "/main",  // 将要跳转的页面
                    query: {data: data}  // 传递的值
                })
            },
            // 使宽度和高度等比例缩放
            w_px() {
                let w_line = this.h_px / 0.5625
                return w_line
            },
            toggleFinish(item) {
                item.isFinished = !item.isFinished;
            },
            submit_comment() {
                let formData = new FormData();
                formData.append("blog_title", "vue001");
                formData.append("username", "zrt");
                formData.append("content", this.comment);
                this.getDate();
                formData.append("create_time", this.date_now);
                formData.append("username_parent", "0001");
                axios.post("http://"+ this.urlIP + ":" + this.urlPort + "/http/main/addcomment", formData)
                    .then((res)=>{
                        console.log(res.data.message)
                    })
            },
            getDate(){
                var aData = new Date();
                console.log(aData) //Wed Aug 21 2019 10:00:58 GMT+0800 (中国标准时间)

                this.date_now =
                    aData.getFullYear() + "-" + (aData.getMonth() + 1) + "-" + aData.getDate();

            }
        }
    }

</script>

<style scoped>
  .tips {
    text-align: center;
  }

  .container {
    width: 100%;
    height: 300px;
  }

  .el-image {
    width: 100%;
    height: 100%;
  }

  .el-image img {
    width: 100%;
    height: 100%;
  }

  .el-carousel {
    margin: 0 auto;

  }

  .el-carousel {
    align-items: center;
    justify-content: center;
    text-align: center;
  }

  .el-carousel__item {
    color: #475669;
    font-size: 14px;
    opacity: 0.50;
    line-height: 150px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
  #comment-input{
    float: left;
  }

  /*img{*/
  /*  !*width: 100%;*!*/
  /*  height: inherit;*/
  /*}*/
</style>
