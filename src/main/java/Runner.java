import java.util.concurrent.ExecutionException;

public class Runner {

    public static void main(String[] args) {

        try {
            ThreadProcessor.executeMathOperation(10E+9);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        System.out.println(fxs.size());
    }
}
