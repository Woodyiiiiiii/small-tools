# note

## download redis

```aidl
brew install redis
```

找到redis.conf(在/opt/homebrew/etc目录中)，在最后加入如下配置：
```
daemonize yes // 设置后台启动
```

通过配置文件redis.conf启动redis服务：
```
redis-server /opt/homebrew/etc/redis.conf
```

查看redis启动:
```
ps -ef | grep redis
```

启动redis客户端：
```aidl
redis cli
quit 关闭客户端
```

关闭redis服务端：
```
redis-cli shutdown
```

