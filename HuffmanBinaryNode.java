
public class HuffmanBinaryNode {

	private HuffmanBinaryNode parent;
	private HuffmanBinaryNode leftChild;
	private HuffmanBinaryNode rightChild;
	private String key;
	private int frequency = -1;
	private boolean isLeaf = false;
	private int incomingEdge = -1;

	public HuffmanBinaryNode() {

	}

	public HuffmanBinaryNode(int frequency, String key) {
		this.key = key;
		this.frequency = frequency;
	}

	public HuffmanBinaryNode getParent() {
		return this.parent;
	}

	public void setParent(HuffmanBinaryNode parent) {
		this.parent = parent;
	}

	public HuffmanBinaryNode getLeft() {
		return this.leftChild;
	}

	public void setLeft(HuffmanBinaryNode left) {
		this.leftChild = left;
	}

	public HuffmanBinaryNode getRight() {
		return this.rightChild;
	}

	public void setRight(HuffmanBinaryNode right) {
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
