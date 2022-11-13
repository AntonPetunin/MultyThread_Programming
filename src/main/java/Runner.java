import java.util.concurrent.ExecutionException;

public class Runner {

    public static void main(String[] args) {

        try {
            ThreadProcessor.executeOperation();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        System.out.println(fxs.size());
    }
}
