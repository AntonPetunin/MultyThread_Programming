public class MySemaphore {
    private final int permits;
    private int counter;

    public MySemaphore(int permits) {
        if (permits > 0){
            this.permits = permits;
            this.counter = permits;
        }
        else {
            throw new RuntimeException("Incorrect permit value");
        }
    }

    public synchronized void acquire() {
        try {
            while (counter <= 0)
                wait();
//            System.out.println("Semaphore: поток занял место");
            counter--;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void release() {
        if (counter < permits){
            counter++;
            notifyAll();
//            System.out.println("Semaphore: поток освободил место");
        }
    }
}
