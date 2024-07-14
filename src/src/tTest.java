import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.distribution.TDistribution;

public class tTest {
    public static double tStatCalc(double mean1, double mean2, double var1, double var2, int n1, int n2){
        double tStat = (mean1 - mean2) / Math.sqrt(var1/n1 + var2/n2);
        return tStat;
    }

    public static int degreesOfFreedomCalc(int groupsize1, int groupsize2){
        return groupsize1+groupsize2-2;
    }

    //two-sided Students t-Test simplified
    public static boolean tTesterSimple(TCriticalValues criticalValues, double tStat, double significanceLevel, double degreesOfFreedom){
        double p_value = 0;
        List<Double> criticalVs = new ArrayList<>();
        if(significanceLevel == 0.1){criticalVs = criticalValues.getLevel0_10();}
        else if(significanceLevel == 0.05){criticalVs = criticalValues.getLevel0_05();}
        else if(significanceLevel == 0.025){criticalVs = criticalValues.getLevel0_025();}
        else if(significanceLevel == 0.01){criticalVs = criticalValues.getLevel0_01();}
        else{criticalVs = criticalValues.getLevel0_005();}
        for(int i = 0; i < criticalVs.size(); i++){
            if(i == degreesOfFreedom){
                if(criticalVs.get(i) < tStat){
                    return true;
                }
                else{return false;}
            }
        }
        return false;
    }

    //two-sided Students t-Test with p-value calculation via math commons lib
    public static double finalCalculator(double tStat, double degreesOfFreedom){
        TDistribution tDistribution = new TDistribution(degreesOfFreedom);
        return 2 * (1 - tDistribution.cumulativeProbability(Math.abs(tStat)));
    }


    //actual implementation
    public static List<Double> tTester(Counts count){
        List<Double> pValue = new ArrayList<>();
        int groupsize1 = count.getGroupsizeN1();
        int groupsize2 = count.getGroupsizeN2();
        List<List<Double>> var = count.getVariance();
        List<List<Double>> mean = count.getMean();
        int rownumber = var.size();
        for(int i = 0; i < rownumber; i++) {
            List<Double> rowMean = mean.get(i);
            List<Double> rowVariance = var.get(i);
            double mean1 = rowMean.get(0);
            double mean2 = rowMean.get(1);
            double var1 = rowVariance.get(0);
            double var2 = rowVariance.get(1);
            double tStat = tStatCalc(mean1, mean2, var1, var2, groupsize1, groupsize2);
            int degreesOfFreedom = degreesOfFreedomCalc(groupsize1, groupsize2);
            double p = finalCalculator(tStat, degreesOfFreedom);
            pValue.add(p);
        }
        count.setpValue(pValue);
        return pValue;
    }
}
