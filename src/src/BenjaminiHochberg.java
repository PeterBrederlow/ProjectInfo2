import java.util.ArrayList;
import java.util.List;

public class BenjaminiHochberg {
    public static double[] pValueToArray(Counts count){
        List<Double> pValues = count.getPValue();
        double[] doubleArray = new double[pValues.size()];
        for (int i = 0; i < pValues.size(); i++) {
            doubleArray[i] = pValues.get(i);
        }
        return doubleArray;
    }

    //Methode aus Foliensatz 22 der VO Praktische Informatik2, adaptiert um originale Positionen zu speichern
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

    public static List<Double> BenjaminiHochberg(Counts count){
        double[] unsortedPValues = pValueToArray(count);
        List<Integer> indexMapping = insertionSortMapper(unsortedPValues);
        double[] sortedpValues = insertionSort(unsortedPValues);
        double[] adjPValues = new double[sortedpValues.length];
        for(int i = sortedpValues.length-1; i >= 0; i--){
            double adjP = Math.min(sortedpValues[i], sortedpValues[i] * ((double) sortedpValues.length /(i+1)));
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
