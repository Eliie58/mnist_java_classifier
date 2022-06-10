package epita.fr.mnist;

import epita.fr.mnist.service.ImageCsvDAO;

import java.io.IOException;
import java.util.Arrays;

public class TaskAc {

    public static void main(String[] args) throws IOException {

        TaskAc taskAc = new TaskAc();
        taskAc.test();
    }

    public double[] test() throws IOException {

        TaskAb taskAb = new TaskAb();
        String line = taskAb.test();

        ImageCsvDAO imageCsvDAO = new ImageCsvDAO();
        System.out.println("Task A : Convert this array of String into an array of double");
        double[] doubleArray = imageCsvDAO.convertLine(line);
        System.out.printf("Line Double Parts are %s%n", Arrays.toString(doubleArray));
        return doubleArray;
    }
}
