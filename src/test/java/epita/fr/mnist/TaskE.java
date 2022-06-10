package epita.fr.mnist;

import epita.fr.mnist.model.Image;
import epita.fr.mnist.model.Representative;
import epita.fr.mnist.service.Classifier;
import epita.fr.mnist.service.ImageCsvDAO;
import epita.fr.mnist.utils.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TaskE {
    public static void main(String[] args) throws IOException {

        TaskE taskE = new TaskE();
        taskE.test();
    }

    public void test() throws IOException {

        ImageCsvDAO imageCsvDAO = new ImageCsvDAO();
        List<Image> images = imageCsvDAO.getAllImages("src/test/resources/train.csv");
        Classifier centroidClassifier = new Classifier(Mode.Average);
        System.out.println("Task E : Calculating the average representant");

        Map<Double, Representative> result = centroidClassifier.trainCentroids(images);
        result.forEach((label, representative) -> {
            System.out.printf("Representative for %s%n", label);
            Utils.showMatrix(representative.getResult());
        });
    }
}
