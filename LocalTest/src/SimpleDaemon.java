import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class SimpleDaemon implements Runnable {

    public static void main(String args[]) throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemon());
            daemon.setDaemon(true); //Must call before run()
            daemon.start();
        }
        System.out.println("all daemons start");

        System.out.println(BigDecimal.ZERO);
        TimeUnit.MILLISECONDS.sleep(200);
    }

    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + "I'm daemon");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
