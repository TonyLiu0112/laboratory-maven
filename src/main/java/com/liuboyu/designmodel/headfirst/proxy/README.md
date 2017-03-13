# 代理模式
通过一个代理对象，访问远程目标代理，这里使用的是RMI（remote method invoke）来实现

## 例子
糖果公司的CEO需要在自己办工作电脑上，看到全国各地的糖果机的状态和库存。
这样，可以通过糖果机的一个代理对象将状态和库存通过网络汇报到CEO的电脑中。

在这里，这个远程代理屏蔽了所有的网络I/O、socked细节，但还是需要考虑服务注册时服务发现的问题
这里的远程调用使用java的RMI来实现

## RMI使用的前提步骤
1. 要是用rmic命令，到编译的class文件夹下，生成相关配置文件
2. 启动注册中心服务