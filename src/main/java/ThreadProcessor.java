import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

public class ThreadProcessor {

    public static void executeMathOperation(double accuracy) throws ExecutionException, InterruptedException {
        double eps = 1 / accuracy;
        double rowSum = 0;
        int maxThreadNum = 100;
        CountDownLatch latch = new CountDownLatch(maxThreadNum);

        ExecutorService executor = Executors.newFixedThreadPool(maxThreadNum);

        List<Future<Double>> futureList = new ArrayList<>();

        for (int index = 0; index < maxThreadNum; index++){
            int finalIndex = index;

            Callable<Double> call = () -> {
                var res = MathUtilClass.calcSinEndlessRow(eps, finalIndex, maxThreadNum);
                latch.countDown();
                System.out.println("Calculation progress: " + (maxThreadNum - latch.getCount()) + "%");

                return res;
            };

            futureList.add(executor.submit(call));
        }
        executor.shutdown();

        for (var future : futureList){
            rowSum += future.get();
        }

        System.out.println("Row sum is " + rowSum);
    }

}
