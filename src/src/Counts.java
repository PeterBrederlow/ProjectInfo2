import java.util.List;

public class Counts {
    private List<String> geneNames;
    private List<String> descriptors;
    private List<List<Integer>> counts;
    private List<List<Double>> normCounts;
    private int[] groups;
    private List<List<Integer>> mean;
    private List<List<Integer>> variance;

    public Counts(List<String> list1, List<String> list2 , List<List<Integer>> list3) {
        this.geneNames = list1;
        this.descriptors = list2;
        this.counts = list3;
    }

    public Counts(List<String> list1, List<String> list2 , List<List<Integer>> list3, List<List<Integer>> list4 , List<List<Integer>> list5, List<List<Double>> list6) {
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

    public List<List<Double>> getNormCountsounts() {
        return normCounts;
    }

    public void setGroup(int[] group) {
        this.groups = group;
    }

    public void setNormCounts( List<List<Double>> normCounts) {
        this.normCounts = normCounts;
    }

    public void setMean( List<List<Integer>> mean) {
        this.mean = mean;
    }

    public void setVariance( List<List<Integer>> variance) {
        this.variance = variance;
    }
}
