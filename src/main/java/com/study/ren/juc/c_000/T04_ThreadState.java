package com.study.ren.juc.c_000;

/**
 * @author: renjiahui
 * @date: 2021-09-07 0:31
 * @description: 正常结束一个线程即为结束一个线程。stop()方法太粗暴，容易导致状态的不一样。不建议使用
 */
public class T04_ThreadState {

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(this.getState());

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }

    /**
     * 线程的状态：创建、就绪、运行、阻塞、死亡
     * @param args
     */
    public static void main(String[] args) {
        Thread myThread = new MyThread();
        System.out.println(myThread.getState());

        myThread.start();

        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(myThread.getState());
    }
}
