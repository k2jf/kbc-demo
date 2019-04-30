# kbc-login

## 功能
用户验证

## 维护者
wangliheng@k2data.com.cn

## 依赖组件
kbc-auth

## application.properties 配置
```
kbc.login.interceptor.allow.origin=/login
kbc.login.interceptor.intercept.origin=/**

kbc.login.interceptor.allow.origin 如果是多个路径用','隔开，如：
kbc.login.interceptor.allow.origin=/login,/logs
```

## git拉取示例
```
1. git remote add -f kbc-login git@github.com:k2jf/kbc-login.git

2. git subtree add -P src/main/java/com/k2data/kbc/login kbc-login master --squash
```

##说明
```
不拦截路径："/login"

```
## 初始登录接口
请求参数

| 属性        | 说明     | 类型   | 默认值 |
| ----------- | -------- | ------ | ------ |
| userName | 用户名 | String | Null | 
| password | 密码 | String | Null |

http://localhost:9080/login?userName=admin&password=96e79218965eb72c92a549dd5a330112
