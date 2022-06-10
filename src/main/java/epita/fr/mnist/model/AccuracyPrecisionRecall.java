package epita.fr.mnist.model;

public record AccuracyPrecisionRecall(double accuracy, double precision, double recall) {


    public double getAccuracy() {
        return accuracy;
    }

    public double getPrecision() {
        return precision;
    }

    public double getRecall() {
        return recall;
    }
}
