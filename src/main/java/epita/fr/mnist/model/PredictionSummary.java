package epita.fr.mnist.model;

import java.util.HashMap;
import java.util.Map;

public class PredictionSummary {

    private final Map<Double, Integer> predictedValueCount;
    private Integer totalCount;

    public PredictionSummary() {
        this.predictedValueCount = new HashMap<>();
        this.totalCount = 0;
    }

    public void addPrediction(double predictedLabel) {
        Integer labelCount = predictedValueCount.get(predictedLabel);
        if (labelCount == null) {
            labelCount = 0;
        }
        totalCount++;
        predictedValueCount.put(predictedLabel, ++labelCount);
    }

    public double getConfusionValue(double value) {
        Integer count = predictedValueCount.get(value);
        if (count == null) {
            return 0.0;
        }
        return (double) count / totalCount;
    }

    public Integer getCount(double label) {
        return predictedValueCount.get(label);
    }

    public Integer getAllButCount(double label) {
        return totalCount - predictedValueCount.get(label);
    }
}
