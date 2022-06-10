package epita.fr.mnist;

import epita.fr.mnist.utils.Utils;

import java.io.IOException;

public class TaskBb {

    public static void main(String[] args) throws IOException {

        TaskBb taskBb = new TaskBb();
        taskBb.test();
    }

    public void test() throws IOException {

        TaskBa taskBa = new TaskBa();
        double[][] matrix = taskBa.test();
        System.out.println("Task B : Write a method “showMatrix” that prints a matrix in the console");
        Utils.showMatrix(matrix);
    }
}
