package epita.fr.mnist.service;

import epita.fr.mnist.model.Image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ImageCsvDAO {

    private static final Integer MATRIX_SIZE = 28;

    public List<Image> getAllImages(String fileName) throws IOException {

        List<double[]> fileData = readCsvFile(fileName);
        return fileData.stream().map(row -> new Image(row[0], getMatrixFromArray(row))).collect(Collectors.toList());
    }

    private List<double[]> readCsvFile(String fileName) throws IOException {

        // Create File instance
        List<String> lines = getFileData(fileName);
        // Skip the header
        lines.remove(0);

        return lines.stream().map(this::convertLine).collect(Collectors.toList());
    }

    /**
     * Return all lines from File
     */
    public List<String> getFileData(String fileName) throws IOException {
        File file = new File(fileName);
        return Files.readAllLines(file.toPath());
    }

    /**
     * returns a double array constructed from the input string, after splitting by comma
     *
     * @param line the input line to split
     * @return double array
     */
    public double[] convertLine(String line) {
        return Arrays.stream(getLineParts(line)).mapToDouble(Double::parseDouble).toArray();
    }

    /**
     * Split a line into an array of Strings, using `,`
     *
     * @param line the input line to split
     * @return string array
     */
    public String[] getLineParts(String line) {
        return line.split(",");
    }

    /**
     * Transform 1-dimensional double array into 2-dimensional double array
     *
     * @param array the array to convert
     * @return Transform array of double into a matrix of double using MATRIX_SIZE
     */
    public double[][] getMatrixFromArray(double[] array) {

        int skip = 1;
        double[][] matrix = new double[MATRIX_SIZE][];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            matrix[i] = Arrays.copyOfRange(array, i * MATRIX_SIZE + skip, (i + 1) * MATRIX_SIZE + skip);
        }
        return matrix;
    }
}
