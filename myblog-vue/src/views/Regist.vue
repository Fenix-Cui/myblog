<template>
  <div class="login-wrap">
    <div class="ms-login">
      <div class="ms-title">欢迎加入我们</div>
      <el-form :model="param" ref="login1" label-width="0px" class="ms-content">
        <el-form-item prop="username">
          <el-input v-model="param.username" placeholder="请输入用户名">
            <div slot="prepend">用户名</div>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input placeholder="请输入密码" v-model="param.password">
            <div slot="prepend">密&nbsp;&nbsp;&nbsp;&nbsp;码</div>
          </el-input>
        </el-form-item>
        <el-form-item prop="age">
          <el-input v-model="param.age" placeholder="请输入年龄">
            <div slot="prepend">年龄</div>
          </el-input>
        </el-form-item>
        <!--        <el-form-item prop="open_id">-->
        <!--          <el-input v-model="param.open_id" placeholder="请输入微信id">-->
        <!--            <div slot="prepend">微信id</div>-->
        <!--          </el-input>-->
        <!--        </el-form-item>-->
        <el-form-item prop="sex">
          <el-radio-group v-model="param.sex">

            <el-radio-button label="男"></el-radio-button>
            <el-radio-button label="女"></el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item prop="phone">
          <el-input v-model="param.phone" placeholder="请输入联系电话">
            <div slot="prepend">联系电话</div>
          </el-input>
        </el-form-item>
        <el-form-item prop="email">
          <el-input v-model="param.email" placeholder="请输入邮箱">
            <div slot="prepend">邮箱</div>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-date-picker v-model="param.birthday" align="right"
                          type="date" placeholder="你的生日">
          </el-date-picker>
        </el-form-item>


        <div class="login-btn">
          <el-button type="primary" @click="registerForm()">确认</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  import {formatDate} from "element-ui/lib/utils/date-util";

  export default {
    name: "Register",
    data: function () {
      return {
        // pickerOptions: {
        //   disabledDate(time) {
        //     return time.getTime() > Date.now();
        //   },
        //   shortcuts: [{
        //     text: '今天',
        //     onClick(picker) {
        //       picker.$emit('pick', new Date());
        //     }
        //   }]
        // },
        param: {
          // open_id: '',
          username: '',
          password: '',
          age: '',
          sex: '',
          phone: '',
          email: '',
          birthday: '',
        },

      };
    },
    methods: {
      registerForm() {
        let formData = new FormData();
        formData.append("username", this.param.username);
        formData.append("password", this.param.password);
        formData.append("age", this.param.age);
        formData.append("sex", this.param.sex);
        formData.append("phone", this.param.phone);
        formData.append("email", this.param.email);
        formData.append("birthday", formatDate(this.param.birthday, 'yyyy-MM-dd'));
        axios.post("http://"+ this.urlIP + ":" + this.urlPort + "/http/main/getUserRegisterResult", formData).then((res) => {
          if (res.data.message == "error") {
            this.$message({
              message: '已存在该用户名',
              type: 'error'
            });
          }
          if (res.data.message == "success") {
            this.$message({
              message: '注册成功',
              type: 'success'
            });
            this.$router.push({path: '/login'});
          }
        })
      }
    }
  }
</script>

<style scoped>
  .login-wrap {
    position: absolute;
    width: 100%;
    height: 100%;
    /*background-image: url(../assets/background.png);*/
    /*background-size: 100%;*/
  }

  .ms-title {
    width: 100%;
    line-height: 50px;
    text-align: center;
    font-size: 20px;
    color: #919191;
    border-bottom: 1px solid #ddd;
  }

  .ms-login {
    position: absolute;
    left: 45%;
    top: 30%;
    width: 530px;
    margin: -190px 0 0 -175px;
    border-radius: 5px;
    background: #ffffff;
    overflow: hidden;
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
