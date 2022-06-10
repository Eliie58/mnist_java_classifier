package epita.fr.mnist.utils;

import java.util.Collections;
import java.util.List;

public class Utils {


    private static final Integer THRESHOLD = 100;
    private static final String ABOVE_THRESHOLD = "xx";
    private static final String BELOW_THRESHOLD = "  ";
    private static final String NEW_LINE = "\r\n";

    public static void showMatrix(double[][] matrix) {
        StringBuilder output = new StringBuilder();
        for (double[] row : matrix) {
            for (double value : row) {
                output.append(getCharacter(value));
            }
            output.append(NEW_LINE);
        }
        System.out.println(output);
    }


    /**
     * @param value the value to print
     * @return the character to print according to the value of the character
     */
    private static String getCharacter(double value) {
        String result = BELOW_THRESHOLD;
        if (value > THRESHOLD) {
            result = ABOVE_THRESHOLD;
        }
        return result;
    }

    public static double getAverage(List<Double> list) {
        double average = 0.0;
        for (double value : list) {
            average += value;
        }
        return average / list.size();
    }

    public static double getMedian(List<Double> list) {
        Collections.sort(list);
        if (list.size() % 2 == 0) {
            return (list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2;
        }
        return list.get(list.size() / 2);
    }
}
