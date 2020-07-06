import Axios from 'axios';

import * as config from '@/config';

const { BASE_API_ROOT } = config;

const axios = Axios.create();

axios.defaults.baseURL = BASE_API_ROOT;
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';

const formatResponse = (response) => {
  const data = response.data;
  if(data.errCode === 0) {
    return data.data;
  } else {
    return Promise.reject(data);
  }
};

const ERROR_CODE = {
  SERVER_ERROR: 500,
  NETWORK_ERROR: 503,
  AUTHORIZE_ERROR: 401
};

const formatAjaxError = (error) => {
  const response = error.response;
  if(response && response.status === ERROR_CODE.SERVER_ERROR) {
    return Promise.reject({
      message: '服务器内部错误，请稍后重试！',
      status: response.status
    });
  }
  if(response && response.status === ERROR_CODE.NETWORK_ERROR) {
    return Promise.reject({
      message: '网络问题，请稍后重试！'
    });
  }
  if(response && response.status === ERROR_CODE.AUTHORIZE_ERROR) {
    // window.location.href = window.location.origin + '/login';
  }
  return Promise.reject(response.data || {
    message: '服务器开小差了呢！'
  });
};

//Add a response interceptor
axios.interceptors.response.use(formatResponse, formatAjaxError);

export default function axiosServices(root) {
  return (method, route, data) => {
    const params = method === 'get' ? { params: data } : data;
    return axios[method](
      `${root}/${route}`,
      params
    );
  };
}
