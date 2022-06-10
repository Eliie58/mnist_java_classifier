package epita.fr.mnist;

import epita.fr.mnist.service.ImageCsvDAO;

import java.io.IOException;
import java.util.Arrays;

public class TaskBa {

    public static void main(String[] args) throws IOException {

        TaskBa taskBa = new TaskBa();
        taskBa.test();
    }

    public double[][] test() throws IOException {

        TaskAc taskAc = new TaskAc();
        double[] doubles = taskAc.test();
        ImageCsvDAO imageCsvDAO = new ImageCsvDAO();
        double[][] matrix = imageCsvDAO.getMatrixFromArray(doubles);

        System.out.println("Task B : Transform the array of double extracted from the remaining 784 columns to a 2d square matrix (28 x 28)");
        System.out.printf("Matrix is %s%n", Arrays.deepToString(matrix));
        return matrix;
    }
}
