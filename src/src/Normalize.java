import java.util.ArrayList;
import java.util.List;

public class Normalize {

    /**
     * Method to normalize the raw counts using CPM (Counts Per Million).
     *
     * @param count: the input reads in a Counts object.
     * @return nested list containing normalized counts.
     */
    public static void normalize(Counts count) {
        int[][] counts = CsvReader.convertToMatrix(count);
        List<List<Double>> normCounts = new ArrayList<>();
        List<Double> rowCounts = new ArrayList<>();
        final int[] totalReads = new int[counts[0].length];

        //fill totalReads with the sum of all reads contained in one sample (in one column)
        for (int j = 0; j < counts[0].length; j++) {
            int sum = 0;
            for (int i = 0; i < counts.length; i++) {
                sum += counts[i][j];
            }
            totalReads[j] = sum;
        }

        //compute normalized counts and fill list
        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts[i].length; j++) {
                rowCounts.add((counts[i][j] * 1E6) / totalReads[j]);
            }
            normCounts.add(rowCounts);
            rowCounts = new ArrayList<>();
        }
        count.setNormCounts(normCounts);
    }
}
