### Synchronized底层实现
* JDK1.6之前的实现是重量级的，之后的版本进行了改进
* 使用synchronized之后，会在编译之后在同步的代码块前后加上monitorenter和monitorexit字节码指令，他依赖操作系统底层互斥锁实现。他的作用主要就是实现原子性操作和解决共享变量的内存可见性问题。
* 执行monitorenter指令时会尝试获取对象锁，如果对象没有被锁定或者已经获得了锁，锁的计数器+1。此时其他竞争锁的线程则会进入等待队列中。
* 执行monitorexit指令时则会把计数器-1，当计数器值为0时，则锁释放，处于等待队列中的线程再继续竞争锁。
* synchronized是排它锁，当一个线程获得锁之后，其他线程必须等待该线程释放锁后才能获得锁，而且由于Java中的线程和操作系统原生线程是一一对应的，线程被阻塞或者唤醒时时会从用户态切换到内核态，这种转换非常消耗性能。
* 从内存语义来说，加锁的过程会清除工作内存中的共享变量，再从主内存读取，而释放锁的过程则是将工作内存中的共享变量写回主内存。

### Synchronized源码上的分析
* synchronized实际上有两个队列waitSet和entryList。
  * 当多个线程进入同步代码块时，首先进入entryList
  * 有一个线程获取到monitor锁后，就赋值给当前线程，并且计数器+1
  * 如果线程调用wait方法，将释放锁，当前线程置为null，计数器-1，同时进入waitSet等待被唤醒，调用notify或者notifyAll之后又会进入entryList竞争锁
  * 如果线程执行完毕，同样释放锁，计数器-1，当前线程置为null
![synchronized源码上的分析](https://mmbiz.qpic.cn/mmbiz_jpg/ibBMVuDfkZUljPgPC9h7FmEyOSbttvPehVayU2Ey8Fm3lFvDoaSjT2prBjWibRkk2tB1ric2LHVDCXYicyK2gb195Q/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)


### Synchronized锁升级概念
* 可偏向时：无锁->偏向锁->轻量级锁->重量级锁
* 不可偏向时：无锁->轻量级锁->重量级锁
  * 当线程访问同步块获取锁时，会在对象头和栈帧中的锁记录里存储偏向锁的线程ID，之后这个线程再次进入同步块时都不需要CAS来加锁和解锁了，偏向锁会永远偏向第一个获得锁的线程，如果后续没有其他线程获得过这个锁，持有锁的线程就永远不需要进行同步，反之，当有其他线程竞争偏向锁时，持有偏向锁的线程就会释放偏向锁。可以用过设置-XX:+UseBiasedLocking开启偏向锁。
  * 轻量级锁：JVM的对象的对象头中包含有一些锁的标志位，代码进入同步块的时候，JVM将会使用CAS方式来尝试获取锁，如果更新成功则会把对象头中的状态位标记为轻量级锁，如果更新失败，当前线程就尝试自旋来获得锁。
  * 重量级锁：当轻量级锁（轻量级锁不存在竞争关系，当有竞争，会变为自旋锁，如果自旋次数内还是没拿到锁，则进行锁升级）在竞争过程中，自旋达到次数还没有获取到锁，则升级为重量级锁
  * 自旋锁：由于大部分时候，锁被占用的时间很短，共享变量的锁定时间也很短，所有没有必要挂起线程，用户态和内核态的来回上下文切换严重影响性能。自旋的概念就是让线程执行一个忙循环，可以理解为就是啥也不干，防止从用户态转入内核态，自旋锁可以通过设置-XX:+UseSpining来开启，自旋的默认次数是10次，可以使用-XX:PreBlockSpin设置。
  * 自适应锁：自适应锁就是自适应的自旋锁，自旋的时间不是固定时间，而是由前一次在同一个锁上的自旋时间和锁的持有者状态来决定。
  * 锁只能升级，不能降级
![锁升级过程](https://mmbiz.qpic.cn/mmbiz_jpg/ibBMVuDfkZUljPgPC9h7FmEyOSbttvPehP1heYUUerKq0Xd3k7DGl9xqicy6NsgJow4xHIYSK0Oc90aN7TO2TsibA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

### 注意
* 锁的是对象，不是代码
* 锁定方法，非锁定方法，同时执行
* Synchronized(Object)，锁的对象不能是String、Integer、Long等基本类型