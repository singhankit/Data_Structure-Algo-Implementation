package ds_sorting;

public class QuickSort {
	
	static void quickSort(int[] arr,int low, int high){
		if(low<high){
			int pi = partition(arr,low,high);
		
		quickSort(arr,low,pi-1);
		quickSort(arr,pi+1,high);
		}
	}
	

	 static int partition(int[] arr, int low, int high) {
		 int i=low-1;
		 int pivot=arr[high];
		 for(int j=low;j<high;j++){
			 if(arr[j]<=pivot){
				 i++;
				 int temp=arr[j];
				 arr[j]=arr[i];
				 arr[i]=temp;
			 }
		 }
		 int temp=arr[i+1];
		 arr[i+1]=arr[high];
		 arr[high]=temp;
		return i+1;
	}


	public static void main(String[] args) {
		int[] ar={1,1,23,8,20,5};
		System.out.println("Before Sorting :");
		for(int a:ar){
			System.out.print(a+" ");
		}
		System.out.println("\n"+"After Sorting :");
		quickSort(ar,0,ar.length-1);
		for(int a:ar){
			System.out.print(a+" ");
		}
	}

}
