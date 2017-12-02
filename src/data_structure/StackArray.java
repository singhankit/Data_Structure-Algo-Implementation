package data_structure;

public class StackArray {
	Object[] arrStack;
	int size;
	int top;

	public StackArray(int size) {
		this.size = size;
		arrStack=new Object[this.size];
		top = -1;
	}
	
	

}
