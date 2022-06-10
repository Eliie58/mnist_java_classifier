package epita.fr.mnist;

import epita.fr.mnist.model.Image;
import epita.fr.mnist.service.Classifier;
import epita.fr.mnist.service.ImageCsvDAO;
import epita.fr.mnist.service.ModelEvaluator;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        ImageCsvDAO imageCsvDAO = new ImageCsvDAO();
        List<Image> images = imageCsvDAO.getAllImages("src/main/resources/mnist_train.csv");

        Classifier centroidClassifier = new Classifier(Mode.Average);
        centroidClassifier.trainCentroids(images);
        ModelEvaluator modelEvaluator = new ModelEvaluator(centroidClassifier);
        System.out.println("Evaluating Centroid Classifier");
        modelEvaluator.evaluateClassifier("src/main/resources/mnist_test.csv");

        Classifier medoidClassifier = new Classifier(Mode.Median);
        medoidClassifier.trainCentroids(images);
        modelEvaluator = new ModelEvaluator(medoidClassifier);
        System.out.println("Evaluating Medoid Classifier");
        modelEvaluator.evaluateClassifier("src/main/resources/mnist_test.csv");

    }
}
