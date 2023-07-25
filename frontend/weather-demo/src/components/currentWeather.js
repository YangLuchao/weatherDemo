import { currentWeatherByCityName } from '../apis/common'
import React from 'react'
import { Input,Badge, Descriptions } from 'antd';
import { useState } from 'react';
import { message } from 'antd';
const { Search } = Input;

export default function MyComponent() {
  const [form, setForm] = useState({
    currentState: '-',
    currentStateIcon: '-',
    calculationTime: '-',
    minTemperature: '-',
    maxTemperature: '-',
    avgTemperature: '-',
    feelsLike: '-',
    temperatureUnit: '-',
    humidity: '-',
    clouds: '-',
    atmosphericPressure: '-'
  });
  function replace(res) {
    setForm({
      ...form,
      currentState : res.currentState,
      currentStateIcon : res.currentStateIcon,
      calculationTime : res.calculationTime,
      minTemperature : res.minTemperature,
      maxTemperature : res.maxTemperature,
      avgTemperature : res.avgTemperature,
      feelsLike : res.feelsLike,
      temperatureUnit : res.temperatureUnit,
      humidity : res.humidity,
      clouds: res.clouds,
      atmosphericPressure : res.atmosphericPressure
    });
  }
  return <div><Search
  placeholder="input search text"
  allowClear
  enterButton="Search"
  size="large"
  onSearch= {(value) => {
    if(!value){
      message.error("请输入城市");
      return;
    }
    currentWeatherByCityName(
      {"cityName": value},
      (res) => {
        replace(res);
      },
      (res) => {
        console.log(res)
      },
    );
  }}
/>
<Descriptions bordered>
    <Descriptions.Item label="天气">{form.currentState}
    {form.currentStateIcon == '-' ? '' : <img 
        src={form.currentStateIcon}
      />
    }</Descriptions.Item>
    <Descriptions.Item label="体感温度">{form.feelsLike} {
      form.temperatureUnit == '-' ? '' : form.temperatureUnit
    }</Descriptions.Item>
    <Descriptions.Item label="最高温">{form.maxTemperature} {
      form.temperatureUnit == '-' ? '' : form.temperatureUnit
    }</Descriptions.Item>
    <Descriptions.Item label="最低温">{form.minTemperature} {
      form.temperatureUnit == '-' ? '' : form.temperatureUnit
    }</Descriptions.Item>
    <Descriptions.Item label="平均温度">{form.avgTemperature} {
      form.temperatureUnit == '-' ? '' : form.temperatureUnit
    }</Descriptions.Item>
    <Descriptions.Item label="湿度" span={2}>
      {form.humidity}
    </Descriptions.Item>
    <Descriptions.Item label="气压">{form.atmosphericPressure}</Descriptions.Item>
    <Descriptions.Item label="云量">{form.clouds}</Descriptions.Item>
  </Descriptions>
</div>; 
}
