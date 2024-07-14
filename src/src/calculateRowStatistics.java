import java.util.ArrayList;
import java.util.List;

public class calculateRowStatistics {

    //assuming there are only two groups to compare...assign groups (0/1) after descriptors
    public static int[] groupDefiner(Counts count){
        List<String> descriptors = count.getDescriptors();
        List<String> uniqueElements = new ArrayList<>();
        for (String descriptor : descriptors) {
            if (!uniqueElements.contains(descriptor)) {
                uniqueElements.add(descriptor);
            }
        }
        int colnumber = descriptors.size();
        int[] group = new int[colnumber];
        int groupcountN1 =0 ;
        int groupcountN2 = 0;
        for(int i = 0; i < colnumber; i++){
            if(descriptors.get(i) == uniqueElements.get(0)){
                group[i] = 0;
                groupcountN1++;
            }
            else{
                group[i] = 1;
                groupcountN2++;
            }
        }
        count.setGroup(group);
        count.setGroupsizeN1(groupcountN1);
        count.setGroupsizeN2(groupcountN2);
        return group;
    }

    public static List<List<Double>> meanCalculator(Counts counts){
        List<List<Integer>> count = counts.getCounts();
        List<List<Double>> meanValues = new ArrayList<>();
        int[] group = groupDefiner(counts);
        int rownumber = count.size();
        int colnumber = count.get(0).size();
            for(int i = 0; i < rownumber; i++) {
                List<Integer> row = count.get(i);
                int CounterOne = 0;
                int CounterTwo = 0;
                int MagnitudeOne = 0;
                int MagnitudeTwo = 0;
                for (int j = 0; j < colnumber; j++) {
                    if(group[j] == 0){
                        CounterOne++;
                        MagnitudeOne = MagnitudeOne + row.get(j);
                    }
                    else{
                        CounterTwo++;
                        MagnitudeTwo = MagnitudeTwo + row.get(j);
                    }
                }
                double meanOne = (CounterOne != 0) ? MagnitudeOne / CounterOne : 0;
                double meanTwo = (CounterTwo != 0) ? MagnitudeTwo / CounterTwo : 0;
                List<Double> groupMeans = new ArrayList<>();
                groupMeans.add(meanOne);
                groupMeans.add(meanTwo);
                meanValues.add(groupMeans);
        }
        return meanValues;
    }

    public static Counts rowStatisticsCalculator(Counts counts, int[] group){
        List<List<Double>> variances = new ArrayList<>();
        List<List<Double>> meanValues = new ArrayList<>();
        List<List<Double>> count = counts.getNormCounts();
        int rownumber = count.size();
        int colnumber = count.get(0).size();
        for(int i = 0; i < rownumber; i++) {
            List<Double> row = count.get(i);
            double CounterOne = 0;
            double CounterTwo = 0;
            double MagnitudeOne = 0;
            double MagnitudeTwo = 0;
            for (int j = 0; j < colnumber; j++) {
                if(group[j] == 0){
                    CounterOne++;
                    MagnitudeOne = MagnitudeOne + row.get(j);
                }
                else{
                    CounterTwo++;
                    MagnitudeTwo = MagnitudeTwo + row.get(j);
                }
            }
            double meanOne = (CounterOne != 0) ? MagnitudeOne / CounterOne : 0;
            double meanTwo = (CounterTwo != 0) ? MagnitudeTwo / CounterTwo : 0;
            double degreesOfFreedom1 = CounterOne-1;
            double degreesOfFreedom2 = CounterTwo-1;
            double sumOfSquaresOne = 0;
            double sumOfSquaresTwo = 0;
            for (int j = 0; j < colnumber; j++) {
                if (group[j] == 0) {
                    sumOfSquaresOne += Math.pow(row.get(j) - meanOne, 2);
                } else {
                    sumOfSquaresTwo += Math.pow(row.get(j) - meanTwo, 2);
                }
            }
            double varianceOne = (CounterOne > 1) ? sumOfSquaresOne / degreesOfFreedom1 : 0;
            double varianceTwo = (CounterTwo > 1) ? sumOfSquaresTwo / degreesOfFreedom2 : 0;
            List<Double> groupMeans = new ArrayList<>(row);
            List<Double> groupVariances = new ArrayList<>(row);
            groupMeans.add(meanOne);
            groupMeans.add(meanTwo);
            groupVariances.add(meanOne);
            groupVariances.add(meanTwo);
            meanValues.add(groupMeans);
            variances.add(groupVariances);
        }
        counts.setMean(meanValues);
        counts.setVariance(variances);
        return counts;
    }

    public static void main(String[] args) {
        String filePath = args[0];
        Counts csvData = CsvReader.readInCsv(filePath, ";");
        int[] group = groupDefiner(csvData);
        csvData = calculateRowStatistics.rowStatisticsCalculator(csvData, group);

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
