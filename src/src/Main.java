import org.knowm.xchart.BubbleChart;
import org.knowm.xchart.BubbleChartBuilder;
import org.knowm.xchart.SwingWrapper;
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
        tTest.tTester(csvData);
        BenjaminiHochberg.BenjaminiHochberg(csvData);
        tTest.filterSignificance(csvData);
        Graphics.plot(csvData, filePath);
    }
}