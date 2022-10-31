import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadProcessor {

    public static void executeMathOperation(double accuracy) throws ExecutionException, InterruptedException {
        double eps = 0.1 / accuracy;
        double rowSum = 0;
        int maxThreadNum = 100;

        long oldTime = System.currentTimeMillis();

        MyLatch latch = new MyLatch(maxThreadNum);
        MySemaphore semaphore = new MySemaphore(15);
        ExecutorService executor = Executors.newFixedThreadPool(maxThreadNum);
        List<Future<Double>> futureList = new ArrayList<>();
        MyLock lock = new MyLock();

        for (int index = 0; index < maxThreadNum; index++){
            Callable<Double> call = new MyCallable(eps, maxThreadNum, index, lock, latch, oldTime);

            semaphore.acquire();
            futureList.add(executor.submit(() -> {
                try {
                    return call.call();
                } finally {
                    semaphore.release();
                }
            }));
        }

        executor.shutdown();

        for (var future : futureList){
            rowSum += future.get();
        }
        latch.await();

        System.out.println("Row sum is " + rowSum);
    }

}
