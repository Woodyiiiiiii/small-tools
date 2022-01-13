# note

## download zookeeper

use homebrew
```
// search info
brew info zookeeper

// download
brew install zookeeper

```

start zookeeper
```
// start zookeeper server
zkServer start
// zkServer启动不了，用上面命令配置conf启动
// 貌似howebrew下载的文件都在/opt/homebrew/下
// brew install tree 下载了tree工具来查看目录层级

// connect
zkCli 
// command
ls /
quit

// search status
zkServer status

// stop zookeeper
zkServer stop

```