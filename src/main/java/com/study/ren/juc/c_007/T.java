package com.study.ren.juc.c_007;

/**
 * @author: renjiahui
 * @date: 2021-09-07 23:50
 * @description: 同步方法和非同步方法是否可以同时调用？
 */
public class T {

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1 start...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end...");
    }

    public void m2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2 ");
    }

    public static void main(String[] args) {
        T t = new T();

        /*new Thread(() -> t.m1(), "t1").start();
        new Thread(() -> t.m2(), "t2").start();*/

        new Thread(t::m1, "t1").start();
        new Thread(t::m2, "t2").start();

        /**
         * jdk1.8之前的写法
         */
  /*      new Thread(new Runnable() {
            @Override
            public void run() {
                t.m1();
            }
        });*/

    }
}
