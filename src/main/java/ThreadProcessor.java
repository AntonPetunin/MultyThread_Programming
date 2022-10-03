import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ThreadProcessor {

    private static List<Thread> threads = new ArrayList<>();

    public static void threadStop(double index) {
        long id = Math.round(index);
        Optional<Thread> foundThread = threads.stream().filter(el -> el.getId() == id).findFirst();

        if (foundThread.isPresent()) {
            if (!foundThread.get().isInterrupted()){
                foundThread.get().stop();
                System.out.println("Был остановлен поток " + foundThread.get().getId() + "!");
            }
            else {
                System.out.println("Поток " + foundThread.get().getId() + " уже прерван!");
            }
        }
        else {
            System.out.println("Потока " + id + " не существует!");
        }
    }

    public static void startNewThread(double parAccuracy) {
        var accuracy = Math.pow(10, parAccuracy);
        Thread thread = new Thread(() -> getMathTask(accuracy));
        thread.start();
        threads.add(thread);
        System.out.println("Был создан поток " + thread.getId() + "!");
    }

    public static void awaitThread(double index) throws InterruptedException {
        long id = Math.round(index);
        Optional<Thread> foundThread = threads.stream().filter(el -> el.getId() == id).findFirst();
        if (foundThread.isPresent()) {
            foundThread.get().join();
            System.out.println("Поток " + id + " выполняется, ждите!");
        }
        else {
            System.out.println("Потока " + id + " не существует!");
        }
    }

    public static void StopAllThreads() {
        for (var thread : threads) {
            if (!thread.isInterrupted()) {
                thread.stop();
            }
        }
    }

    private static void getMathTask(double accuracy) {
        double eps = 1 / accuracy;
        double rowSum = 0;
        try {
            rowSum = MathUtilClass.calcSinEndlessRow(eps);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Сумма ряда равна " + rowSum);
    }

}
