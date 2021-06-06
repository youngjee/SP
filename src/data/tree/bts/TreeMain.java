package data.tree.bts;

public class TreeMain {
	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		tree.addNode(5);
		tree.addNode(4);
		tree.addNode(3);
		tree.addNode(8);
		tree.addNode(7);
		tree.addNode(10);
		tree.addNode(9);
		tree.addNode(12);
		tree.addNode(11);
		
		tree.inOrderTraverse(tree.findNode(8));
	}
}
