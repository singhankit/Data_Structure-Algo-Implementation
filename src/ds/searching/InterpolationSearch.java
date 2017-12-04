package ds.searching;

public class InterpolationSearch {

	public static void main(String[] args) {
		DataSet item = new DataSet(1000000);
		int search = 120023;
		Boolean IsFound = false;
		int low=0;
		int high=item.getSize()-1;
		int mid=0;
		
		while(!IsFound){
			mid=low+((high-low)/(item.data[high]-
					item.data[low]))*(search-item.data[low]);
			if(low>high){
				System.out.println("Number is not found.");
				break;
			}
			item.NumberTry++;
			if(item.data[mid]==search){
				System.out.println("Number is found after "+item.NumberTry+" try.");
				break;
			}
			else if(item.data[mid]<search){
				low=mid+1;
			}
			else{
				high=mid-1;
			}
		}

		
	}

}
