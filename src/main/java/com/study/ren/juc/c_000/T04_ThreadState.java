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
     * 线程的状态：创建、就绪、运行、阻塞、死亡、等待、有时间的等待
     * 详细说明：
     *  1：NEW状态：当new Thread()时，即为创建状态
     *  2：Runnable状态，此状态中又分为READY状态（就绪状态）和RUNNING（运行状态）
     *  3：WAITING状态，此状态为等待状态，当线程调用wait方法后线程将会处于此状态
     *  4：TIMED_WAITING状态，有等待时间当等待状态
     *  5：BLOCKED状态，阻塞状态，当线程加锁之后会进入该状态
     *  6：TERMINATED状态，死亡状态，当线程执行完毕就会进入该状态
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
