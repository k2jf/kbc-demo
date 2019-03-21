# kbc-kmx

##依赖
- kbc-cors （目前包含在脚手架内）

## 功能
- KMX请求代理：/kmx/*
- KMX用户验证：/kmx_login

## 安装
在application.properties里添加如下配置项：
```
kbc.kmx.host=10.12.20.36
kbc.kmx.port.data.service.v2=8081
kbc.kmx.port.data.service.v3=8082
kbc.kmx.port.batch.rest=8124
kbc.kmx.port.object.rest=28090
kbc.kmx.port.file.rest=28095
kbc.kmx.port.pas.services=28085
kbc.kmx.port.ecf.rest=28102
kbc.kmx.port.meter.v1=28100
kbc.kmx.port.auth.service=28091
kbc.kmx.k2key=
```

## 维护者
zhanghao

