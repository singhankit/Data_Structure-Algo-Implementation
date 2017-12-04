package ds.sorting;

public class BubbleSort {
	static void bubbleSort(int[] arr){
		for (int i = 0; i < arr.length; i++) {
			for (int j = 1; j < arr.length-i; j++) {
				if(arr[j]<arr[j-1]){
					int temp=arr[j];
					arr[j]=arr[j-1];
					arr[j-1]=temp;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] ar={1,1,13,8,0,5};
		System.out.println("Before Sorting :");
		for(int a:ar){
			System.out.print(a+" ");
		}
		System.out.println("\n"+"After Sorting :");
		bubbleSort(ar);
		for(int a:ar){
			System.out.print(a+" ");
		}
	}

}
