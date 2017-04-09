
public class DecoderNode {

	private DecoderNode leftChild;
	private DecoderNode rightChild;
	private String key;
	private String code;

	public DecoderNode() {

	}

	public DecoderNode(String key, String code, DecoderNode leftChild, DecoderNode rightChild) {
		this.key = key;
		this.code = code;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	public DecoderNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(DecoderNode leftChild) {
		this.leftChild = leftChild;
	}

	public DecoderNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(DecoderNode rightChild) {
		this.rightChild = rightChild;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
