import org.knowm.xchart.*;

public class Graphics {

    public static void plot(Counts count, String filename){
        // size of arrays (number of datapoints) = number of all genes
        double[] xData = count.getAdjPVal();
        double[] yData = count.getLogFoldchange();
        double[] zData = new double[count.getAdjPValue().size()];

        //z Data: size of data point (in all cases 1)
        for (int i = 0; i < zData.length; i++){
            zData[i] = 1;
        }

        //plot
        BubbleChart chart = new BubbleChart(new BubbleChartBuilder());
        chart.addSeries(filename, xData, yData, zData);
        new SwingWrapper(chart).displayChart();
    }
}
