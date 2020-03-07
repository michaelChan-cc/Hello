## 多线程 


### 基本概念
```
程序program: 静态表现 /在资源管理器中， 如 Firefox
进程process： 程序启动后的动态表现 /在Processes进程管理器中  如  多个firefox.exe*32
线程thread: 一个进程运行中会开启或关闭多个thread  如 thread-main thread-gc 负责浏览器的页面、声音等
```
###  诉求
CPU单核， 其实是单线程，只是CPU处理速度快， 看上去是多任务运行
CPU多核， 可以每个CPU可以独立处理thread, 多线程执行的顺序是由CPU控制，可以设置优先级

### 实现
继承Thread
实现Runnable, 推荐理由： 灵活，JAVA单继承，实现对用一个资源的线程操作
实现Callable, 支持 异常处理 和 返回值(抢票业务需要返回值)。



