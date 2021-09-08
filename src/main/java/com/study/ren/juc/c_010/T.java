package com.study.ren.juc.c_010;

import java.util.concurrent.TimeUnit;

/**
 * @author: renjiahui
 * @date: 2021-09-08 0:09
 * @description:
 */
public class T {

    synchronized void m() {
        System.out.println("m1 start...");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1 end...");
        System.out.println("T对象的this为：" + this);
    }

    public static void main(String[] args) {
        new TT().m();
    }
}

class TT extends T {

    @Override
    synchronized void m() {
        System.out.println("child m start...");
        super.m();
        System.out.println("child m end...");
        System.out.println("TT类对象中的super对象为：" + super.toString());
        System.out.println("TT类对象中的this为：" + this);
    }
}
