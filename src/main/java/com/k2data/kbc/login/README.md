# kbc-auth-login

## 功能
用户验证

## 维护者
wangliheng@k2data.com.cn





## git拉取示例
```
1. git remote add -f kbc-auth-login git@github.com:k2jf/kbc-auth-login.git

2. git subtree add -P src/main/java/com/k2data/kbc/login kbc-auth-login master --squash
```

##说明
```
1. 不拦截路径：“/login”、"/register"
2. 登录接口

```
请求参数

| 属性        | 说明     | 类型   | 默认值 |
| ----------- | -------- | ------ | ------ |
| userName | 用户名 | String | Null | 
| password | 密码 | String | Null |

http://localhost:9080/login?userName=admin&password=admin
