import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

//需要被执行的线程
class ExceptionThread2 implements Runnable {

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by " + t.getName());
        System.out.println("ExceptionThread2 = " + t.getUncaughtExceptionHandler());
        throw new RuntimeException("aaaaa");
    }
}

//拦截器
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
    }
}

//线程工厂 --创建线程
class HandlerThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        System.out.println("HandlerThreadFactory creating new Thread");
        Thread t = new Thread(r, "新线程");
        System.out.println("created " + t.getName());

        //为新线程添加拦截器
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());

        System.out.println("HandlerThreadFactory newThread = " + t.getUncaughtExceptionHandler());
        return t;
    }
}

//构造器
public class CaptureUncaughtException {
    public static void main(String args[]) {
        //线程池创建线程并使用工厂为每个线程添加拦截器
        ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());

        /**
         * 执行线程 ExceptionThread2
         *    注意：ExceptionThread2使用的线程 是 HandlerThreadFactory 中生成的
         */

        exec.execute(new ExceptionThread2());
    }
}
