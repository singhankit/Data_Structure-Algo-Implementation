package ds_graph;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {

	int size;
	AdjList[] array;
	
	public BreadthFirstSearch(int size) {
		this.size = size;
		array=new AdjList[this.size];
		for(int i=0;i<size;i++){
			array[i]=new AdjList();
			array[i].head=null;
			
		}
	}
	
	public void add(int src, int dest){
		Node n=new Node(dest, null);
		n.next=array[src].head;
		array[src].head=n;
	}
	
	public void bfsExplore(int startVertex){
		Boolean[] visited=new Boolean[size];
		for(int i=0;i<size;i++)
			visited[i]=false;
		Queue<Integer> queue=new LinkedList<>();
		queue.add(startVertex);
		while(!queue.isEmpty()){
			int n=queue.poll();
			System.out.println("Visited :"+n);
			visited[n]=true;
			Node head=array[n].head;
			while(head!=null){
				if(visited[head.value]==false){
					queue.add(head.value);
					visited[head.value]=true;
				}
				else{
					head=head.next;
				}
			}
		}
				
	}
	
	public static void main(String[] args) {
		BreadthFirstSearch bfs=new BreadthFirstSearch(6);
		bfs.add(0,2);
		bfs.add(0,1);
		bfs.add(1, 4);
		bfs.add(1, 3);
		bfs.add(1, 0);
		bfs.add(3, 1);
		bfs.add(4, 1);
		bfs.add(2, 5);
		bfs.add(2, 0);
		bfs.add(5, 2);
		bfs.bfsExplore(0);
	}
}
