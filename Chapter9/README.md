#### Mongo

##### [Windows MongoDB安装配置](https://my.oschina.net/liuzidong/blog/2986758)

##### 启动服务命令，保存为 .cmd文件
```
@echo off
start cmd /k "cd/d C:\Program Files\MongoDB\Server\3.4\bin&&net start MongoDB"
```

##### 或者
```
@echo off
net start MongoDB
```
