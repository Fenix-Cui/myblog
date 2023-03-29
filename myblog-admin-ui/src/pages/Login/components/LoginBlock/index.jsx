import React, { useState } from 'react';
import { Input, Message, Form, Divider, Checkbox, Icon, Notification, Button } from '@alifd/next';
import { useInterval } from './utils';
import styles from './index.module.scss';
import axios from "axios";

const openNotification = () => {
  const args = {
    title: '暂时不可用',
    content: '抱歉，未开放其他登录方式，请使用其他方式登录。',
    icon: 'prompt'
  };
  Notification.open(args);
};

const { Item } = Form;
const DEFAULT_DATA = {
  name: '',
  // eslint-disable-next-line @iceworks/best-practices/no-secret-info
  password: '',
  autoLogin: true,
  phone: '',
  code: '',
};

const LoginBlock = (
  props = {
    dataSource: DEFAULT_DATA,
  },
) => {
  const { dataSource = DEFAULT_DATA } = props;
  const [postData, setValue] = useState(dataSource);
  const [isRunning, checkRunning] = useState(false);
  const [isPhone, checkPhone] = useState(false);
  const [second, setSecond] = useState(59);
  useInterval(
    () => {
      setSecond(second - 1);

      if (second <= 0) {
        checkRunning(false);
        setSecond(59);
      }
    },
    isRunning ? 1000 : null,
  );

  const formChange = (values) => {
    setValue(values);
  };

  const sendCode = (values, errors) => {
    if (errors) {
      return;
    } // get values.phone

    checkRunning(true);
  };

  // 提交表单
  const handleSubmit = (values, errors) => {
    if (errors) {
      console.log('errors', errors);
      return;
    }
    // console.log('values:', values);

    let formData = new FormData(); //初始化时将form Dom对象传入
    formData.append('username', values.name);
    formData.append('password', values.password);
    axios.post("http://localhost:9000//http/main/getLoginResult", formData).then((res)=>{
        if(res.data.message == "success"){

          Message.success('登录成功');
          window.location.href="/#/dashboard/analysis"
        }else{
          Message.error('用户名或密码错误');
        }
      }
    )
  };

  // 手机号登录表单
  const phoneForm = (
    <>
      <Item format="tel" required requiredMessage="必填" asterisk={false}>
        <Input
          name="phone"
          innerBefore={
            <span className={styles.innerBeforeInput}>
              +86
              <span className={styles.line} />
            </span>
          }
          maxLength={20}
          placeholder="手机号"
        />
      </Item>
      <Item
        required
        requiredMessage="必填"
        style={{
          marginBottom: 0,
        }}
      >
        <Input
          name="code"
          innerAfter={
            <span className={styles.innerAfterInput}>
              <span className={styles.line} />
              <Form.Submit
                text
                type="primary"
                style={{
                  width: 64,
                }}
                disabled={!!isRunning}
                validate={['phone']}
                onClick={sendCode}
                className={styles.sendCode}
              >
                {isRunning ? `${second}秒后再试` : '获取验证码'}
              </Form.Submit>
            </span>
          }
          maxLength={20}
          placeholder="验证码"
        />
      </Item>
    </>
  );
  // 账号密码登录表单
  const accountForm = (
    <>
      <Item required requiredMessage="必填">
        <Input name="name" maxLength={20} placeholder="用户名" />
      </Item>
      <Item
        required
        requiredMessage="必填"
        style={{
          marginBottom: 0,
        }}
      >
        <Input.Password name="password" htmlType="password" placeholder="密码" />
      </Item>
    </>
  );

  const byAccount = () => {
    checkPhone(false);
  };

  const byForm = () => {
    checkPhone(true);
  };

  return (
    <div className={styles.LoginBlock}>
      <div className={styles.innerBlock}>
        <a href="#">
          <img
            className={styles.logo}
            src="https://img.alicdn.com/tfs/TB1KtN6mKH2gK0jSZJnXXaT1FXa-1014-200.png"
            alt="logo"
          />
        </a>
        <div className={styles.desc}>
          <span onClick={byAccount} className={isPhone ? undefined : styles.active}>
            账户密码登录
          </span>
          <Divider direction="ver" />
          <span onClick={byForm} className={isPhone ? styles.active : undefined}>
            手机号登录
          </span>
        </div>

        <Form value={postData} onChange={formChange} size="large">
          {isPhone ? phoneForm : accountForm}

          <div className={styles.infoLine}>
            <Item
              style={{
                marginBottom: 0,
              }}
            >
              <Checkbox name="autoLogin" className={styles.infoLeft} disabled>
                自动登录
              </Checkbox>
            </Item>
            <div>
              <a href="/" className={styles.link}>
                忘记密码
              </a>
            </div>
          </div>

          <Item
            style={{
              marginBottom: 10,
            }}
          >
            <Form.Submit type="primary" onClick={handleSubmit} className={styles.submitBtn} validate>
              登录
            </Form.Submit>
          </Item>
          <div className={styles.infoLine}>
            <Button type="secondary" size="small" duration="5" onClick={openNotification}>
              其他登录方式
            </Button>

            <a href="/#/user/register" className={styles.link}>
              注册账号
            </a>
          </div>
        </Form>
      </div>
    </div>
  );
};

export default LoginBlock;
