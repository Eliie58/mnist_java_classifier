package epita.fr.mnist;

import epita.fr.mnist.service.ImageCsvDAO;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TaskAb {

    public static void main(String[] args) throws IOException {

        TaskAb taskAb = new TaskAb();
        taskAb.test();
    }

    public String test() throws IOException {

        TaskAa taskAa = new TaskAa();
        List<String> fileData = taskAa.test();

        ImageCsvDAO imageCsvDAO = new ImageCsvDAO();
        System.out.println("Task A : Get an array of String from this line");
        String line = fileData.get(23);
        System.out.printf("Line is %s%n", line);
        String[] lineParts = imageCsvDAO.getLineParts(fileData.get(1));
        System.out.printf("Line Parts are %s%n", Arrays.toString(lineParts));
        return line;
    }
}
