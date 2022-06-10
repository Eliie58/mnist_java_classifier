package epita.fr.mnist.service;

import epita.fr.mnist.Mode;
import epita.fr.mnist.model.Image;
import epita.fr.mnist.model.Representative;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Classifier {

    final private Map<Double, Representative> trainedDataset = new HashMap<>();
    final private Mode mode;

    public Classifier(Mode mode) {
        this.mode = mode;
    }

    public Map<Double, Representative> trainCentroids(List<Image> images) {
        images.forEach(this::processImage);
        switch (mode) {
            case Average -> calculateAverageDataset();
            case Median -> calculateMedianDataset();
        }
        return trainedDataset;
    }

    private void calculateAverageDataset() {
        trainedDataset.forEach((key, value) -> value.calculateAverage(false));
    }

    private void calculateMedianDataset() {
        trainedDataset.forEach((key, value) -> value.calculateMedian(false));
    }

    protected void processImage(Image image) {
        double label = image.getLabel();
        Representative currentValue = trainedDataset.get(label);
        if (currentValue == null) {
            currentValue = new Representative(label);
        }
        currentValue.addPoint(image.getDataMatrix());
        trainedDataset.put(label, currentValue);
    }

    /**
     * @param value the label we want to get its distribution
     * @return the number of occurrences of this label
     */
    public Long calculateDistribution(List<Image> images, double value) {
        return images.stream().filter(image -> image.getLabel() == value).count();
    }

    public double predict(Image image) {
        double label = -1.0;
        double minDistance = Double.MAX_VALUE;
        for (Map.Entry<Double, Representative> doubleRepresentativeEntry : trainedDataset.entrySet()) {
            double distance = getDistanceBetween(image.getDataMatrix(), doubleRepresentativeEntry.getValue().getResult());
            if (distance < minDistance) {
                label = doubleRepresentativeEntry.getKey();
                minDistance = distance;
            }
        }
        return label;
    }

    public static double getDistanceBetween(double[][] dataMatrix, double[][] average) {
        double distance = 0.0;
        for (int i = 0; i < dataMatrix.length; i++) {
            for (int j = 0; j < dataMatrix[0].length; j++) {
                distance += Math.abs(dataMatrix[i][j] - average[i][j]);
            }
        }
        return Math.sqrt(distance);
    }
}
