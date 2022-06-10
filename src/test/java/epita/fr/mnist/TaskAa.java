package epita.fr.mnist;

import epita.fr.mnist.service.ImageCsvDAO;

import java.io.IOException;
import java.util.List;

public class TaskAa {

    public static void main(String[] args) throws IOException {

        TaskAa taskAa = new TaskAa();
        taskAa.test();
    }

    public List<String> test() throws IOException {
        String testFileName = "src/test/resources/train.csv";
        ImageCsvDAO imageCsvDAO = new ImageCsvDAO();
        List<String> fileData = imageCsvDAO.getFileData(testFileName);

        System.out.println("Task A : Print the 2 first lines in the console");
        System.out.println(fileData.get(1));
        System.out.println(fileData.get(1));
        return fileData;

    }
}
