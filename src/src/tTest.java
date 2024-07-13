import java.util.ArrayList;
import java.util.List;

public class tTest {
    public static double tStatCalc(double mean1, double mean2, double var1, double var2, int n1, int n2){
        double tStat = (mean1 - mean2) / Math.sqrt(var1/n1 + var2/n2);
        return tStat;
    }

    public static int degreesOfFreedomCalc(int groupsize1, int groupsize2){
        return groupsize1+groupsize2-2;
    }

    //two-sided Students t-Test
    public static double tTester(TCriticalValues criticalValues, double tStat, double significanceLevel, double degreesOfFreedom){
        double p_value = 0;
        if(significanceLevel == 0.1){List<Double> criticalVs = criticalValues.getLevel0_10();}
        else if(significanceLevel == 0.05){List<Double> criticalVs = criticalValues.getLevel0_05();}
        else if(significanceLevel == 0.025){List<Double> criticalVs = criticalValues.getLevel0_025();}
        else if(significanceLevel == 0.01){List<Double> criticalVs = criticalValues.getLevel0_01();}
        else{List<Double> criticalVs = criticalValues.getLevel0_005();}
        return p_value;
    }

}
