package epita.fr.mnist;

import epita.fr.mnist.model.Image;
import epita.fr.mnist.service.Classifier;
import epita.fr.mnist.service.ImageCsvDAO;

import java.io.IOException;
import java.util.List;

public class TaskD {

    public static void main(String[] args) throws IOException {

        TaskD taskD = new TaskD();
        taskD.test();
    }

    public void test() throws IOException {

        ImageCsvDAO imageCsvDAO = new ImageCsvDAO();
        List<Image> images = imageCsvDAO.getAllImages("src/test/resources/train.csv");
        Classifier centroidClassifier = new Classifier(Mode.Average);
        System.out.println("Task D : Get the total count of occurrences in the data set for a digit");

        double[] labels = new double[]{0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};
        for (double label : labels) {
            long count = centroidClassifier.calculateDistribution(images, label);
            System.out.printf("Occurrences of %s count is %s%n", label, count);
        }

    }
}
