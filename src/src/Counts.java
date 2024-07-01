import java.util.List;

public class Counts {
    private List<String> geneNames;
    private List<String> descriptors;
    private List<List<Integer>> counts;

    public Counts(List<String> list1, List<String> list2 , List<List<Integer>> list3) {
        this.geneNames = list1;
        this.descriptors = list2;
        this.counts = list3;
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
}
