import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.Assert.*;

public class MySemaphoreTest {

    @Test
    public void oneThread() throws ExecutionException, InterruptedException {
        MySemaphore semaphore = new MySemaphore(1);

        int threadNum = 100;

        var tasks = GetTasks(threadNum);
        int res = 0;
        for (var task : tasks) {
            res += task.get();
        }

        int expectedValue = (int)((1 + threadNum) / 2.0 * threadNum);

        Assert.assertEquals("Не равны ожидаемое и результируемое значение!", res, expectedValue);

    }

    private List<Future<Integer>> GetTasks(int maxThreadNum){
        List<Future<Integer>> res = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(maxThreadNum);

        for (int index = 0; index < maxThreadNum; index++){
            Callable<Integer> call = new Callable<>() {
                static int increment = 0;

                @Override
                public Integer call() throws Exception {
                    return ++increment;
                }
            };

            res.add(executor.submit(() -> call.call()));
        }

        return res;
    }
}