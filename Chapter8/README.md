#### Jedis
##### 启动服务命令，保存为 .cmd文件
```
@echo off
start cmd /k "F:&&cd j2ee\Redis-x64-3.2.100&&redis-server.exe redis.windows.conf"
start cmd /k "F:&&cd j2ee\Redis-x64-3.2.100&&redis-cli.exe"
```
