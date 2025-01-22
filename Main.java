import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;


class Main {
    static int linearSearchIndexRandom=0;
    static int linearSearchIndexSorted=0;
    static int binarySearchIndexSorted=0;
    public static void main(String args[]) throws IOException {
        String csv="TrafficFlowDataset.csv";
        String splitter=",";
        String line;
        int[]data=null;
        //data=null;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(csv));
        try{
            bufferedReader.readLine();
            int size=250000;
            data=new int[size];
            int i=0;
            while ((line= bufferedReader.readLine())!=null&&i<size){
                String[]row=line.split(splitter);
                data[i]= Integer.parseInt(row[6]);
                i++;
            }
        }catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        int[] dataSizes={500,1000,2000,4000,8000,16000,32000,64000,128000,250000};
        double[][] yAxisForRandom= new double[3][10];
        double[][] yAxisForSorted= new double[3][10];
        double[][] yAxisForReversed= new double[3][10];
        double[][] yAxisForSearch= new double[3][10];
        int i=0;
        for (int size:dataSizes){
            System.out.println("******");
            System.out.println("Size: " + size);
            int[] sampleData=Arrays.copyOf(data,size);
            int[]sortedData = Arrays.copyOf(sampleData,size);
            Arrays.sort(sortedData);
            int[]reverseSortedData= Arrays.copyOf(sampleData,size);
            Arrays.sort(reverseSortedData);
            reverseArray(reverseSortedData,reverseSortedData.length);

            //SORTING
            double[] averageTimesRandomData=calculateAverageSortingTime(size,data,"Random");
            yAxisForRandom[0][i]=averageTimesRandomData[0];
            yAxisForRandom[1][i]=averageTimesRandomData[1];
            yAxisForRandom[2][i]=averageTimesRandomData[2];

            double[] averageTimesSortedData=calculateAverageSortingTime(size,sortedData,"Sorted");
            yAxisForSorted[0][i]=averageTimesSortedData[0];
            yAxisForSorted[1][i]=averageTimesSortedData[1];
            yAxisForSorted[2][i]=averageTimesSortedData[2];

            double[] averageTimeReversedData=calculateAverageSortingTime(size,reverseSortedData,"Reversed");
            yAxisForReversed[0][i]=averageTimeReversedData[0];
            yAxisForReversed[1][i]=averageTimeReversedData[1];
            yAxisForReversed[2][i]=averageTimeReversedData[2];

            //SEARCHING
            double averageLinearSearchTimeRandomData=calculateSearchTime(size,sampleData,"Random","Linear");
            double averageLinearSearchTimeSortedData=calculateSearchTime(size,sortedData,"Sorted","Linear");
            double averageBinarySearchTimeSortedData=calculateSearchTime(size,sortedData,"Sorted","Binary");

            yAxisForSearch[0][i]=averageLinearSearchTimeRandomData;
            yAxisForSearch[1][i]=averageLinearSearchTimeSortedData;
            yAxisForSearch[2][i]=averageBinarySearchTimeSortedData;

            i++;
        }

        showAndSaveChart("Random Input Data Timing Plots in ms",dataSizes,yAxisForRandom,true);
        showAndSaveChart("Sorted Input Data Timing Plots in ms",dataSizes,yAxisForSorted,true);
        showAndSaveChart("Reverse Sorted Input Data Timing Plots in ms",dataSizes,yAxisForReversed,true);
        showAndSaveChart("Searching Algorithms Plots in ns",dataSizes,yAxisForSearch,false);

    }

