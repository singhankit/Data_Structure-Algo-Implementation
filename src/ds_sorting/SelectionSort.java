package ds_sorting;

public class SelectionSort {
	
	static void selectionSort(int[] arr){
		for (int i = 0; i < arr.length-1; i++) {
			int index=i;
			for (int j = i+1; j < arr.length; j++) {
				if(arr[j]<arr[index]){
					index=j;
				}
			}
			if(index!=i){
				int temp=arr[index];
				arr[index]=arr[i];
				arr[i]=temp;
			}
			
		}
	}

	public static void main(String[] args) {
		int[] ar={1,1,23,8,20,5};
		System.out.println("Before Sorting :");
		for(int a:ar){
			System.out.print(a+" ");
		}
		System.out.println("\n"+"After Sorting :");
		selectionSort(ar);
		for(int a:ar){
			System.out.print(a+" ");
		}
	}

}
