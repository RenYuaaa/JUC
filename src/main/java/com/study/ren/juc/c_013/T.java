package com.study.ren.juc.c_013;

import java.util.concurrent.TimeUnit;

/**
 * @author: renjiahui
 * @date: 2021-09-08 0:24
 * @description: volatile作用：
 *  保证线程可见性
 *      MESI，缓存一致性协议
 *  禁止指令重排序
 *      DCL单例
 *      Double Check Lock
 *      单例模式的第六个例子
 */
public class T {
    // 对比一下有无volatile当情况下，这个那个程序运行结果当区别
    // 加了volatile程序可以结束，不加则程序不会结束
    /*volatile*/ boolean running = true;

    void m() {
        System.out.println("m start");
        while (running) {
            /*try {
                TimeUnit.MICROSECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        T t = new T();

        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
    }
}
