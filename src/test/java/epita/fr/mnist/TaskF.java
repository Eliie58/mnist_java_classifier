package epita.fr.mnist;

import epita.fr.mnist.model.Image;
import epita.fr.mnist.model.Representative;
import epita.fr.mnist.service.Classifier;
import epita.fr.mnist.service.ImageCsvDAO;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TaskF {

    public static void main(String[] args) throws IOException {

        TaskF taskF = new TaskF();
        taskF.test();
    }

    public void test() throws IOException {

        ImageCsvDAO imageCsvDAO = new ImageCsvDAO();
        List<Image> images = imageCsvDAO.getAllImages("src/test/resources/train.csv");
        Classifier centroidClassifier = new Classifier(Mode.Average);
        System.out.println("Task F : Performing your first classification");
        Map<Double, Representative> representativeMap = centroidClassifier.trainCentroids(images);

        List<Image> testImages = imageCsvDAO.getAllImages("src/test/resources/test.csv");
        iterateTestImages(testImages, representativeMap);
    }

    private void iterateTestImages(List<Image> images, Map<Double, Representative> representativeMap) {
        int count = 0;
        Iterator<Image> iterator = images.iterator();
        while (count < 10) {
            Image image = iterator.next();
            if (image.getLabel() == 0.0) {
                count++;
                System.out.printf("Image #%s%n", count);
                calculateDistance(image, representativeMap);
            }
        }

    }

    private void calculateDistance(Image image, Map<Double, Representative> representativeMap) {
        double minDistanceTo = 0.0;
        double minDistance = Double.MAX_VALUE;
        for (Map.Entry<Double, Representative> doubleRepresentativeEntry : representativeMap.entrySet()) {
            double distance = Classifier.getDistanceBetween(image.getDataMatrix(), doubleRepresentativeEntry.getValue().getResult());
            System.out.printf("Distance to %s is %s%n", doubleRepresentativeEntry.getKey(), distance);
            if (distance < minDistance) {
                minDistance = distance;
                minDistanceTo = doubleRepresentativeEntry.getKey();
            }
        }
        System.out.printf("Min Distance is to #%s%n", minDistanceTo);
    }
}
