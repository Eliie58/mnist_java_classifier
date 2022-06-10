package epita.fr.mnist;

import epita.fr.mnist.model.Image;
import epita.fr.mnist.service.Classifier;
import epita.fr.mnist.service.ImageCsvDAO;
import epita.fr.mnist.service.ModelEvaluator;

import java.io.IOException;
import java.util.List;

public class TaskH {

    public static void main(String[] args) throws IOException {

        TaskH taskH = new TaskH();
        taskH.test();
    }

    public void test() throws IOException {

        System.out.println("Task H: Implement classification performance assessment");

        ImageCsvDAO imageCsvDAO = new ImageCsvDAO();
        List<Image> images = imageCsvDAO.getAllImages("src/test/resources/train.csv");

        Classifier centroidClassifier = new Classifier(Mode.Average);
        centroidClassifier.trainCentroids(images);
        ModelEvaluator modelEvaluator = new ModelEvaluator(centroidClassifier);
        System.out.println("Evaluating Centroid Classifier");
        modelEvaluator.evaluateClassifier("src/test/resources/test.csv");
    }
}
