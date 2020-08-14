package data.tree.bts;

public class TreeNode {
	int key;
	TreeNode leftChild = null;
	TreeNode rightChild = null;

	public TreeNode(int key) {
		this.key = key;
		leftChild = null;
		rightChild = null;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getKey() {
		return key;
	}
	
}