    public static void showAndSaveChart(String title, int[] xAxis, double[][] yAxis, boolean isSorting) throws IOException {
        // Create Chart
        /*XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);*/

        // Add a plot for a sorting algorithm

        if (isSorting) {
            XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                    .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

            // Convert x axis to double[]
            double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

            // Customize Chart
            chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
            chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
            chart.addSeries("Insertion Sort", doubleX, yAxis[0]);
            chart.addSeries("Merge Sort", doubleX, yAxis[1]);
            chart.addSeries("Counting Sort", doubleX, yAxis[2]);
            // Save the chart as PNG
            BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

            // Show the chart
            new SwingWrapper(chart).displayChart();
        }else{
            XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                    .yAxisTitle("Time in Nanoseconds").xAxisTitle("Input Size").build();

            // Convert x axis to double[]
            double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

            // Customize Chart
            chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
            chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
            chart.addSeries("Linear Search Random", doubleX, yAxis[0]);
            chart.addSeries("Linear Search Sorted", doubleX, yAxis[1]);
            chart.addSeries("Binary Search Sorted", doubleX, yAxis[2]);
            // Save the chart as PNG
            BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

            // Show the chart
            new SwingWrapper(chart).displayChart();
        }

        // Save the chart as PNG
        //BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        // Show the chart
        //new SwingWrapper(chart).displayChart();
    }
    public static void reverseArray(int[]array,int n) {
        int i, t;
        for (i = 0; i < n / 2; i++) {
            t = array[i];
            array[i] = array[n - i - 1];
            array[n - i - 1] = t;
        }
    }
    protected static double[] calculateAverageSortingTime(int size, int[]data, String dataType){
        String message;
        double[] avgTimes = new double[3];
        if (dataType.equals("Sorted")){
            message=" With Sorted Data: ";
        }else if (dataType.equals("Reversed")){
            message=" With Reverse Sorted Data: ";
        }else {
            message=" With Random Data: ";
        }
        //long insertionTotalTime = 0;
        //long mergeTotalTime = 0;
        //long countingTotalTime = 0;
        for (int i = 0; i < 10; i++) {
            double insertionTotalTime = 0;
            double mergeTotalTime = 0;
            double countingTotalTime = 0;

            int[] insertionData = Arrays.copyOf(data, size);
            double startingTimeInsertion = System.nanoTime();
            InsertionSort.insertionSort(insertionData);
            double endingTimeInsertion = System.nanoTime();
            //insertionTotalTime += (endingTimeInsertion - startingTimeInsertion) / 1000000;
            insertionTotalTime += Math.ceil((endingTimeInsertion - startingTimeInsertion) / 1000000);

            int[] mergeData = Arrays.copyOf(data, size);
            double startingTimeMerge = System.nanoTime();
            MergeSort.sort(mergeData, 0, size - 1);
            double endingTimeMerge = System.nanoTime();
            //mergeTotalTime += (endingTimeMerge - startingTimeMerge) / 1000000;
            mergeTotalTime += Math.ceil((endingTimeMerge - startingTimeMerge) / 1000000);

            int[] countingData = Arrays.copyOf(data, size);
            double startingTimeCounting = System.nanoTime();
            CountingSort.countingSort(countingData);
            //CountingSort.countSort(countingData);
            double endingTimeCounting = System.nanoTime();
            //countingTotalTime += (endingTimeCounting - startingTimeCounting) / 1000000;
            countingTotalTime += Math.ceil((endingTimeCounting - startingTimeCounting) / 1000000);

            avgTimes[0] += insertionTotalTime;
            avgTimes[1] += mergeTotalTime;
            avgTimes[2] += countingTotalTime;
        }

        for (int i = 0; i < 3; i++) {
            avgTimes[i] /= 10;
        }

        System.out.println("Average Insertion Sort Time"+ message + avgTimes[0] + " ms");
        System.out.println("Average Merge Sort Time" + message +avgTimes[1] + " ms");
        System.out.println("Average Counting Sort Time" + message + avgTimes[2] + " ms");
        System.out.println("---");
        return avgTimes;
    }

    protected static double calculateSearchTime(int size, int[] data, String dataType,String searchType){
        double totalTime=0;
        //double[] avgTimes = new double[10];
        String message;
        int index=0;
        int whichOne=0;
        if (searchType.equals("Binary")){
            index=binarySearchIndexSorted;
            message="Average Binary Search Time with Sorted Data: ";
            whichOne=1;
        }else{
            if (dataType.equals("Sorted")){
                index=linearSearchIndexSorted;
                message= "Average Linear Search Time with Sorted Data: ";
                whichOne=2;
            }else{
                index=linearSearchIndexRandom;
                message="Average Linear Search Time with Random Data: ";
                whichOne=3;
            }
        }
        for (int i=0;i<1000;i++) {
            //double startingTime = System.nanoTime();
            int j = new Random().nextInt(size);
            int searchedValue = data[j];
            double startingTime = System.nanoTime();
            if (whichOne==1){
                int result = BinarySearch.binarySearch(data, searchedValue);
            } else if (whichOne==2) {
                int result = LinearSearch.linearSearch(data, searchedValue);
            }else {
                int result = LinearSearch.linearSearch(data, searchedValue);
            }
            double endingTime = System.nanoTime();
            double difference = endingTime - startingTime;
            totalTime += difference;
        }
        double averageTime=totalTime/1000;
        //avgTimes[index]=averageTime;
        System.out.println(message+  averageTime + " ns");
        if (whichOne==1){
            binarySearchIndexSorted++;
        } else if (whichOne==2) {
            linearSearchIndexSorted++;
        }else {
            linearSearchIndexRandom++;
        }

        return averageTime;
    }

}

