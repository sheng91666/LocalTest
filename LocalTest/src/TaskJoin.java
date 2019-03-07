class JoinTask implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + " .. " + i);
        }
    }
}

public class TaskJoin {
    public static void main(String[] args) throws InterruptedException {
        // 创建任务对象
        JoinTask jt = new JoinTask();
        // 创建线程对象
        Thread t1 = new Thread(jt, "线程1");
        Thread t2 = new Thread(jt, "线程2");
        Thread t3 = new Thread(jt, "线程3");
        // 启动线程
        t1.start();
//        void join(); //等待该线程终止。
        t1.join();
        t2.start();
        t3.start();
    }
}


