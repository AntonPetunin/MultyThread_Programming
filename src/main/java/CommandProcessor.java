import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CommandProcessor {

    public void RunProcessor() throws InterruptedException {
        ProcessCmd command = null;

        System.out.println("start <n> - запускает задачу с параметром времени n.");
        System.out.println("stop <n> - останавливает задачу с номером n.");
        System.out.println("await <n> - консоль блокируется до момента завершения задачи с номером n.");
        System.out.println("exit - завершает выполнения всей программы.");

        while (command != ProcessCmd.EXIT) {
            List<String> commands = ReadInput();
            command = getTaskType(commands.get(0));
            execOperations(command, Double.parseDouble(commands.get(1)));
        }


//        long oldTime = System.currentTimeMillis();
//        long runTime = System.currentTimeMillis() - oldTime;
//        System.out.println("Run time: " + runTime + " ms");
    }

    private List<String> ReadInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите команду:");

        String question = sc.nextLine();
        List<String> command = new ArrayList<>(List.of(question.split("\s")));

        if (command.size() > 0 && command.get(0).equalsIgnoreCase("exit"))
            command.add("0");

        if (command.size() < 2)
            while (true) {
                command.clear();
                System.out.println("Некорректный ввод! Попробуйте ввести <Команда> <n>.");
                question = sc.next();
                command = new ArrayList<>(List.of(question.split("\s")));
            }

        return command;
    }



    private void execOperations(ProcessCmd command, double n) throws InterruptedException {

        switch (command) {
            case START: {
                ThreadProcessor.startNewThread(n);
                break;
            }

            case STOP: {
                ThreadProcessor.threadStop(n);
                break;
            }

            case AWAIT: {
                ThreadProcessor.awaitThread(n);
                break;
            }

            case EXIT: {
                ThreadProcessor.StopAllThreads();
                break;
            }

        }
    }


    private ProcessCmd getTaskType(String view) {
        switch (view) {
            case "start" -> {
                return ProcessCmd.START;
            }
            case "stop" -> {
                return ProcessCmd.STOP;
            }
            case "await" -> {
                return ProcessCmd.AWAIT;
            }
            case "exit" -> {
                return ProcessCmd.EXIT;
            }
        }

        return ProcessCmd.undefined;
    }
}