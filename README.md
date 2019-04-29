# kbc-demo

## 功能
集成kbc的各个组件，用于演示目的。

## 维护者
zhanghao

## 使用说明

### 环境要求：
- java 8或以上版本
- maven 3或以上版本
- git 1.8或以上版本

### 下载代码
```bash
git clone git@github.com:k2jf/kbc-demo.git
```

### 启动服务
```bash
cd kbc-demo
mvn spring-boot:run
```

### kbc-auth-login  如需登录
```
http://localhost:9080/login?userName=admin&password=admin
```
### 查看接口文档
```
http://localhost:9080/swagger-ui.html#/

```