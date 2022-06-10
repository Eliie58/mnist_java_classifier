package epita.fr.mnist.model;

import epita.fr.mnist.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Representative {

    private final double label;
    private List<List<List<Double>>> centroid;
    private double[][] result;

    public Representative(double label) {
        this.label = label;
    }

    public double getLabel() {
        return label;
    }

    public void addPoint(double[][] point) {
        if (centroid == null) {
            centroid = new ArrayList<>();
            return;
        }
        for (int i = 0; i < point.length; i++) {
            for (int j = 0; j < point[0].length; j++) {
                if (centroid.size() <= i) {
                    List<List<Double>> atI = new ArrayList<>();
                    centroid.add(atI);
                }
                List<List<Double>> atI = centroid.get(i);
                if (atI.size() <= j) {
                    List<Double> atJ = new ArrayList<>();
                    atI.add(atJ);
                }
                List<Double> atJ = atI.get(j);
                atJ.add(point[i][j]);
            }
        }
    }

    public void calculateAverage(boolean printResults) {
        result = new double[centroid.size()][centroid.get(0).size()];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = Utils.getAverage(centroid.get(i).get(j));
            }
        }
        if (!printResults) {
            return;
        }
        System.out.println("Centroid of " + getLabel() + " is ");
        Utils.showMatrix(result);
    }

    public void calculateMedian(boolean printResults) {
        result = new double[centroid.size()][centroid.get(0).size()];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = Utils.getMedian(centroid.get(i).get(j));
            }
        }
        if (!printResults) {
            return;
        }
        System.out.println("Centroid of " + getLabel() + " is ");
        Utils.showMatrix(result);
    }

    public double[][] getResult() {
        return result;
    }


}
