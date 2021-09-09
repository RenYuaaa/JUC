### ReentrantLock VS Synchronized

### 区别
* Reentrantlock底层是用CAS实现，而Synchronized底层是Monitorentry和Monitorexit实现
* Reentrantlock可以使用tryLock、lockinterupptibly进行打断锁
* Reentrantlock和Synchronized都是非公平锁，但是Reentrantlock可以通过构造参数进行修改为公平锁
