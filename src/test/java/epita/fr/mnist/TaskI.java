package epita.fr.mnist;

import epita.fr.mnist.model.Image;
import epita.fr.mnist.service.Classifier;
import epita.fr.mnist.service.ImageCsvDAO;
import epita.fr.mnist.service.ModelEvaluator;

import java.io.IOException;
import java.util.List;

public class TaskI {

    public static void main(String[] args) throws IOException {

        TaskI taskI = new TaskI();
        taskI.test();
    }

    public void test() throws IOException {

        System.out.println("Task I: Improve the model");

        ImageCsvDAO imageCsvDAO = new ImageCsvDAO();
        List<Image> images = imageCsvDAO.getAllImages("src/test/resources/train.csv");

        Classifier centroidClassifier = new Classifier(Mode.Median);
        centroidClassifier.trainCentroids(images);
        ModelEvaluator modelEvaluator = new ModelEvaluator(centroidClassifier);
        System.out.println("Evaluating Centroid Classifier");
        modelEvaluator.evaluateClassifier("src/test/resources/test.csv");
    }
}
