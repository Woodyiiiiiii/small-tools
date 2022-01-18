# kafka-note

查看Kafka信息，并查看Kafka依赖的openJDK和Zookeeper在本机中是否存在
```
brew info kafka
```

安装Kafka
```
brew install kafka
```

启动zookeeper
```
sh zkServer start
```

启动Kafka
```
// 用&后台启动
sh kafka-server-start /opt/homebrew/etc/kafka/server.properties &
```

关闭Kafka
```
sh kafka-server-stop &
```