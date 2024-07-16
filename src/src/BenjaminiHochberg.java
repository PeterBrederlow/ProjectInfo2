import java.util.ArrayList;
import java.util.List;

public class BenjaminiHochberg {
    /**
     * creates a double array containing the p-values
     *
     * @param  count containing the p-values from the t-Test
     * @return p-value array
     */
    public static double[] pValueToArray(Counts count){
        List<Double> pValues = count.getPValue();
        double[] doubleArray = new double[pValues.size()];
        for (int i = 0; i < pValues.size(); i++) {
            doubleArray[i] = pValues.get(i);
        }
        return doubleArray;
    }

    //Methode aus Foliensatz 22 der VO Praktische Informatik2
    /**
     * sorts after p-values
     *
     * @param  array is an unsorted double array, in this case for p-values
     * @return sorted p-value array
     */
    public static double[] insertionSort(double[] array) {
        for (int i = 1; i < array.length; i++) {
            double key = array[i];
            int j = i - 1;
        // Verschiebe Elemente des Arrays, die größer als der Schlüssel sind,
        // eine Position nach rechts
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
        return array;
    }

    /**
     * works similar to insertionSort, but creates a mapping, so that adjusted p-Values can get
     * mapped back to their original position
     *
     * @param  array is an unsorted double array, in this case for p-values
     * @return List containing the index prior and after sorting
     */
    public static List<Integer> insertionSortMapper(double[] array) {
        int n = array.length;
        List<Integer> indexMapping = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            indexMapping.add(i);
        }

        for (int i = 1; i < n; i++) {
            double key = array[i];
            int keyIndex = indexMapping.get(i);
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                indexMapping.set(j + 1, indexMapping.get(j));
                j = j - 1;
            }
            array[j + 1] = key;
            indexMapping.set(j + 1, keyIndex);
        }

        return indexMapping;
    }

    /**
     * Benjamini Hochberg correction for multiple testing, to adjust for false positive
     * significant p-values. Here is an explanation link:
     * https://www.youtube.com/watch?v=K8LQSvtjcEo&t=618s
     *
     * @param  count containing the unsorted p-values
     *         the unsorted p-values get sorted, then the Benjamini Hochberg adjusted p-values
     *         are calculated and mapped back to their original position. After that they also
     *         get set in the count object.
     * @return List containing the adjusted p-values
     */
    public static List<Double> BenjaminiHochberg(Counts count){
        double[] unsortedPValues = pValueToArray(count);
        List<Integer> indexMapping = insertionSortMapper(unsortedPValues);
        double[] sortedpValues = insertionSort(unsortedPValues);
        double[] adjPValues = new double[sortedpValues.length];
        for(int i = sortedpValues.length-1; i >= 0; i--){
            double adjP = Math.max(sortedpValues[i], sortedpValues[i] * ((double) sortedpValues.length /(i+1)));
            adjPValues[i] = adjP;
        }
        List<Double> originalOrderAdjPValues = new ArrayList<>();
        for (int i = 0; i < adjPValues.length; i++) {
            originalOrderAdjPValues.add(0.0);
        }
        for (int i = 0; i < indexMapping.size(); i++) {
            originalOrderAdjPValues.set(indexMapping.get(i), adjPValues[i]);
        }
        count.setAdjPValue(originalOrderAdjPValues);
        return originalOrderAdjPValues;
    }
}
