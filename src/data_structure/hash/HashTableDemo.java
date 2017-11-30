package data_structure.hash;

public class HashTableDemo {

public static void main(String[] args) {
	HashTableArray<String> hm=new HashTableArray<>(6);
	hm.put(10, "Singh");
	hm.put(12, "mick");
	hm.put(28, "jack");
	hm.put(14, "ira");
	hm.put(4, "june");
	
	System.out.println(hm.get(28));
}
}
