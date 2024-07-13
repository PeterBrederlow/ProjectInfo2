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

    public Counts(List<String> list1, List<String> list2 , List<List<Integer>> list3) {
        this.geneNames = list1;
        this.descriptors = list2;
        this.counts = list3;
    }

    public Counts(List<String> list1, List<String> list2 , List<List<Integer>> list3, List<List<Double>> list4 , List<List<Double>> list5, List<List<Double>> list6) {
        this.geneNames = list1;
        this.descriptors = list2;
        this.counts = list3;
        this.mean = list4;
        this.variance = list5;
        this.normCounts = list6;
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
}
