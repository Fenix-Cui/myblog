<template>
  <div class="ms-login">


    <div class="ms-title">后台管理系统</div>
    <el-form :model="param" ref="login1" label-width="0px" class="ms-content">
      <el-form-item prop="username">
        <el-input v-model="param.username" placeholder="username">
          <div slot="prepend">用户名</div>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          type="password"
          placeholder="password"
          v-model="param.password"
          @keyup.enter.native="submitForm()"
        >
          <div slot="prepend">密&nbsp;&nbsp;&nbsp;&nbsp;码</div>
        </el-input>
      </el-form-item>

      <div class="login-btn">
        <el-button type="primary" @click="submitForm()">登录</el-button>
        <el-button type="primary" @click="registerForm()">注册</el-button>
      </div>
    </el-form>


  </div>
</template>

<script>
  import axios from "axios";

  export default {
    name: "Login",
    data() {
      return {
        param: {
          username: '',
          password: '',
        },
      }
    },
    methods: {
      submitForm() {
        this.$store.state.username = this.param.username
        let formData = new FormData();
        formData.append("username", this.param.username);
        formData.append("password", this.param.password);
        axios.post('http://"+ this.urlIP + ":" + this.urlPort + "/http/main/getLoginResult', formData)
          .then((response) => {
            if (response.data.message == "success") {
              this.$message({
                message: '登陆成功',
                duration: 1000, // 显示时间
                type: 'success'
              })
              this.$router.push(
                {
                  path: '/main'
                }
              )
            }
            else{
              this.$message({
                message: '用户名或密码错误',
                duration: 3000, // 显示时间
                type: 'error'
              })
            }
          })
      },
      registerForm() {
        this.$router.push({path: '/register'});
      },
    }
  }
</script>

<style scoped>
  .ms-login {
    position: absolute;
    left: 50%;
    top: 40%;
    width: 350px;
    height: 360px;
    margin: -190px 0 0 -175px;
    border-radius: 5px;
    background: rgba(255, 255, 255, 0.3);
    overflow: hidden;
  }

  .ms-title {
    width: 100%;
    line-height: 50px;
    text-align: center;
    font-size: 20px;
    color: #284d57;
    border-bottom: 1px solid #ddd;
  }

  .ms-content {
    padding: 30px 30px;
  }

  .login-btn {
    text-align: center;
  }

  .login-btn button {
    width: 100%;
    height: 36px;
    margin-bottom: 10px;
  }

  .el-button + .el-button {
    margin-left: 0px;
  }

  .login-tips {
    font-size: 12px;
    line-height: 30px;
    color: #fff;
  }
</style>
