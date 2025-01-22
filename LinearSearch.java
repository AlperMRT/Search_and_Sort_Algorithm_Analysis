public class LinearSearch {
    public static int linearSearch(int[]data, int element){
        int length = data.length;
        for (int i=0;i<length;i++){
            if (data[i]==element){
                return i;
            }
        }
        return -1;
    }
}
