import java.util.concurrent.TimeUnit;

public class MyLatch {
    private int counter;

    public MyLatch(int counter) {
        this.counter = counter;
    }

    public synchronized void countDown(){
        counter--;
        if (counter <= 0)
            notifyAll();
    }

    public synchronized void await(){
        try {
            if (counter > 0)
                wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void await(long timeout){
        try {
            wait(timeout);
            System.out.println("Latch opened!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getCount(){
        return counter;
    }
}
