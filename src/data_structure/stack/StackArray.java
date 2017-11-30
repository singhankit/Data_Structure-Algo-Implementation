package data_structure.stack;

public class StackArray {
	Object[] arrStack;
	int size;
	int top;

	public StackArray(int size) {
		this.size = size;
		arrStack=new Object[this.size];
		for (int i = 0; i < arrStack.length; i++) {

		}
		top = -1;
	}

}
