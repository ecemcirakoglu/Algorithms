
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.SwingWrapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {

    public static int[] deneme  = new int[251282];

    public static int convertInteger(Integer deneme){
        return deneme.intValue();
    }

    public static void main(String args[]) throws IOException {

        //Reading CSV file
        String csvFile = args[0];
        //String csvFile = "/Users/ecemcirakoglu/Desktop/b21821665/src/TrafficFlowAll.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String[] TrafficFlowDataset = new String[0];
        try {
            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            int i = 0;
            while ((line = br.readLine()) != null) {
                TrafficFlowDataset = line.split(cvsSplitBy);
                //Arrays seventh column is "Flow Duration"
                deneme[i] = convertInteger(Integer.parseInt(TrafficFlowDataset[7]));
                i++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // X axis data
        int[] inputAxis = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 251282};

        // Create sample data for linear runtime
        double[][] yAxis = new double[4][10];

        /////////FOR RANDOM DATA///////
        //System.out.println("-------RANDOM DATA-------");
        for(int axis=0; axis < inputAxis.length; axis++){
            int[] random_cutted_array = new int[inputAxis[axis]];
            for(int i=0; i<inputAxis[axis]; i++){
                random_cutted_array[i]=deneme[i];
            }
            int count=10;
            long total_is1=0;
            long total_ms1=0;
            long total_cs1=0;
            long total_ps1=0;

            while(count>0) {
                //Insertion Sort
                InsertionSort is1 = new InsertionSort();
                long start_time_is1 = System.currentTimeMillis();
                is1.insertionSort(random_cutted_array);
                long end_time_is1 = System.currentTimeMillis();
                //System.out.print("INSERTION SORT FOR SIZE ->" + random_cutted_array.length + " ");
                long is1_time = end_time_is1 - start_time_is1;
                //System.out.print(end_time_is1 - start_time_is1 + " ");
                //System.out.println(" ");

                //Merge Sort
                MergeSort ms1 = new MergeSort();
                long start_time_ms1 = System.currentTimeMillis();
                ms1.mergeSort(random_cutted_array, 0, random_cutted_array.length - 1);
                long end_time_ms1 = System.currentTimeMillis();
                //System.out.print("MERGE SORT FOR SIZE ->" + random_cutted_array.length + " ");
                long ms1_time = (end_time_ms1 - start_time_ms1);
                //System.out.print((end_time_ms1 - start_time_ms1) + " ");
                //System.out.println(" ");

                for(int i=0; i<inputAxis[axis]; i++){
                    random_cutted_array[i]=deneme[i];
                }

                //Counting Sort
                CountingSort cs1 = new CountingSort();
                long start_time_cs1 = System.currentTimeMillis();
                cs1.countingSort(random_cutted_array, random_cutted_array.length);
                long end_time_cs1 = System.currentTimeMillis();
                //System.out.print("COUNTING SORT FOR SIZE ->" + random_cutted_array.length + " ");
                long cs1_time = (end_time_cs1 - start_time_cs1);
                //System.out.print((end_time_cs1 - start_time_cs1) + " ");
                //System.out.println(" ");

                //PigeonHoles Sort
                PigeonholesSort ps1 = new PigeonholesSort();
                long start_time_ps1 = System.currentTimeMillis();
                ps1.pigeonholesSort(random_cutted_array);
                long end_time_ps1 = System.currentTimeMillis();
                //System.out.print("PIGEONHOLE SORT FOR SIZE ->" + random_cutted_array.length + " ");
                long ps1_time = (end_time_ps1 - start_time_ps1);
                //System.out.print((end_time_ps1 - start_time_ps1) + " ");
                //System.out.println(" ");

                //System.out.println();
                total_is1 += is1_time;
                total_ms1 += ms1_time;
                total_cs1 += cs1_time;
                total_ps1 += ps1_time;
                count--;

            }
            //System.out.println("For size" + axis);
            yAxis[0][axis] = (double)total_is1/10;
            //System.out.println("IS " + (double)total_is1/10);
            yAxis[1][axis] = (double)total_cs1/10;
            //System.out.println( "CS " +(double)total_cs1/10);
            yAxis[2][axis] = (double)total_ms1/10;
            //System.out.println("MS " +(double)total_ms1/10);
            yAxis[3][axis] = (double)total_ps1/10;
            //System.out.println("PS " +(double)total_ps1/10);

        }
        // Save the char as .png and show it
        showAndSaveChart("RANDOM DATA", inputAxis, yAxis);

        ///////FOR SORTED DATA///////
        //System.out.println("-------SORTED DATA-------");
        MergeSort sorted_array = new MergeSort();
        sorted_array.mergeSort(deneme,0,deneme.length-1);
        for(int axis=0; axis < inputAxis.length; axis++){
            int[] sorted_cutted_array = new int[inputAxis[axis]];
            for(int i=0; i<inputAxis[axis]; i++){
                sorted_cutted_array[i]=deneme[i];
            }

            long total_is2=0;
            long total_ms2=0;
            long total_cs2=0;
            long total_ps2=0;
            int count=10;
            while(count>0) {

                //Insertion Sort
                InsertionSort is2 = new InsertionSort();
                long start_time_is2 = System.currentTimeMillis();
                is2.insertionSort(sorted_cutted_array);
                long end_time_is2 = System.currentTimeMillis();
                //System.out.print("INSERTION SORT FOR SIZE ->" + sorted_cutted_array.length + "-> ");
                long is2_time = end_time_is2 - start_time_is2;
                //System.out.print((end_time_is2 - start_time_is2) + " ");
                //System.out.println(" ");

                //Merge Sort
                MergeSort ms2 = new MergeSort();
                long start_time_ms2 = System.currentTimeMillis();
                ms2.mergeSort(sorted_cutted_array, 0, sorted_cutted_array.length - 1);
                long end_time_ms2 = System.currentTimeMillis();
                //System.out.print("MERGE SORT FOR SIZE ->" + sorted_cutted_array.length + "-> ");
                long ms2_time = end_time_ms2 - start_time_ms2;
                //System.out.print((end_time_ms2 - start_time_ms2) + " ");
                //System.out.println(" ");
                //System.out.println("ms after" + sorted_cutted_array[0]);

                //Counting Sort
                CountingSort cs2 = new CountingSort();
                long start_time_cs2 = System.currentTimeMillis();
                cs2.countingSort(sorted_cutted_array, sorted_cutted_array.length);
                long end_time_cs2 = System.currentTimeMillis();
                //System.out.print("COUNTING SORT FOR SIZE ->" + sorted_cutted_array.length + "-> ");
                long cs2_time = end_time_cs2 - start_time_cs2;
                //System.out.print((end_time_cs2 - start_time_cs2) + " ");
                //System.out.println(" ");

                //PigeonHoles Sort
                PigeonholesSort ps2 = new PigeonholesSort();
                long start_time_ps2 = System.currentTimeMillis();
                ps2.pigeonholesSort(sorted_cutted_array);
                long end_time_ps2 = System.currentTimeMillis();
                //System.out.print("PIGEONHOLE SORT FOR SIZE ->" + sorted_cutted_array.length + "-> ");
                long ps2_time = end_time_ps2 - start_time_ps2;
                //System.out.print((end_time_ps2 - start_time_ps2) + " ");
                //System.out.println(" ");
                //System.out.println("ps after" + sorted_cutted_array[0]);

                //System.out.println();
                total_is2 += is2_time;
                total_ms2 += ms2_time;
                total_cs2 += cs2_time;
                total_ps2 += ps2_time;
                count--;
            }

            yAxis[0][axis] = (double)total_is2/10;
            yAxis[1][axis] = (double)total_cs2/10;
            yAxis[2][axis] = (double)total_ms2/10;
            yAxis[3][axis] = (double)total_ps2/10;

        }
        showAndSaveChart("SORTED DATA", inputAxis, yAxis);
        
        ///////FOR REVERSE SORTED DATA/////
        //System.out.println("-------REVERSE SORTED DATA-------");
        InsertionSort reverse_array = new InsertionSort();
        reverse_array.insertionSort_reverse(deneme);
        for (int axis = 0; axis < inputAxis.length; axis++) {
            int[] reverse_cutted_array = new int[inputAxis[axis]];
            for (int i = 0; i < inputAxis[axis]; i++) {
                reverse_cutted_array[i] = deneme[i];
            }
            long total_is3 = 0;
            long total_ms3 = 0;
            long total_cs3 = 0;
            long total_ps3 = 0;
            int count = 10;
            while(count>0) {

                //Insertion Sort
                InsertionSort is3 = new InsertionSort();
                long start_time_is3 = System.currentTimeMillis();
                is3.insertionSort(reverse_cutted_array);
                long end_time_is3 = System.currentTimeMillis();
                //System.out.print("INSERTION SORT FOR SIZE ->" + reverse_cutted_array.length + "-> ");
                long is3_time = end_time_is3 - start_time_is3;
                //System.out.print((end_time_is3 - start_time_is3) + " ");
                //System.out.println(" ");

                //Merge Sort
                MergeSort ms3 = new MergeSort();
                long start_time_ms3 = System.currentTimeMillis();
                ms3.mergeSort(reverse_cutted_array, 0, reverse_cutted_array.length - 1);
                long end_time_ms3 = System.currentTimeMillis();
                //System.out.print("MERGE SORT FOR SIZE ->" + reverse_cutted_array.length + "-> ");
                long ms3_time = end_time_ms3 - start_time_ms3;
                //System.out.print((end_time_ms3 - start_time_ms3) + "-> ");
                //System.out.println(" ");

                for(int i=0; i<inputAxis[axis]; i++){
                    reverse_cutted_array[i]=deneme[i];
                }

                //Counting Sort
                CountingSort cs3 = new CountingSort();
                long start_time_cs3 = System.currentTimeMillis();
                cs3.countingSort(reverse_cutted_array, reverse_cutted_array.length);
                long end_time_cs3 = System.currentTimeMillis();
                //System.out.print("COUNTING SORT FOR SIZE ->" + reverse_cutted_array.length + "-> ");
                long cs3_time = end_time_cs3 - start_time_cs3;
                //System.out.print((end_time_cs3 - start_time_cs3) + "-> ");
                //System.out.println(" ");

                //PigeonHoles Sort
                PigeonholesSort ps3 = new PigeonholesSort();
                long start_time_ps3 = System.currentTimeMillis();
                ps3.pigeonholesSort(reverse_cutted_array);
                long end_time_ps3 = System.currentTimeMillis();
                //System.out.print("PIGEONHOLE SORT FOR SIZE ->" + reverse_cutted_array.length + "-> ");
                long ps3_time = end_time_ps3 - start_time_ps3;
                //System.out.print((end_time_ps3 - start_time_ps3) + " ");
                //System.out.println(" ");

                //System.out.println();
                total_is3 += is3_time;
                total_ms3 += ms3_time;
                total_cs3 += cs3_time;
                total_ps3 += ps3_time;
                count--;
            }
            //System.out.println("For size" + axis);
            yAxis[0][axis] = (double)total_is3/10;;
            //System.out.println("IS " + (double)total_is3/10);
            yAxis[1][axis] = (double)total_cs3/10;
            //System.out.println( "CS " +(double)total_cs3/10);
            yAxis[2][axis] = (double)total_ms3/10;
            //System.out.println("MS " +(double)total_ms3/10);
            yAxis[3][axis] = (double)total_ps3/10;
            //System.out.println("PS " +(double)total_ps3/10);

        }
        showAndSaveChart("REVERSE SORTED DATA", inputAxis, yAxis);

    }
    public static void showAndSaveChart(String title, int[] xAxis, double[][] yAxis) throws IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add a plot for a sorting algorithm
        chart.addSeries("Insertion", doubleX, yAxis[0]);
        chart.addSeries("Counting", doubleX, yAxis[1]);
        chart.addSeries("Merge", doubleX, yAxis[2]);
        chart.addSeries("Pigeonhole", doubleX, yAxis[3]);


        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        // Show the chart
        new SwingWrapper(chart).displayChart();
    }
}
class InsertionSort{
    public void insertionSort(int[] arr){
        int i;
        int key;
        for(int j=1; j<arr.length; j++){
            key = arr[j];
            i= j-1;
            while(i>0 && arr[i]>key){
                arr[i+1] = arr[i];
                i=i-1;
            }
            arr[i+1] = key;
        }
    }
    public void insertionSort_reverse(int[] arr){
        int size = arr.length;
        for(int step=1; step<size; step++){
            int key= arr[step];
            int j = step-1;
            while(j>=0 && key>arr[j]){
                arr[j+1] = arr[j];
                --j;
            }
            arr[j+1] = key;
        }

    }
}
class MergeSort{
    public void Merge(int[] arr,int startIndex,int endIndex){

        ArrayList<Integer> mergedSortedArray = new ArrayList<Integer>();

        int midIndex = (endIndex + startIndex) / 2;
        int leftIndex = startIndex;
        int rightIndex = midIndex + 1;

        while (leftIndex <= midIndex && rightIndex <= endIndex) {
            if (arr[leftIndex] <= arr[rightIndex]) {
                mergedSortedArray.add(arr[leftIndex]);
                leftIndex++;
            } else {
                mergedSortedArray.add(arr[rightIndex]);
                rightIndex++;
            }
        }

        while (leftIndex <= midIndex) {
            mergedSortedArray.add(arr[leftIndex]);
            leftIndex++;
        }

        while (rightIndex <= endIndex) {
            mergedSortedArray.add(arr[rightIndex]);
            rightIndex++;
        }

        int i = 0;
        int j = startIndex;
        while (i < mergedSortedArray.size()) {
            arr[j] = mergedSortedArray.get(i++);
            j++;
        }
    }
    public void mergeSort(int[] arr,int lo,int hi){
        if (lo < hi && (hi - lo) >= 1) {
            int mid = (hi + lo) / 2;
            mergeSort(arr,lo, mid);
            mergeSort(arr,mid + 1, hi);
            Merge(arr,lo, hi);
        }
    }
}
class PigeonholesSort{
    public void pigeonholesSort(int[] arr){
        int n=arr.length;
        int min = arr[0];
        int max=arr[0];
        int range,i,j,index;

        for (int a = 0; a < n; a++) {
            if (arr[a] > max)
                max=arr[a];
            if(arr[a] < min)
                min=arr[a];
        }
        range = max - min + 1;
        int[] phole = new int[range];
        Arrays.fill(phole,0);

        for(i=0;i<n;i++){
            phole[arr[i]-min]++;
        }
        index=0;
        /*
        for(j=0;j<range;j++){
            while(phole[j]-->0)
                arr[index++] = j + min;
        }*/
    }
}
class CountingSort{
    public void countingSort(int[] arr,int size){
        int[] output = new int[size+1];

        //Find the largest element of the array
        int max= arr[0];
        for(int i = 1; i<size; i++){
            if(arr[i]>max)
                max=arr[i];
        }
        int[] count = new int[max+1];

        //Initialize count array with zeros.
        for(int i=0; i<max; ++i){
            count[i] =0;
        }
        //Store the count of each element
        for(int i =0; i<size; ++i){
            count[arr[i]]++;
        }
        //Store the cumulative count of each array
        for(int i=1; i<=max; ++i){
            count[i] += count[i-1];
        }
        for(int i=size-1;i>=0;i--){
            output[count[arr[i]]-1] =arr[i];
            count[arr[i]]--;
        }
        //Copy the sorted elements into original array
        /*
        for(int i=0; i<size; i++){
            arr[i] = output[i];
        }*/
    }
}





