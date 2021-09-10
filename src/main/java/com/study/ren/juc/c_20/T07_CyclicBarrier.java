package com.study.ren.juc.c_20;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier可以用在限流方面
 */
public class T07_CyclicBarrier {
    public static void main(String[] args) {

        // 拦截线程，当线程数量达到设置当上限时放行
        CyclicBarrier barrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("满人。。。发车");
            }
        });

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    //
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
