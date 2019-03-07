class UnresponSiveUI {
    private volatile double d = 1;

    public UnresponSiveUI() throws Exception {
        while (d > 0) {
            d = d + (Math.PI + Math.E) / d;
            System.in.read();
        }
    }
}

public class ResponseSaveUI extends Thread {
    private static volatile double d = 1;

    public ResponseSaveUI() {
        setDaemon(true);
        start();
    }

    public void run() {
        while (true) {
            d = d + (Math.PI + Math.E) / d;
        }
    }

    public static void main(String args[]) throws Exception {
        new ResponseSaveUI();
        System.in.read();
        System.out.println(d);
    }
}
