public class Normalize {

    /**
     * Method to normalize the raw counts using CPM (Counts Per Million).
     *
     * @param counts: the input reads in a matrix, can be retrieved from CsvReader.convertToMatrix.
     * @return double matrix containing normalized counts.
     */
    public static double[][] normalize(int[][] counts) {
        final double[][] normalizedCounts = new double[counts.length][counts[0].length];
        final int[] totalReads = new int[counts[0].length];

        //fill totalReads with the sum of all reads contained in one sample (in one column)
        for (int j = 0; j < counts[0].length; j++) {
            int sum = 0;
            for (int i = 0; i < counts.length; i++) {
                sum += counts[i][j];
            }
            totalReads[j] = sum;
        }

        //compute normalized counts and fill matrix
        for (int j = 0; j < counts[0].length; j++) {
            for (int i = 0; i < counts.length; i++) {
                normalizedCounts[i][j] = (counts[i][j] * 1E6) / totalReads[j];
            }
        }

        return normalizedCounts;
    }

    //main to test
    public static void main(String[] args) {
        String filePath = args[0];
        Counts csvData = CsvReader.readInCsv(filePath, ";");

        for (int i = 0; i < 5; i++){
            for (int j = 0 ; j < 5; j++){
                System.out.print(normalize(CsvReader.convertToMatrix(csvData))[i][j] + " ");
            }
            System.out.println();
        }
    }
}
