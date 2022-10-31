import java.io.IOException;
import java.util.*;

import static java.lang.Math.abs;
import static java.lang.Math.sin;

public class MathUtilClass {

    public static double calcSinEndlessRow(double eps, int threadNum, int genNum) {
        double sum = 0.0;
        double term = 10E+7;
        long minIterNumber = threadNum == 0 ? 1 : (long)(1 / eps) / genNum * threadNum;

        if (threadNum + 1 < genNum){
            long maxIterNumber = minIterNumber + (long)(1 / eps / genNum);
            for (long x = minIterNumber; x < maxIterNumber; x++)
                sum += 1 / (x * sin(x));
        }
        else
            for (long x = minIterNumber; abs(term) > eps; x++)
                sum += term = 1 / (x * sin(x));

        return sum;
    }

//    private void printProgress() {
//        if (x % step == 0 && set.add(10 * x / capacity)){
//            long progress = Collections.max(set);
//            long least = 100 - progress;
//            String progressString = repeat("=", progress) + repeat("-", least);
//            System.out.println("[" + progressString + "] " + Collections.max(set) + "%");
//        }
//    }


    private static String repeat(String symbol, long num){
        String res = "";
        for (int i = 0; i < Math.abs(num); i++){
            res += symbol;
        }
        return res;
    }
}
