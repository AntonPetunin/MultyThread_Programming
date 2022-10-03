import java.io.IOException;
import java.util.*;

import static java.lang.Math.abs;
import static java.lang.Math.sin;

public class MathUtilClass {

    public static double calcSinEndlessRow(double eps/*, Map<Integer, Double> fxs*/) throws IOException {
        double sum = 0.0;
        double term = 10E+7;
//        int step = (int) (0.001 / eps);
//        long capacity = (long) (0.1 / eps);
//        Set<Long> set = new HashSet<>();

        for (long x = 1; abs(term) > eps; x++){
            sum += term = 1 / (x * sin(x));

//            if (x % step == 0 && set.add(10 * x / capacity)){
//                long progress = Collections.max(set);
//                long least = 100 - progress;
//                String progressString = repeat("=", progress) + repeat(">", 1) + repeat("-", least);
//                System.out.println("[" + progressString + "] " + Collections.max(set) + "%");
//            }
//            fxs.put(x, term);
        }

        return sum;
    }


    private static String repeat(String symbol, long num){
        String res = "";
        for (int i = 0; i < Math.abs(num); i++){
            res += symbol;
        }
        return res;
    }
}
