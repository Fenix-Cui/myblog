<template>
  <div>
    <div class="block">
      <span class="demonstration">默认 Hover 指示器触发</span>

      <el-carousel height="530px">
        <el-carousel-item v-for="img_list in img_lists" :key="img_list">
          <el-image :src="img_list" fit="scale-down" @click="img_url(img_list)"></el-image>
        </el-carousel-item>
      </el-carousel>
    </div>
  </div>
</template>

<script>
    export default {
      name: "Classification",
      data(){
        return{
          img_lists:[
            "static/images/java.png",
            "static/images/python.png"
          ]
        }
      },
      methods:{
        img_url(value){
          // 获取图片对应的参数
          let index = value.lastIndexOf("\/");
          let data = value.substring(index + 1,value.length-4);

          this.$router.push({
            path: "/main",  // 将要跳转的页面
            query: {data: data}  // 传递的值
          })
        }
      }
    }

</script>

<style scoped>
  .el-carousel{
    align-items: center;
    justify-content: center;
    text-align: center;
    width: 50%;
  }
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
  /*img{*/
  /*  !*width: 100%;*!*/
  /*  height: inherit;*/
  /*}*/
</style>
