
public class HuffmanPairingNode {

	private HuffmanPairingNode nextSibling = null;
	private HuffmanPairingNode leftChild = null;
	private HuffmanPairingNode rightChild = null;
	private HuffmanPairingNode child = null;
	private HuffmanPairingNode left = null;
	private HuffmanPairingNode right = null;
	private int frequency;
	private String key;
	private int incomingEdge;
	private boolean isLeaf = false;

	public HuffmanPairingNode() {

	}

	public HuffmanPairingNode(int frequency, String key) {
		this.key = key;
		this.frequency = frequency;
	}

	public HuffmanPairingNode getNextSibling() {
		return this.nextSibling;
	}

	public void setNextSibling(HuffmanPairingNode nextSibling) {
		this.nextSibling = nextSibling;
	}

	public HuffmanPairingNode getLeftChild() {
		return this.leftChild;
	}

	public void setLeftChild(HuffmanPairingNode leftChild) {
		this.leftChild = leftChild;
	}

	public HuffmanPairingNode getRightChild() {
		return this.rightChild;
	}

	public void setRightChild(HuffmanPairingNode rightChild) {
		this.rightChild = rightChild;
	}

	public HuffmanPairingNode getChild() {
		return this.child;
	}

	public void setChild(HuffmanPairingNode child) {
		this.child = child;
	}

	public HuffmanPairingNode getLeft() {
		return this.left;
	}

	public void setLeft(HuffmanPairingNode left) {
		this.left = left;
	}

	public HuffmanPairingNode getRight() {
		return this.right;
	}

	public void setRight(HuffmanPairingNode right) {
		this.right = right;
	}

	public int getFrequency() {
		return this.frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getIncomingEdge() {
		return this.incomingEdge;
	}

	public void setIncomingEdge(int incomingEdge) {
		this.incomingEdge = incomingEdge;
	}

	public void setToLeaf() {
		this.isLeaf = true;
	}

	public boolean isLeaf() {
		return this.isLeaf;
	}
}
