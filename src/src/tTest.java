import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.distribution.TDistribution;

public class tTest {
    /**
     * Method that calculates the t Statistic for a gene, which is used for further calculations
     *
     * @param  mean1 arithmetic mean of first group
     * @param  mean2 arithmetic mean of second group
     * @param  var1 variance of first group
     * @param  var2 variance of second group
     * @return t Statistic for a gene
     */
    public static double tStatCalc(double mean1, double mean2, double var1, double var2, int n1, int n2){
        double tStat = (mean1 - mean2) / Math.sqrt(var1/n1 + var2/n2);
        return tStat;
    }

    /**
     * Method that calculates the degrees of freedom for the tes
     *
     * @param  groupsize1 Size of first group to compare
     * @param  groupsize2 Size of second group to compare
     * @return t Statistic for a gene
     */
    public static int degreesOfFreedomCalc(int groupsize1, int groupsize2){
        return groupsize1+groupsize2-2;
    }


    /**
     * Simple t Test that only says whether a gene is significant or not
     *
     * @param  criticalValues Table of critical values, if tStat is higher -> significant, otherwise not
     * @param  tStat of gene when comparing group1 and 2
     * @param  significanceLevel Defines: when is a gene considered to be differentially expressed
     * @param  degreesOfFreedom to calculate t Test
     * @return Is a gene differentially expressed
     */
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

    /**
     * two-sided Students t-Test using a T Distribution from the math.commons library
     *
     * @param  tStat of gene when comparing group1 and 2
     * @param  degreesOfFreedom to calculate t Test
     * @return p-value of t-test to compare group 1 and 2 for one gene
     */
    public static double finalCalculator(double tStat, double degreesOfFreedom){
        TDistribution tDistribution = new TDistribution(degreesOfFreedom);
        return 2 * (1 - tDistribution.cumulativeProbability(Math.abs(tStat)));
    }


    /**
     * T-Test for all genes
     *
     * @param  count is a count object with normalized counts and their regarding statistics and groups
     * @return p-value after t-test for all genes
     */
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

    public static void filterSignificance(Counts count){
        double[] pValue = new double[count.getAdjPValue().size()];
        double[] logFoldchange = new double[count.getAdjPValue().size()];

        for (int i = 0 ; i < count.getAdjPValue().size(); i++){
            if (count.getAdjPValue().get(i) < 0.05){
                pValue[i] = count.getAdjPValue().get(i);
                logFoldchange[i] = count.getFoldchange().get(i).get(0);
            }
        }

        count.setLogFoldchange(logFoldchange);
        count.setAdjPVal(pValue);
    }
}
