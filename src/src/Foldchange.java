import java.util.ArrayList;
import java.util.List;

public class Foldchange {

    public static List<List<Double>> estimateFoldchange(Counts counts){
        List<List<Double>> foldchange = new ArrayList<>();
        List<List<Double>> mean = calculateRowStatistics.meanCalculator(counts);

        for (int i = 0; i < mean.size(); i++){
            double mean0 = mean.get(i).get(0);
            double mean1 = mean.get(i).get(1);
            if (mean1 == 0){
                mean1 = 1E-6;
            }
            if (mean0 == 0){
                mean0 = 1E-6;
            }
            List<Double> fold = new ArrayList<>();
            fold.add( Math.log(mean0 / mean1) / Math.log(2));
            foldchange.add(fold);
        }
        return foldchange;
    }


    public static void main(String[] args) {
        String filePath = args[0];
        Counts csvData = CsvReader.readInCsv(filePath, ";");
        System.out.println(estimateFoldchange(csvData));
    }
}
