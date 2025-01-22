public class CountingSort {
    /*public static void countingSort(int[]data){
        int length=data.length;
        int k=data[0];
        for (int i=0;i<length;i++){
            if (data[i]>k){
                k=data[i];
            }
        }
        int[]count = new int[k+1];
        int[]output = new int[length];
        for (int i=0;i<length;i++){
            count[data[i]]++;
        }
        for (int i=1;i<=k;i++){
            count[i]+=count[i-1];
        }
        for (int i=length-1; i >= 0; i--) {
            output[count[data[i]]-1] = data[i];
            count[data[i]]--;
        }
        for (int i = 0; i < length; i++) {
            data[i] = output[i];
        }

    }*/
    public static int[] countingSort(int[] data) {
        int length = data.length;
        int k = 0;

        for (int i=0;i<length;i++) {
            k = Math.max(k, data[i]);
        }

        int[] countArray = new int[k + 1];

        for (int i=0;i<length;i++) {
            countArray[data[i]]++;
        }

        for (int i=1;i<=k;i++) {
            countArray[i] += countArray[i - 1];
        }

        int[] outputArray = new int[length];

        for (int i=length-1;i>=0;i--) {
            outputArray[countArray[data[i]] - 1] = data[i];
            countArray[data[i]]--;
        }

        return outputArray;
    }

}
