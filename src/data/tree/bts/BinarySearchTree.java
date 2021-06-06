package data.tree.bts;

public class BinarySearchTree {

	private TreeNode root;

	public void addNode(int key) {
		if (findNode(key) != null)
			return; // 이미 존재하면 그냥 리턴

		TreeNode newNode = new TreeNode(key);

		if (root == null) {
			root = newNode; // 트리가 비어있으면 root 에 삽입
		} else {
			TreeNode focusNode = root; // 탐색용 노드
			TreeNode parent; // 탐색용 노드의 부모 노드

			while (true) {
				parent = focusNode; // 이동

				if (key < parent.key) { // 삽입하려는 키가 현재 노드보다 작으면
					focusNode = parent.leftChild; // 왼쪽으로 이동

					if (focusNode == null) { // 왼쪽 노드가 비어있으면
						parent.leftChild = newNode; // 왼쪽 노드에 삽입
						return;
					}
				} else { // 삽입하려는 키가 현재 노드와 같거나 크다면
					focusNode = parent.rightChild; // 오른쪽으로 이동

					if (focusNode == null) { // 오른쪽 노드가 비어있으면
						parent.rightChild = newNode;// 오른쪽 노드에 삽입
						return;
					}
				}
			}
		}
	}

	public TreeNode findNode(int key) {
		// 트리가 비었을 때
		if (root == null)
			return null;

		TreeNode focusNode = root;

		while (focusNode.key != key) {
			if (key < focusNode.key) { // 현재노드보다 작으면
				focusNode = focusNode.leftChild; // 왼쪽으로
			} else { // 크면
				focusNode = focusNode.rightChild; // 오른쪽으로
			}

			// 찾으려는 노드가 없을 때
			if (focusNode == null)
				return null;
		}

		return focusNode;
	}

	// 왼->가운데->오른쪽
	public void inOrderTraverse(TreeNode focusNode) {
		if (focusNode != null) {
			inOrderTraverse(focusNode.leftChild);
			System.out.print(focusNode.key + " ");
			inOrderTraverse(focusNode.rightChild);
		}
	}

	// 가운데->왼->오른쪽
	public void preOrderTraverse(TreeNode focusNode) {
		if (focusNode != null) {
			System.out.print(focusNode.key + " ");
			preOrderTraverse(focusNode.leftChild);
			preOrderTraverse(focusNode.rightChild);
		}
	}

	// 왼->오른쪽->가운데
	public void postOrderTraverse(TreeNode focusNode) {
		if (focusNode != null) {
			postOrderTraverse(focusNode.leftChild);
			postOrderTraverse(focusNode.rightChild);
			System.out.print(focusNode.key + " ");
		}
	}
}
