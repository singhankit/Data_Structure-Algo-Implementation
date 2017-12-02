package data_structure;

public class Entry {
int key;
Object value;
Entry next;
	public Entry(int key,Object value) {
		this.key=key;
		this.value=value;
		next=null;
				
		// TODO Auto-generated constructor stub
	}
	public Entry(){
		next=null;
	}
	public int getKey() {
		return key;
	}
	public Object getValue() {
		return value;
	}
	

}
