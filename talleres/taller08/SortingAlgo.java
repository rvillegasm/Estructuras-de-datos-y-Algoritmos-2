import java.util.Random;

/**
 * SortingAlgo
 */
public class SortingAlgo {

  public static int[] mergesort(int[]a) {
    mergesort(a, 0, a.length-1);
    return a;
  }

  private static void mergesort(int[]a,int l, int r) {
    if(l < r) {
      int m = (l+r)/2;
      mergesort(a,l,m);
      mergesort(a,m+1,r);
      merge(a,l,m,r);
    }
  }

  private static void merge(int[]a,int l, int m, int r) {

    int temp1 = (m-l)+1;
    int temp2 = r-m;

    int[] arrL = new int[temp1];
    int[] arrR = new int[temp2];

    for(int i=0;i<temp1;i++){
      arrL[i]= a[l+i];
    }
    for(int j=0;j<temp2;j++){
      arrR[j]= a[m+j+1];
    }

    int i = 0;
    int j = 0;

    int k = l;

    while(i<temp1 && j<temp2){
      if(arrL[i] <= arrR[j]){
        a[k] = arrL[i];
        i++;
      }else{
        a[k] = arrR[j];
        j++;
      }
      k++;
    }

    while(i < temp1){
      a[k] = arrL[i];
      i++;
      k++;
    }

    while(j < temp2){
      a[k] = arrR[j];
      j++;
      k++;
    }

  }

  public static int[] quicksort(int[] arr) {
    quicksort(arr, 0, arr.length - 1);
    return arr;
  }

  private static void quicksort(int[] arr, int l, int r) {
    if(l < r) {

      int p = partition(arr, l, r);

      quicksort(arr, l, p-1);
      quicksort(arr, p+1, r);
    }
  }

  private static int partition(int[] arr, int l, int r) {
    
    int pivot = arr[r];
    int i = (l-1);

    for(int j = l; j <= r - 1; j++) {
      if(arr[j] <= pivot) {
        i++; 
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      } 
    }

    int temp = arr[i + 1];
    arr[i + 1] = arr[r];
    arr[r] = temp;

    return (i + 1);
  }


  public static void main(String[] args) {
    int[] a = {9,3,5,4};
    int[] b = quicksort(a);
    for(int v : b) {
      System.out.println(v);
    }
  }
}