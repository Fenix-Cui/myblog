<template>
  <div>
    <el-header>
      <MarketHome></MarketHome>
    </el-header>
    <div id="center">
      <el-card shadow="hover" class="box-card">
        <div slot="header" class="clearfix">
          <span style="font-size: 30px;color: #91c02f">我的信息</span>
          <el-button style="float: right; " type="primary" @click="change">修改信息</el-button>
        </div>
        <div class="text item">
          <el-form :model="param" ref="ruleForm" label-width="140px" class="demo-ruleForm">
            <el-form-item label="用户名" prop="username">
              <el-input v-model="param.username" placeholder="请输入用户名" :disabled="true" style="width: 60%;"></el-input>
            </el-form-item>
            <el-form-item label="年龄" prop="age">
              <el-input placeholder="请输入年龄" v-model="param.age" style="width: 60%;"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="sex">
              <el-select v-model="param.sex" placeholder="请选择">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"
                           style="width: 60%;"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="生日" prop="birthday">
              <el-date-picker v-model="param.birthday" type="date" placeholder="选择生日"
                              style="width: 60%;"></el-date-picker>
            </el-form-item>
            <!--            <el-form-item label="微信id" prop="open_it">-->
            <!--              <el-input-->
            <!--                placeholder="请输入微信id"-->
            <!--                v-model="param.open_id"-->
            <!--                :disabled="true"-->
            <!--                style="width: 60%;"-->
            <!--              >-->
            <!--              </el-input>-->
            <!--            </el-form-item>-->
            <el-form-item label="邮箱" prop="email">
              <el-input placeholder="请输入邮箱" v-model="param.email" style="width: 60%;"></el-input>
            </el-form-item>
            <el-form-item label="联系电话" prop="phone">
              <el-input placeholder="请输入联系电话" v-model="param.phone" style="width: 60%;"></el-input>
            </el-form-item>
          </el-form>
        </div>
      </el-card>
    </div>
  </div>


</template>

<script>
  import axios from "axios"
  import MarketHome from "./MarketHome";

  export default {
    name: "Myself",
    components: {
      MarketHome
    },
    data() {
      return {
        param: {
          username: this.$store.state.username,
          age: "",
          sex: "",
          birthday: "",
          // open_id: "12235552",
          email: "",
          phone: "",
        },
        options: [{
          value: '男',
          label: '男'
        }, {
          value: '女',
          label: '女'
        }],
      }
    },
    mounted() {
      if (this.$store.state.username == null) {
        this.$router.push(
          {
            path: '/login'
          })
      } else {
        let formData = new FormData();
        formData.append("username", this.$store.state.username)
        axios.post("http://"+ this.urlIP + ":" + this.urlPort + "/http/main/getUserInformation", formData)
          .then((res) => {
            this.param.age = res.data["age"]
            this.param.sex = res.data["sex"]
            this.param.birthday = res.data["birthday"]
            this.param.email = res.data["email"]
            this.param.phone = res.data["phone"]
          })
      }
    },
    methods: {
      change() {
        let formData = new FormData();
        formData.append("username", this.param.username)
        formData.append("age", this.param.age)
        formData.append("sex", this.param.sex)
        formData.append("birthday", this.param.birthday)
        formData.append("email", this.param.email)
        formData.append("phone", this.param.phone)
        axios.post("http://"+ this.urlIP + ":" + this.urlPort + "/http/main/updateUserInformation", formData)
          .then((res) => {
            this.$message({
              message: '修改成功',
              duration: 1500, // 显示时间
              type: 'success'
            })
          })
      }
    }
  }
</script>

<style scoped>
  #center {
    position: relative;
    margin-left: 30%;
    margin-top: 3%;
    width: 50%;
    height: 60%;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: flex-end;
    /*overflow-y: scroll;*/
    /*box-sizing: border-box;*/
  }

  .text {
    font-size: 25px;
    color: #777777;
  }

  .item {
    margin-bottom: 5px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  /*p{ line-height:30px}*/
  .box-card {
    width: 70%;
  }

  .el-card {
    background-color: rgba(255, 255, 255, 0);
  }
</style>
<style>
  .el-form-item__label {
    color: #42352b;
    font-size: 20px;
  }

  .el-form {
    margin-top: 5%;
  }

  .el-input__inner {
    background-color: rgba(255, 255, 255, 0);
    color: #42352b;
    font-size: 18px;
  }
</style>
