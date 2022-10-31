import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class MyLatch {
    private AtomicInteger counter;

    public MyLatch(AtomicInteger counter) {
        this.counter = counter;
    }

    public synchronized void countDown(){
        if (counter.get() <= 0) return;

        counter.getAndDecrement();
        if (counter.get() <= 0)
            notifyAll();
    }

    public synchronized void await(){
        if (counter.get() <= 0) return;
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void await(long timeout){
        try {
            wait(timeout);
//            System.out.println("Latch opened!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getCount(){
        return counter.get();
    }
}
