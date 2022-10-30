import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

public class MyCallable implements Callable<Double> {

    private double eps;
    private int threadNum;
    private int curThreadNum;
    private Lock lock;
    private CountDownLatch latch;
    private long oldTime;

    public MyCallable(double eps, int threadNum, int curThreadNum, Lock lock, CountDownLatch latch, long oldTime) {
        this.threadNum = threadNum;
        this.curThreadNum = curThreadNum;
        this.eps = eps;
        this.lock = lock;
        this.latch = latch;
        this.oldTime = oldTime;
    }

    @Override
    public Double call() {
        try {
            lock.lock();
            if (true) throw new RuntimeException("Зависло");
            var res = MathUtilClass.calcSinEndlessRow(eps, curThreadNum, threadNum);
            System.out.println("Thread " + (threadNum - latch.getCount())
                    + " was calculated in " + (System.currentTimeMillis() - oldTime) + "ms");

            return res;

        } finally {
            latch.countDown();
            lock.unlock();
        }
    }
}
