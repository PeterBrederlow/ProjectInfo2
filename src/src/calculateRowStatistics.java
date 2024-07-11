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
        for(int i = 0; i < colnumber; i++){
            if(descriptors.get(i) == uniqueElements.get(0)){
                group[i] = 0;
            }
            else{
                group[i] = 1;
            }
        }
        count.setGroup(group);
        return group;
    }

    public static List<List<Integer>> meanCalculator(Counts counts, int[] group){
        List<List<Integer>> count = counts.getCounts();
        List<List<Integer>> meanValues = new ArrayList<>();
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
                int meanOne = (CounterOne != 0) ? MagnitudeOne / CounterOne : 0;
                int meanTwo = (CounterTwo != 0) ? MagnitudeTwo / CounterTwo : 0;
                List<Integer> groupMeans = new ArrayList<>(row);
                groupMeans.add(meanOne);
                groupMeans.add(meanTwo);
                meanValues.add(groupMeans);
        }
        return meanValues;
    }

    public static void rowStatisticsCalculator(Counts counts, int[] group){
        List<List<Integer>> variances = new ArrayList<>();
        List<List<Integer>> meanValues = new ArrayList<>();
        List<List<Integer>> count = counts.getCounts();
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
            int meanOne = (CounterOne != 0) ? MagnitudeOne / CounterOne : 0;
            int meanTwo = (CounterTwo != 0) ? MagnitudeTwo / CounterTwo : 0;
            int degreesOfFreedom1 = CounterOne-1;
            int degreesOfFreedom2 = CounterTwo-1;
            int sumOfSquaresOne = 0;
            int sumOfSquaresTwo = 0;
            for (int j = 0; j < colnumber; j++) {
                if (group[j] == 0) {
                    sumOfSquaresOne += Math.pow(row.get(j) - meanOne, 2);
                } else {
                    sumOfSquaresTwo += Math.pow(row.get(j) - meanTwo, 2);
                }
            }
            int varianceOne = (CounterOne > 1) ? sumOfSquaresOne / degreesOfFreedom1 : 0;
            int varianceTwo = (CounterTwo > 1) ? sumOfSquaresTwo / degreesOfFreedom2 : 0;
            List<Integer> groupMeans = new ArrayList<>(row);
            List<Integer> groupVariances = new ArrayList<>(row);
            groupMeans.add(meanOne);
            groupMeans.add(meanTwo);
            groupVariances.add(meanOne);
            groupVariances.add(meanTwo);
            meanValues.add(groupMeans);
            variances.add(groupVariances);
        }
        counts.setMean(meanValues);
        counts.setVariance(variances);
    }
}
