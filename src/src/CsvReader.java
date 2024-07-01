import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CsvReader {
    /**
     * Method to read in a .csv file
     *
     * @param filepath {@link String} specifying the path to the .csv file.
     * @return Array List consisting of rows each containing the (should be constant) number of columns
     */
    public static Counts readInCsv(String filepath, String delimiter) {
        List<List<Integer>> counts = new ArrayList<>();//nested ArrayList to store data, might need to convert to matrix
        List<String> geneNames = new ArrayList<>();
        List<String> descriptors = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filepath))) {
            Iterator<String> lineIterator = fileReader.lines().iterator();
            String line;
            if (lineIterator.hasNext()) {
                String headerLine = lineIterator.next();
                String[] headers = headerLine.split(delimiter);
                for (int i = 1; i < headers.length; i++) {
                    descriptors.add(headers[i]);
                }
            }
            do {
                line = lineIterator.next();
                String[] columns = line.split(delimiter);
                geneNames.add(columns[0]);
                List<Integer> row = new ArrayList<>();
                for (int i = 1; i < columns.length; i++) { //column 1 is skipped, contains gene names
                    int c = Integer.parseInt(columns[i]);
                    row.add(c); //adds up columns to form a row
                }
                counts.add(row); //adds row to final Array List Counts
            } while (lineIterator.hasNext());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new Counts(geneNames, descriptors, counts);
    }

    /**
     * Method to convert List to a count matrix in form of an array
     *
     * @param  counts is the input Counts object containing the ArrayList with the counts
 *             that resulted from reading in the .csv file.
     * @return Count Matrix
     */
    public static int[][] convertToMatrix(Counts counts){
        List<List<Integer>> count = counts.getCounts();
        int rownumber = count.size();
        int colnumber = count.get(0).size();
        int matrix[][] = new int[rownumber][colnumber];
        for(int i = 0; i < rownumber; i++) {
            List<Integer> row = count.get(i);
            for (int j = 0; j < colnumber; j++) {
                matrix[i][j] = row.get(j) != null ? row.get(j) : 0;
            }
        }
        return matrix;
    }

    //main to test
    public static void main(String[] args) {
        String filePath = args[0];
        Counts csvData = readInCsv(filePath, ";");

        // Print the data
        for (List<Integer> row : csvData.getCounts()) {
            for (Integer column : row) {
                System.out.print(column + " ");
            }
            System.out.println();
        }
        System.out.println(csvData.getGeneNames());
        System.out.println(csvData.getDescriptors());
    }
}
