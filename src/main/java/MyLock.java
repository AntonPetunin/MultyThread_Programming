import java.util.concurrent.atomic.AtomicBoolean;

public class MyLock {

    private AtomicBoolean locked = new AtomicBoolean();

    public MyLock() {
        locked.set(false);
    }

    public synchronized void lock() {
        try {
            while (isLocked()) {
                wait();
            }
//            System.out.println("Lock: закрыл");
            locked.set(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void unlock() {
        locked.set(false);
        notifyAll();
//        System.out.println("Lock: открыл");
    }

    private boolean isLocked(){
        return locked.get();
    }
}
