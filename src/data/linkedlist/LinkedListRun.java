package data.linkedlist;

public class LinkedListRun {

	public static void main(String[] args) {
		
		LinkedList linkedList = new LinkedList();
		linkedList.addFirst(new Data(1, "영지"));
		linkedList.addFirst(new Data(2, "태현"));
		linkedList.addFirst(new Data(3, "여름"));
		linkedList.addFirst(new Data(4, "시원"));
		
		System.out.println(linkedList.node(2).toString());
		
		System.out.println(linkedList.toString());
		
		
	}
}

class Data{
	int id;
	String name;
	
	Data(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String toString() {
		return "{"+this.id+","+this.name+"}";
	}
}