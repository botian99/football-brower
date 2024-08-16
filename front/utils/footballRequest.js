import axios from 'axios'
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
// 创建axios实例
const service = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  baseURL: '',
  // 超时
  timeout: 10000
})


// request拦截器
service.interceptors.request.use(config => {
  config.headers['X-Auth-Token'] = '7faf83161ef143e19bb1c34ccf194d47'
  return config;
}, error => {
  console.log(error)
  Promise.reject(error)
})
export default service
