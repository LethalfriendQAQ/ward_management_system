import axios from 'axios'
import { ElMessage } from 'element-plus';
import router from '@/router';

//返回的是axios实例
const service = axios.create({
  baseURL: 'http://localhost:8080'
});

//axios的响应拦截器
service.interceptors.response.use(resp => {
    return resp.data;
}, error => {
    return Promise.reject(error);
})
export default service;