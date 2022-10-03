import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Runner {

    public static void main(String[] args) throws ExecutionException {
//        Callable<Integer> call = () -> {
//            throw new IOException("Hello");
//        };
//        FutureTask<?> future = new FutureTask<>(call);
//
//        new Thread(future).start();

        try {
            CommandProcessor proc = new CommandProcessor();
            proc.RunProcessor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        System.out.println(fxs.size());
    }
}
