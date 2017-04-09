
public class HuffmanFourWayNode {

	private HuffmanFourWayNode parent;
	private HuffmanFourWayNode leftChild;
	private HuffmanFourWayNode rightChild;
	private String key;
	private int frequency = -1;
	private boolean isLeaf = false;
	private int incomingEdge = -1;

	public HuffmanFourWayNode() {

	}

	public HuffmanFourWayNode(int frequency, String key) {
		this.key = key;
		this.frequency = frequency;
	}

	public HuffmanFourWayNode getParent() {
		return this.parent;
	}

	public void setParent(HuffmanFourWayNode parent) {
		this.parent = parent;
	}

	public HuffmanFourWayNode getLeft() {
		return this.leftChild;
	}

	public void setLeft(HuffmanFourWayNode left) {
		this.leftChild = left;
	}

	public HuffmanFourWayNode getRight() {
		return this.rightChild;
	}

	public void setRight(HuffmanFourWayNode right) {
		this.rightChild = right;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getFrequency() {
		return this.frequency;
	}

	public void setIncomingEdge(int incomingEdge) {
		this.incomingEdge = incomingEdge;
	}

	public int getIncomingEdge() {
		return this.incomingEdge;
	}

	public void setToLeaf() {
		this.isLeaf = true;
	}

	public boolean isLeaf() {
		return this.isLeaf;
	}
}
