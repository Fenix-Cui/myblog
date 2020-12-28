<template>
  <div>

    <!-- Main -->
    <div class="main">
      <span>{{blog}}</span>

    </div>
  </div>
</template>

<script>
  import axios from "axios"

  export default {
    name: "Blog",
    data() {
      return {
        blog: []
      };
    },
    mounted() {
      axios({
        methods: 'get',
        url: '/api/http/main/getBlog',
        params: {
          id: 1  // 修改为访客点击的博客id（待修改）
        }
      }).then((res => {
        this.blog = res.data
      }))
      // axios.get("/api/http/main/getBlog").then((res)=>{
      //   this.blog = res.data
      // })
    }
  }
</script>

<style scoped>

</style>
