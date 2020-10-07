## 写时复制
CopyOnWrite容器即写时复制的容器。往一个容器添加元素时，不直接往当前容器`object[]`添加，而是
先将当前容器`object[]`进行copy，复制出一个新的容器`object[] newElements`,然后往新的容器里添加元素，
添加完元素后，再将原容器的引用指向新的容器`setArray(newElement)`;
这样做的好处是可以对CopyOnWrite容器进行并发地读，而不需要加锁，因为当前容器不会添加任何元素。
所以CopyOnWrite容器是一种读写分离地思想，读和写不同的容器。

```java
public boolean add(E var1) {
        ReentrantLock var2 = this.lock;
        var2.lock();

        boolean var6;
        try {
            Object[] var3 = this.getArray();
            int var4 = var3.length;
            Object[] var5 = Arrays.copyOf(var3, var4 + 1);
            var5[var4] = var1;
            this.setArray(var5);
            var6 = true;
        } finally {
            var2.unlock();
        }

        return var6;
    }
```



## 集合类不安全
1. 故障现象
 * java.util.ConcurrentModificationException
2. 导致原因
 * public boolean add(E var1)
3. 解决方案
 * 3.1 Vector 
   ```
   public synchronized boolean add(E var1) //粗粒度加锁(对整个方法加锁)
   ```
       
 * 3.2 Collections.synchronizedList(new ArrayList<>())
   ```
   public void add(int var1, E var2) {
         synchronized(this.mutex) { //中等粒度加锁(对方法体中的逻辑运算加锁)
              this.list.add(var1, var2);
         }
     }
   ```
 * 3.3 CopyOnWriteArrayList-->
      ```
        public boolean add(E var1) {
        ReentrantLock var2 = this.lock; **细粒度加锁**(使用可重用锁对逻辑运算加锁)
        var2.lock();
      ```
4. 优化建议
