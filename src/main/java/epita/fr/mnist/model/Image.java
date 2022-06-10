package epita.fr.mnist.model;

import java.util.Arrays;

public record Image(double label, double[][] dataMatrix) {

    public double getLabel() {
        return label;
    }

    public double[][] getDataMatrix() {
        return dataMatrix;
    }

    @Override
    public String toString() {
        return "Image{" + "label='" + label + '\'' + ", dataMatrix=" + Arrays.toString(dataMatrix) + '}';
    }
}
