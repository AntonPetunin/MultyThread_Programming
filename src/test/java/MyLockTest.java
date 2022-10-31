import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class MyLockTest {

    @Test
    public void TestTwoThreads() throws InterruptedException {
        CountDownLatch firstThreadLatch = new CountDownLatch(1);
        CountDownLatch secondThreadLatch = new CountDownLatch(1);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        MyLock locker = new MyLock();

        Thread firstThread = runFirstThread(locker, firstThreadLatch, atomicInteger);
        Thread secondThread = runSecondThread(locker, secondThreadLatch, atomicInteger);
        firstThread.start();
        secondThread.start();

        firstThreadLatch.await();
        Assert.assertEquals(1, atomicInteger.get());
        secondThreadLatch.countDown();

        secondThread.join();
        Assert.assertEquals(2, atomicInteger.get());
    }

    private Thread runFirstThread(MyLock locker, CountDownLatch latch, AtomicInteger value){
        return new Thread(() -> {
            locker.lock();
            value.set(1);
            latch.countDown();
            locker.unlock();
        });
    }

    private Thread runSecondThread(MyLock locker, CountDownLatch latch, AtomicInteger value){
        return new Thread(() -> {
            locker.lock();
            value.set(2);
            latch.countDown();
            locker.unlock();
        });
    }
}