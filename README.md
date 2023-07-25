# weatherDemo
天气小工具

# 启动

### 前端

```bash
# 下载nodejs
略
# install
cd frontend/weather-demo
npm install
npm run build
```

### nginx

```cpp
# 替换下面这个地址至你自己的打包地址
Y://Documents//GitHub//weatherDemo//frontend//weather-demo//build;
# 启动ng
start nginx.exe
```

### 后端

```cpp
# 后端启动类
WeatherDemoApplication
```

全部完成启动后，浏览器访问http://localhost:9969 如图：

![image](https://cdn.staticaly.com/gh/YangLuchao/img_host@master/20230725/image.3ih39hw7v5q0.webp)

**注意：开发测试所用浏览器为EDGE，其他浏览器未适配**

# 后端

**SOA架构，模块关系如下：**

![image](https://cdn.staticaly.com/gh/YangLuchao/img_host@master/20230725/image.5rbe31e0uvo0.webp)

**所用技术栈：**

JDK17 + Springboot + springboot-web + lombok + openweathermap-api + pinyin4j

# 前端

**前端所用技术栈：**

React + Rantd + axios 



