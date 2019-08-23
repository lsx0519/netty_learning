package com.netty.example.threadpool;

import java.util.concurrent.*;

public class ThreadPoolTest {
    // 核心线程数量
    private static int corePoolSize = 3;
    // 最大线程数量
    private static int maxPoolSize = 5;
    // 线程存活时间：当线程数量超过corePoolSize时，10秒钟空闲即关闭线程
    private static int keepAliveTime = 10000;
    // 缓冲队列
    private static BlockingQueue<Runnable> workQueue = null;
    // 线程池
    private static ThreadPoolExecutor threadPoolExecutor = null;

    static {
        workQueue = new LinkedBlockingQueue<Runnable>(5);
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS,
                workQueue);
    }

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    public static void toBin(int a) {
        for(int i=0;i<32;i++){
            int t=(a & 0x80000000>>>i)>>>(31-i);
            System.out.print(t);
        }
        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException {
       /* toBin(RUNNING);
        toBin(SHUTDOWN);
        toBin(STOP);
        toBin(TIDYING);
        toBin(TERMINATED);*/


        try {
            for (int i = 0; i < 20; i++) {
                System.out.println("=========第" + i + "次");
                threadPoolExecutor.execute(new MyTask());
//                RunnableFuture future = threadPoolExecutor.submit(new MyTask());
                System.out.println("线程池中正在执行的线程数量：" + threadPoolExecutor.getPoolSize());
                System.out.println("线程池缓存的任务队列数量：" + threadPoolExecutor.getQueue().size());
            }
        } finally {
            threadPoolExecutor.shutdown();
        }
    }



}

class MyTask implements Runnable {

    @Override
    public void run() {

    }
}
