public class MergeSort {
    public static void merge(int [] data, int left, int middle, int right){
        int n1 = middle-left+1;
        int n2 = right-middle;
        int l[] = new int[n1];
        int r[] = new int[n2];
        for (int i=0;i<n1;++i){
            l[i]=data[left+i];
        }
        for (int j=0;j<n2;++j){
            r[j]=data[middle+1+j];
        }
        int i=0;
        int j=0;
        int k=left;
        while (i<n1&&j<n2){
            if (l[i]<=r[j]){
                data[k]=l[i];
                i++;
            }else{
                data[k]=r[j];
                j++;
            }
            k++;
        }
        while (i<n1){
            data[k]=l[i];
            i++;
            k++;
        }
        while (j<n2){
            data[k]=r[j];
            j++;
            k++;
        }
    }
    public static void sort(int[]data, int left, int right){
        if (left<right){
            int middle = left + (right-left) / 2;
            sort(data,left,middle);
            sort(data,middle+1,right);
            merge(data,left,middle,right);

        }
    }
}
