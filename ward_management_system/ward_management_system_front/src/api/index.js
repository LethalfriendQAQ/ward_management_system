import axios from 'axios'
import { ElMessage } from 'element-plus';
import router from '@/router';

//返回的是axios实例
const service = axios.create({
  baseURL: 'http://localhost:8080'
});

//Axios的请求的拦截器
service.interceptors.request.use(function (config) {
  // 如果不是登录请求就要在请求头中添加token
  if (!config.url.startsWith("/login")) {
    config.headers.token = sessionStorage.getItem('token');
  }
  return config;
}, function (error) {
  // 对请求错误做些什么
  return Promise.reject(error);
});



//axios的响应拦截器
service.interceptors.response.use(resp => {
  return resp.data;
}, error => {
  if (error.status == 403) {
    ElMessage.error({
      message: '令牌错误，请重新登录',
      type: 'success',
      duration: 1200,
      onClose: () => {
        //删除sessionStorage中的token
        sessionStorage.removeItem('token');
        //跳转到登录页
        router.push('/login');
      }
    });
  }
});
export default service;