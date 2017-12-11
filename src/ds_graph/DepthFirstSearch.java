package ds_graph;

import java.util.PriorityQueue;
import java.util.Stack;

public class DepthFirstSearch {
	int size;
	AdjList[] array;
	
	public DepthFirstSearch(int size) {
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
	
	public void dfsExplore(int startVertex){
		Boolean[] visited=new Boolean[size];
		for(int i=0;i<size;i++)
			visited[i]=false;
		Stack<Integer> st=new Stack<>();
		st.push(startVertex);
		while(!st.isEmpty()){
			int n=st.pop();
			st.push(n);
			visited[n]=true;
			Node head=array[n].head;
			Boolean isDone=true;
			while(head!=null){
				if(visited[head.value]==false){
					st.push(head.value);
					visited[head.value]=true;
					isDone=false;
					break;
				}
				else{
					head=head.next;
				}
				
			}
			if(isDone==true){
				int out=st.pop();
				System.out.println("Visited :"+out);
			}
		}
				
	}
	
	public static void main(String[] args) {
		DepthFirstSearch dfs=new DepthFirstSearch(6);
		dfs.add(0,2);
		dfs.add(0,1);
		dfs.add(1, 4);
		dfs.add(1, 3);
		dfs.add(1, 0);
		dfs.add(3, 1);
		dfs.add(4, 1);
		dfs.add(2, 5);
		dfs.add(2, 0);
		dfs.add(5, 2);
		dfs.dfsExplore(0);
	}

}
