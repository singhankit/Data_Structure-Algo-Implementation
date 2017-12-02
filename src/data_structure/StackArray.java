package data_structure;

public class StackArray<T> {
	Object[] arrStack;
	int size;
	int top;

	public StackArray(int size) {
		this.size = size;
		arrStack=new Object[this.size];
		top = -1;
	}
	
	public void push(Object value){
		if(isFull()){
			System.out.println("Stack is Full !!");
			return;
		}
		arrStack[++top]=value;
		//top++;
	}
	
	private boolean isFull() {
		// TODO Auto-generated method stub
		return top==size-1;
	}
	

	public T pop(){
		if(isEmpty()){
			System.out.println("Stack is Empty");
			return null;
			
		}
		T item=(T) arrStack[top];
		top=top-1;	
		return item;
	}

	private boolean isEmpty() {
		// TODO Auto-generated method stub
		return top==-1;
	}
	
	
public static void main(String[] args) {
	StackArray<Integer> st=new StackArray<>(5);
	st.push(11);
	st.push(12);
	st.push(13);
	st.push(14);
	st.push(15);
	st.push(16);
	System.out.println(st.pop());
	
	while(!st.isEmpty()){
		System.out.println(st.pop());
	}
}
}
