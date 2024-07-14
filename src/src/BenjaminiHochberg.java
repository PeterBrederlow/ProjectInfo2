import java.util.ArrayList;
import java.util.List;

public class BenjaminiHochberg {
    public static double[] sortpValues(Counts count){
        List<Double> pValues = count.getpValue();
        double[] doubleArray = new double[pValues.size()];
        for (int i = 0; i < pValues.size(); i++) {
            doubleArray[i] = pValues.get(i);
        }
        insertionSort(doubleArray);
        return doubleArray;
    }

    //Methode aus Foliensatz 22 der VO Praktische Informatik2, adaptiert um originale Positionen zu speichern
    public static void insertionSort(double[] array) {
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
    }

    public static List<Double> BenjaminiHochberg(Counts count){
        double[] sortedpValues = sortpValues(count);
        List<Double> adjPValue = new ArrayList<>();
        for(int i = sortedpValues.length-1; i >= 0; i--){
            double adjP = Math.min(sortedpValues[i], sortedpValues[i] * ((double) sortedpValues.length /(i+1)));
            adjPValue.add(adjP);
        }
        return adjPValue;
    }
}
