import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

//Using a named inner class
class InnerThread1 {
    private int countDown = 5;
    private Inner inner;

    private class Inner extends Thread {
        Inner(String name) {
            super(name);
            start();
        }
    }

    public void run() {
        try {
            while (true) {
                System.out.println(this);
                if (--countDown == 0) {
                    return;
                }
                sleep(10);
            }
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
    }

    public String toString() {
        return inner.getName() + ":" + countDown;
    }

    public InnerThread1(String name) {
        inner = new Inner(name);
    }
}

//Using an anonymous inner class;  匿名内部类
class InnerThread2 {
    private int countDown = 5;
    private Thread t;

    public InnerThread2(String name) {
        t = new Thread(name) {
            public void run() {
                try {
                    while (true) {
                        System.out.println(this);
                        if (--countDown == 0) {
                            return;
                        }
                        sleep(10);
                    }
                } catch (Exception e) {
                    System.out.println("sleep() interrupted");
                }
            }

            public String toString() {
                return getName() + ":" + countDown;
            }
        };
        t.start();
    }
}

//Using a named Runnable implementation
class InnerRunnable1 {
    private int countDown = 5;
    private Inner inner;

    private class Inner implements Runnable {

        Thread t;

        Inner(String name) {
            t = new Thread(this, name);
            t.start();
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(this);
                    if (--countDown == 0) {
                        return;
                    }
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            } catch (InterruptedException e) {
                System.out.println("sleep() interrupted");
            }
        }

        public String toString() {
            return t.getName() + ":" + countDown;
        }
    }

    public InnerRunnable1(String name) {
        inner = new Inner(name);
    }
}

//Using an anonymous Runnable implementation
class InnerRunnable2 {
    private int countDown = 5;
    private Thread t;

    public InnerRunnable2(String name) {
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println(this);
                        if (--countDown == 0) {
                            return;
                        }
                        TimeUnit.MILLISECONDS.sleep(10);
                    }
                } catch (InterruptedException e) {
                    System.out.println("error");
                }
            }

            public String toString() {
                return Thread.currentThread().getName() + ":" + countDown;
            }
        }, name);
        t.start();
    }
}

//A separate method to run some code as a task
class ThreadMethod {
    private int countDown = 5;
    private Thread t;
    private String name;

    public ThreadMethod(String name) {
        this.name = name;
    }

    public void runTask() {
        if (t == null) {
            t = new Thread(name) {
                public void run() {
                    try {
                        while (true) {
                            System.out.println(this);
                            if (--countDown == 0) {
                                return;
                            }
                            sleep(10);
                        }
                    } catch (InterruptedException e) {
                        System.out.println("error");
                    }
                }

                public String toString() {
                    return getName() + ":" + countDown;
                }
            };
            t.start();
        }
    }
}

public class ThreadVariations {
    public static void main(String args[]) {
        new InnerThread1("innerThread1");
        new InnerThread2("innerThread2");
        new InnerRunnable1("innerRunnable1");
        new InnerRunnable2("innerRunnable2");
        new ThreadMethod("threadMethod").runTask();
    }
}
