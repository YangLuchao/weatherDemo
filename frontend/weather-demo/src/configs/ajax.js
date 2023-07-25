
import axios from 'axios'
import { timeout, baseURL } from './config'
import { message } from 'antd'

const { CancelToken } = axios
axios.defaults.withCredentials = true;
export function parseQueryString(url) {
  const obj = {}
  if (url.indexOf('?') !== -1) {
    const str = url.split('?')[1]
    const strs = str.split('&')
    strs.map((item, i) => {
      const arr = strs[i].split('=')
      obj[arr[0]] = arr[1]
    })
  }
  return obj
}


let baseConfig = {
  url: '/',
  method: 'post', 
  baseURL: '',
  headers: {
    'Content-Type': 'application/json',
  },
  params: {},
  data: {},
  timeout: '',
  withCredentials: true, 
  responseType: 'json', 
  maxContentLength: 2000,
  validateStatus(status) {
    return status >= 200 && status < 300 
  },
}

baseConfig = { ...baseConfig, timeout: timeout, baseURL: baseURL }

export const oftenFetchByPost = (api, options) => {
  if (typeof api === 'function') return api
  return (...rest) => { 
    const data = rest[0] || {}
    let success = null
    let failure = null
    let config = null
    for (let i = 1; i < rest.length; i += 1) {
      if (typeof rest[i] === 'function') {
        if (!success) {
          success = rest[i]
        } else {
          failure = rest[i]
        }
      }
      if (Object.prototype.toString.call(rest[i]) === '[object Object]') {
        config = rest[i]
      }
    }

    const hooks = {
      abort: null,
    }
    const cancelToken = new CancelToken((c) => { hooks.abort = c })
    baseConfig.withCredentials = true
    axios({
      ...baseConfig, ...options, ...config, url: api, data, cancelToken,
    })
    .then(response => response.data)
      .then(response => {
        if(response.code == 200){
          success(response.result)
          return
        }
        if(response.code != 200){
          message.warning(response.message)

        }
      })
      .catch((e) => {
        failure({
          data: '',
          msg: e.message,
          status: 0,
        }) 
      })
    return hooks
  }
}

// 创建发起api的启动器
export const createApi = function (api, options) {
  const obj = parseQueryString(window.location.href)
  let url = api
  if (obj.key) {
    url = `${api}?key=${obj.key}`
    if (obj.sourceName) {
      url = `${api}?key=${obj.key}&sourceName=${obj.sourceName}`
    }
  }
  return oftenFetchByPost(`${url}`, options)
}

