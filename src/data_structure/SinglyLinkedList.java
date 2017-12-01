package data_structure;

class Node {
	public Object data;
	public Node next;

	public Node(Object data, Node node) {
		this.data = data;
		this.next = node;

	}

}

public class SinglyLinkedList<T> {
	public Node head;

	public SinglyLinkedList() {
		this.head = null;
	}
	//add a node at starting
	public void add(Object data) {
		Node newNode = new Node(data, null);
		if (head == null) {
			head = newNode;
		} else {
			newNode.next = head;
			head = newNode;
		}

	}
	//add a node after a specific node
	public void addAfter(Object element, Object after) {
		Node newNode = new Node(element, null);
		Node n = head;
		while (n.next != null && n.data != after) {
			n = n.next;
		}

		newNode.next = n.next;
		n.next = newNode;
	}
	//delete a node after given input
	public void deleteAfter(Object after) {
		Node n = head;
		while (n.next != null && n.data != after) {
			n = n.next;
		}

		n.next = n.next.next;
	}
	//delete a node from head
	public void delete() {
		Node n = head;
		head = n.next;
	}
	// print the linked list 
	public void display() {
		Node n = head;
		while (n != null) {
			System.out.println(n.data);
			n = n.next;
		}
	}

	public static void main(String[] args) {
		SinglyLinkedList<String> singlyLinkedList = new SinglyLinkedList<String>();
		singlyLinkedList.add("Singh");
//		singlyLinkedList.add("ika");
//		singlyLinkedList.add("GV");
//		singlyLinkedList.add("batra");
//		System.out.println("Before delete");
		//singlyLinkedList.display();
		//String str = "nya";
		//singlyLinkedList.addAfter(str, "ika");
		//System.out.println("after delete");
		singlyLinkedList.display();
		System.out.println(singlyLinkedList.head);
		System.out.println(singlyLinkedList.head.next);
		// singlyLinkedList.delete();
		// System.out.println("after delete");
		// singlyLinkedList.display();
		//singlyLinkedList.deleteAfter("GV");
		//System.out.println("After after delete");
		//singlyLinkedList.display();
	}

}