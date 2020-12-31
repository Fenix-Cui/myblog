import React, { useState } from 'react';
import { Input, Form, Box, Button, Card, DatePicker, Message, Radio, Upload } from '@alifd/next';
import styles from './index.module.scss';
import axios from "axios";
import qs from 'qs'


// import { DatePicker } from '@alifd/next';
import moment from 'moment';
const { RangePicker } = DatePicker;
const onChange = (value) => console.log(value);
const onOk = (value) => console.log('onOK:', value.format('YYYY-MM-DD HH:mm:ss'));
const onRangeOk = (value) => console.log('onOk: [%s, %s]', value[0].format('YYYY-MM-DD HH:mm:ss'), value[1].format('YYYY-MM-DD HH:mm:ss'));

const defaultTimeValues = [moment('09:00:00', 'HH:mm:ss', true), moment('23:59:59', 'HH:mm:ss', true)];



const FormItem = Form.Item;
const formItemLayout = {
  colSpan: 12,
};
const DEFAULT_DATA = {
  type: 'private',
};

const DEFAULT_ON_SUBMIT = (values, errors) => {
  if (errors) {
    console.log('errors', errors);
    return;
  }

  // 提交位置
  console.log('values:', values);
  submitMD(values);
  Message.success('提交成功');
};

let save_path = "";

const submitMD = (values) => {
  // let name = document.querySelector("#name").value
  let name = values.name;
  let category = values.category;
  let author = values.author;
  let date_create = values.date[0].format('YYYY-MM-DD HH:mm:ss');
  let date_update = values.date[1].format('YYYY-MM-DD HH:mm:ss');

  let blog_data = new FormData();
  blog_data.append("name", name);
  blog_data.append("category", category);
  blog_data.append("author", author);
  blog_data.append("date_create", date_create);
  blog_data.append("date_update", date_update);
  blog_data.append("save_path", save_path);

  let url="http://localhost:9000/http/main/saveBlog";
  axios.post(url, blog_data).then((res)=>{
    console.log(res.data);
  })

};

const crossdomain = (res) =>{
  console.log(res);
  let formData = new FormData(); //初始化时将form Dom对象传入
  formData.append('file', res);
  console.log("begin");
  axios.post("http://localhost:9000//http/main/saveFile", formData).then((res)=>{
    console.log(res.data.save_path);
    save_path = res.data.save_path;
    }
  )
};

const upload_success = (res) =>{
  console.log("success");
}
const upload_error = (res) =>{
  console.log("success");
}

const BasicForm = (props) => {
  const { dataSource = DEFAULT_DATA, onSubmit = DEFAULT_ON_SUBMIT, onCancel = () => {console.log("click cancel")} } = props;
  const [postData, setValue] = useState(dataSource);

  const formChange = (values) => {
    setValue(values);
  };

  return (
    <Card free>
      <Card.Content>
        <Form className={styles.BasicForm} responsive fullWidth value={postData} labelAlign="top" onChange={formChange}>
          <FormItem {...formItemLayout} label="文章标题：" required requiredMessage="必填">
            <Input placeholder="请输入文章的名称" name="name" />
          </FormItem>

          <FormItem {...formItemLayout} label="文章分类：" required requiredMessage="必填">
            <Input placeholder="请输入文章的分类" name="category" />
          </FormItem>

          <FormItem {...formItemLayout} label="创建日期->修改日期：" required requiredMessage="必填">
            <RangePicker showTime={{ defaultValue: defaultTimeValues, format: 'HH:mm:ss', minuteStep: 15 }} name={"date"}/>
            {/*<DatePicker.RangePicker  name="date" />*/}
          </FormItem>

          <FormItem {...formItemLayout} label="创建作者" required requiredMessage="必填">
            <Input placeholder="请输入作者姓名" name="author" />
          </FormItem>

          <FormItem {...formItemLayout} label="项目权限：">
            <Radio.Group name="type" aria-labelledby="authority of project">
              <Radio id="private" value="private">
                私密项目
              </Radio>
              <Radio id="internal" value="internal">
                内部项目
              </Radio>
              <Radio id="publish" value="publish">
                开放目
              </Radio>
            </Radio.Group>
          </FormItem>

          <FormItem {...formItemLayout} label="上传MarkDown文件：" required requiredMessage="必填">
            <Upload shape="card" name="markdown" dragable={true} action="http://localhost:9000/http/main/saveFile" beforeUpload={crossdomain} onSuccess={upload_success} onError={upload_error}>
              MarkDown
            </Upload>
          </FormItem>

          {/*<button onClick={crossdomain}>跨域</button>*/}

          {/*<FormItem {...formItemLayout} label="项目描述：">*/}
          {/*  <Input.TextArea placeholder="请输入项目详细信息" name="desc" />*/}
          {/*</FormItem>*/}

          <FormItem colSpan={12}>
            <Box spacing={8} direction="row">
              <Form.Submit type="primary" onClick={onSubmit} validate>
                提交
              </Form.Submit>
              <Button onClick={onCancel} type="secondary">
                取消
              </Button>
            </Box>
          </FormItem>
        </Form>
      </Card.Content>
    </Card>
  );
};

export default BasicForm;
