
export const set = 'set$'
export const brandName = 'React' // slogan

// 开发环境默认配置
let _serverIp = 'http://localhost'
let _port = '9968'
let _baseURL = `${_serverIp}:${_port}`
let _mockURL = 'http://localhost'

export const serverIp = _serverIp
export const path = '/weather'
export const timeout = '60000' // 接口超时限制(ms)
export const baseURL = _baseURL
export const mockURL = _mockURL
