package data.tree.file;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private String nodeId;
	private String parentId;
	private List<Node> childNodes = new ArrayList<Node>();
	
	private int totalNodeCnt = 0;
	
	public int getTotalNodeCnt() {
		return totalNodeCnt;
	}
	public void setTotalNodeCnt(int totalNodeCnt) {
		this.totalNodeCnt = totalNodeCnt;
	}
	public Node(String nodeId) {
		super();
		this.nodeId = nodeId;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public List<Node> getChildNodes() {
		return childNodes;
	}
	public void setChildNodes(List<Node> childNodes) {
		this.childNodes = childNodes;
	}
}
