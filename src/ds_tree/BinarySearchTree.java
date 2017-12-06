package ds_tree;

public class BinarySearchTree {
	Node root;

	public BinarySearchTree() {
		root=null;
	}
	
	public void add(int item){
		root=insert(root,item);
	}
	public Node insert(Node root,int item){
		if(root==null){
			root=new Node(item);
			return root;
		}
		if(item>root.value){
			root.right=insert(root.right, item);
		}
		if(item<root.value){
		root.left=insert(root.left, item);
		}
		return root;
	}

	public void search(int value, Node start){
		if(start==null){
			System.out.println("\nNode is not found !!");
			return;
		}
		if(start.value==value){
			System.out.println("\nNode is Found");
			return;
			
		}
		if(value>start.value){
			search(value, start.right);
		}
		else if(value<start.value){
			search(value, start.left);
		}
	}
	
	public Node delete(Node root, int value){
		if(root==null){
			return root;
		}
		
		if(value<root.value){
			root.left=delete(root.left, value);
		}
		else if(value>root.value){
			root.right=delete(root.right, value);
		}
		else{
			if(root.left==null){
				return root.right;
			}
			else if(root.right==null) {
				return root.left;
			}
			
			root.value=minvalue(root.right);
			
		   root=delete(root, root.value);
			
			
		}
		
		return root;
		
	}
	
	
	
	private int minvalue(Node root) {
		int minV=root.value;
		while(root.left!=null){
			minV=root.left.value;
			root=root.left;
		}
		return minV;
	}
	
	public void inorderDisplay(Node root){
		if(root!=null){
			inorderDisplay(root.left);
			System.out.print(root.value+" ");
			inorderDisplay(root.right);
		}
	}

	public static void main(String[] args) {
		BinarySearchTree bt=new BinarySearchTree();
		bt.add(10);
		bt.add(20);
		bt.add(40);
		bt.add(60);
		bt.add(1);
		bt.inorderDisplay(bt.root);
		bt.search(1, bt.root);	
		bt.delete(bt.root, 40);
		bt.inorderDisplay(bt.root);
	}
}
