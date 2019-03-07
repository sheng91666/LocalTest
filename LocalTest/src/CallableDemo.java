import java.util.ArrayList;
import java.util.concurrent.*;

class TaskWithResult implements Callable {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "result of TaskWithResult" + id;
    }

}

public class CallableDemo {
    public static void main(String args[]) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            futures.add(exec.submit(new TaskWithResult(i)));
        }

        for (Future<String> f : futures) {
            try {
                String s = f.get();
                System.out.println(s);
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            } catch (ExecutionException e) {
                System.out.println(e);
            } finally {
                exec.shutdown();
            }
        }
    }

}
