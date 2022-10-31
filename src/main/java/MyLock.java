public class MyLock {
    private static final long defaultTimeout = 30000;

    private int lockCounter = 0;
    private long timeout = defaultTimeout;

    public MyLock() {
    }

    public MyLock(long timeout) {
        this.timeout = timeout;
    }

    public synchronized void lock() {
        try {
            while (isLocked()) {
                wait(timeout);
            }
//            System.out.println("Lock: закрыл");
            lockCounter++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void unlock() {
        lockCounter--;
        notifyAll();
//        System.out.println("Lock: открыл");
    }

    private boolean isLocked(){
        return lockCounter > 0;
    }
}
