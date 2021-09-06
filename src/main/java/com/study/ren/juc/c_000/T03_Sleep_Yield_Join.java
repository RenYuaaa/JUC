package com.study.ren.juc.c_000;

/**
 * @author: renjiahui
 * @date: 2021-09-07 0:18
 * @description:
 */
public class T03_Sleep_Yield_Join {

    public static void main(String[] args) {
        testSleep();

        testYield();

        testJoin();
    }

    static void testSleep() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 两个线程在运行时，其中一个线程调用yield方法，则该线程进入等待队列中。返回到就绪状态（线程的五种状态）
     */
    static void testYield() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                if (i % 10 == 0) {
                    Thread.yield();
                }
            }
        }).start();
    }

    /**
     * join--经常用来等待另一个线程的结束
     * 面试题：怎么保证线程按顺序执行？
     *  在主线程中依次调用t1.join(),t2.join(),t3.join()。或者t1线程中调用t2.join()，t2线程中调用t3.join()
     */
    static void testJoin() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
