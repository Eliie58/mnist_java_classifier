package epita.fr.mnist.service;

import epita.fr.mnist.model.AccuracyPrecisionRecall;
import epita.fr.mnist.model.Image;
import epita.fr.mnist.model.PredictionSummary;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class ModelEvaluator {

    private static final NumberFormat formatter = new DecimalFormat("#0.0000");

    private final Classifier classifier;
    private final Map<Double, PredictionSummary> predictionSummaryMap;
    private final Map<Double, Integer> countTracker;
    private final Set<Double> labels;
    private Integer totalCount = 0;

    public ModelEvaluator(Classifier classifier) {
        this.classifier = classifier;
        this.predictionSummaryMap = new HashMap<>();
        this.labels = new HashSet<>();
        this.countTracker = new HashMap<>();
    }

    public void evaluateClassifier(String fileName) throws IOException {
        List<Image> images = getImages(fileName);
        images.forEach(this::predict);
        printResults();
    }

    private List<Image> getImages(String fileName) throws IOException {
        ImageCsvDAO imageCsvDAO = new ImageCsvDAO();
        return imageCsvDAO.getAllImages(fileName);
    }

    private void predict(Image image) {
        this.totalCount++;
        labels.add(image.getLabel());
        double predictedValue = classifier.predict(image);
        PredictionSummary predictionSummary = predictionSummaryMap.get(image.getLabel());
        if (predictionSummary == null) {
            predictionSummary = new PredictionSummary();
            predictionSummaryMap.put(image.getLabel(), predictionSummary);
        }
        predictionSummary.addPrediction(predictedValue);
        Integer predictionCount = countTracker.get(predictedValue);
        if (predictionCount == null) {
            predictionCount = 0;
        }
        countTracker.put(predictedValue, ++predictionCount);
    }

    private void printResults() {
        List<Double> labelsSorted = labels.stream().sorted().toList();
        System.out.print("Confusion Matrix\n\t\t");
        for (Double label : labelsSorted) {
            System.out.print(label + "\t\t");
        }
        System.out.print("\n");
        for (Double label : labelsSorted) {
            System.out.print(label + " : \t");
            PredictionSummary predictionSummary = predictionSummaryMap.get(label);
            for (Double subLabel : labelsSorted) {
                System.out.print(formatter.format(predictionSummary.getConfusionValue(subLabel)) + "\t");
            }
            System.out.print("\n");
        }
        confusionMatrix();
    }

    private void confusionMatrix() {
        double accuracy = 0.0;
        double precision = 0.0;
        double recall = 0.0;
        List<Double> labelsSorted = labels.stream().sorted().toList();
        for (Double label : labelsSorted) {
            AccuracyPrecisionRecall accuracyPrecisionRecall = getAccuracyPrecisionRecall(label);
            accuracy += accuracyPrecisionRecall.getAccuracy();
            precision += accuracyPrecisionRecall.getPrecision();
            recall += accuracyPrecisionRecall.getRecall();
        }
        System.out.printf("Overall Accuracy : %s, Precision : %s, Recall : %s%n", accuracy / labels.size(), precision / labels.size(), recall / labels.size());
    }

    private AccuracyPrecisionRecall getAccuracyPrecisionRecall(Double label) {
        PredictionSummary predictionSummary = predictionSummaryMap.get(label);
        Integer truePositive = predictionSummary.getCount(label);
        Integer falseNegative = predictionSummary.getAllButCount(label);
        Integer falsePositive = countTracker.get(label) - truePositive;
        Integer trueNegative = totalCount - (truePositive + falseNegative + falsePositive);

        double accuracy = (double) (truePositive + trueNegative) / (truePositive + falsePositive + trueNegative + falseNegative);
        double precision = (double) truePositive / (truePositive + falsePositive);
        double recall = (double) truePositive / (truePositive + falseNegative);
        System.out.printf("For %s : Accuracy : %s, Precision : %s, Recall : %s%n", label, accuracy, precision, recall);
        return new AccuracyPrecisionRecall(accuracy, precision, recall);
    }
}
