public class BinarySearch {
    public static int binarySearch(int[]data, int element){
        int low=0;
        int high=data.length-1;
        while (high-low>1){
            int middle=low+(high-low)/2;
            if (data[middle]<element){
                low=middle+1;
            }else {
                high=middle;
            }
        }
        if (data[low]==element){
            return low;
        }else if (data[high]==element){
            return high;
        }
        return -1;
    }


}
