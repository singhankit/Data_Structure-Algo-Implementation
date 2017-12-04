package data_structure;

public class QueueArray<T> {
Object [] queueArray;
int rear;
int front;
int size;
	public QueueArray(int size) {
		this.size=size;
		queueArray=new Object[this.size];
		front=-1;
		rear=-1;
		
		// TODO Auto-generated constructor stub
	}
	
	public boolean isFull(){
		return rear==size-1;
	}
	
	public boolean isEmpty(){
		return front==-1 || front>rear;
	}
	
	public void queue(Object value){
		if(isFull()){
			System.out.println("Queue is Full !!");
			return; 
		}
		rear=rear+1;
		queueArray[rear]=value;
		if(front==-1){
			front=0;
		}
	}
	
	public T dequeue(){
		if(isEmpty()){
			System.out.println("Queue is Empty !!");
			return null;
		}
		//front=front+1;
		return (T) queueArray[front++];
	}
	
	public static void main(String[] args) {
		QueueArray<String> qArray=new QueueArray<>(5);
		System.out.println(qArray.isEmpty());
		qArray.queue("singh");
		qArray.queue("jack");
		qArray.queue("vince");
		qArray.queue("mick");
		qArray.queue("tony");
		//qArray.queue("ika");
		System.out.println(qArray.dequeue());
		System.out.println(qArray.dequeue());
	}

}
