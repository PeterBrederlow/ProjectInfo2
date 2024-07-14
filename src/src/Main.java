import java.util.List;


/**
Main class to run program.
To run program, give your .csv file as arg[0].
Note: you need to activate libraries first!
 */
public class Main {
    public static void main(String[] args) {
        String filePath = args[0];
        System.out.println("--- File: " + filePath + " ---");
        Counts csvData = CsvReader.readInCsv(filePath, ";");
        Normalize.normalize(csvData);
        calculateRowStatistics.groupDefiner(csvData);
        calculateRowStatistics.rowStatisticsCalculator(csvData, new int[] {0, 1});
        Foldchange.estimateFoldchange(csvData);


        System.out.println(csvData.getDescriptors());
        System.out.println(csvData.getCounts().get(0));
        System.out.println(csvData.getNormCounts().get(0));
        System.out.println(csvData.getVariance().get(0));
        System.out.println(csvData.getMean().get(0));
    }
}