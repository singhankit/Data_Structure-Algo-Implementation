package ds_sorting;

public class MergeSort {

	static void mergeSort(int[] ar, int l, int r) {
		if(l<r){
			int m=(l+r)/2;
			mergeSort(ar, l, m);
			mergeSort(ar, m+1, r);
			merge(ar,l,m,r);
		}
		
	}
	//Merges two subarrays of ar[].
	static void merge(int[] ar,int l,int m,int r){
		int n1=m-l+1;
		int n2=r-m;
		
		int[] left=new int[n1];
		int[] right=new int[n2];
		
		for(int i=0;i<n1;i++){
			left[i]=ar[l+i];
		}
		
		for(int i=0;i<n2;i++){
			right[i]=ar[m+1+i];
		}
		
		int i=0, j=0;
		int k=l;
		while(i<n1 && j<n2){
			if(left[i]<=right[j]){
				ar[k]=left[i];
				i++;
			}
			else{
				ar[k]=right[j];
				j++;
			}k++;
		}
		
		while(i<n1){
			ar[k]=left[i];
			i++;
			k++;
		}
		
		while(j<n2){
			ar[k]=right[j];
			j++;
			k++;
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		int[] ar={1,1,23,8,20,5,0};
		System.out.println("Before Sorting :");
		for(int a:ar){
			System.out.print(a+" ");
		}
		System.out.println("\n"+"After Sorting :");
		mergeSort(ar,0,ar.length-1);
		for(int a:ar){
			System.out.print(a+" ");
		}
	}


}
