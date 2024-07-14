import java.util.List;

public class Counts {
    private List<String> geneNames;
    private List<String> descriptors;
    private List<List<Integer>> counts;
    private List<List<Double>> normCounts;
    private int[] groups;
    private int groupsizeN1;
    private int groupsizeN2;
    private List<List<Double>> mean;
    private List<List<Double>> variance;
    private List<Double> pValue;
    private List<Double> adjPValue;
    private List<List<Double>> foldchange;

    public Counts(List<String> list1, List<String> list2 , List<List<Integer>> list3) {
        this.geneNames = list1;
        this.descriptors = list2;
        this.counts = list3;
    }

    public Counts(List<String> list1, List<String> list2 , List<List<Integer>> list3, List<List<Double>> list4 , List<List<Double>> list5, List<List<Double>> list6, List<List<Double>> list7) {
        this.geneNames = list1;
        this.descriptors = list2;
        this.counts = list3;
        this.mean = list4;
        this.variance = list5;
        this.normCounts = list6;
        this.foldchange = list7;
    }

    public List<String> getDescriptors() {
        return descriptors;
    }

    public List<String> getGeneNames() {
        return geneNames;
    }

    public List<List<Integer>> getCounts() {
        return counts;
    }

    public List<List<Double>> getNormCounts() {
        return normCounts;
    }

    public int getGroupsizeN1() {
        return groupsizeN1;
    }

    public int getGroupsizeN2() {
        return groupsizeN2;
    }

    public List<List<Double>> getMean() {
        return mean;
    }

    public List<List<Double>> getVariance() {
        return variance;
    }

    public List<Double> getPValue() {
        return pValue;
    }

    public List<Double> getAdjPValue() {
        return adjPValue;
    }

    public void setGroup(int[] group) {
        this.groups = group;
    }

    public void setGroupsizeN1(int groupsizeN1) {
        this.groupsizeN1 = groupsizeN1;
    }

    public void setGroupsizeN2(int groupsizeN2) {
        this.groupsizeN2 = groupsizeN2;
    }

    public void setNormCounts( List<List<Double>> normCounts) {
        this.normCounts = normCounts;
    }

    public void setMean(List<List<Double>> mean) {
        this.mean = mean;
    }

    public void setVariance(List<List<Double>> variance) {
        this.variance = variance;
    }

    public void setpValue(List<Double> pValue) {
        this.pValue = pValue;
    }

    public void setAdjPValue(List<Double> adjPValue) {
        this.adjPValue = adjPValue;
    }

    public void setFoldchange(List<List<Double>> foldchange) {this.foldchange = foldchange;}

    public List<List<Double>> getFoldchange() {return this.foldchange;}

}
