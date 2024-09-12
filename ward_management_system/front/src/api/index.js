import axios from 'axios'
import { ElMessage } from 'element-plus';
import router from '@/router';
import { useTokenStore } from "@/stores/token.js";


//返回的是axios实例
const service = axios.create({
  baseURL: 'http://localhost:8080'
});

//Axios的请求的拦截器
service.interceptors.request.use(function (config) {
  const tokenStore = useTokenStore();
  // 如果不是登录请求就要在请求头中添加token
  if (!config.url.startsWith("/login")) {
    //将Store中的token放在请求头当中
    config.headers.token = tokenStore.tokenStr;
  }
  return config;
}, function (error) {
  // 对请求错误做些什么
  return Promise.reject(error);
});



//axios的响应拦截器
service.interceptors.response.use(resp => {
  //获取续期的jwt
  let token = resp.headers.token;
  //将续期的jwt放在store中
  const tokenStore = useTokenStore();
  tokenStore.update(token);
  return resp.data;
}, error => {
  if (error.status == 403) {
    ElMessage.error({
      message: '令牌错误，请重新登录',
      type: 'success',
      duration: 1200,
      onClose: () => {
        //将store中的token设置为初值
        const tokenStore = useTokenStore();
        tokenStore.$reset();
        //跳转到登录页
        router.push('/login');
      }
    });
  }
});
export default service;