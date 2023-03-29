<template>
  <div>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    {{data}}
  </div>
</template>

<script>
  import axios from "axios";

  export default {
    name: "Search",
    data() {
      return {
        data: ""
      }
    },
    mounted() {
      this.data = this.$route.query.data;

      let formData = new FormData(); //初始化时将form Dom对象传入
      formData.append("data", data);
      axios.post("http://"+ this.urlIP + ":" + this.urlPort + "/http/main/", formData)
        .then((res) => {

        })
    },
  }
</script>

<style scoped>

</style>
