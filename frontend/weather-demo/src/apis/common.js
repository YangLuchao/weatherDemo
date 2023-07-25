
import { createApi } from '../configs/ajax'
import { mockURL, baseURL } from '../configs/config'

const prefix = 'weather'
const option = { baseURL: mockURL }

export const currentWeatherByCityName = createApi(`${baseURL}/${prefix}/current/weatherByCityName`, option)
