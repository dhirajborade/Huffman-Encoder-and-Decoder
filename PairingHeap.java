import java.util.ArrayList;
import java.util.List;

public class PairingHeap {
	private int heapSize;
	private static List<HuffmanPairingNode> heap;
	private static HuffmanPairingNode root = null;

	public PairingHeap(int capacity) {
		setHeapSize(capacity);
		setHeap(new ArrayList<HuffmanPairingNode>(capacity));
	}

	public PairingHeap() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void makeEmpty() {
		root = null;
	}

	public HuffmanPairingNode insert(HuffmanPairingNode huffmanPairingNode) {
		if (root == null) {
			root = huffmanPairingNode;
		} else {
			root = compareAndLink(root, huffmanPairingNode);
		}
		return root;
	}

	private static HuffmanPairingNode compareAndLink(HuffmanPairingNode first, HuffmanPairingNode second) {
		if (second == null) {
			return first;
		}
		if (second.getFrequency() < first.getFrequency()) {
			second.setChild(first.getChild());
			first.setChild(second);
			first.setNextSibling(second.getLeftChild());
			if (first.getNextSibling() != null) {
				first.getNextSibling().setChild(first);
			}
			second.setLeftChild(first);
			return second;
		} else {
			second.setChild(first);
			first.setNextSibling(second.getNextSibling());
			if (first.getNextSibling() != null) {
				first.getNextSibling().setChild(first);
			}
			second.setNextSibling(first.getLeftChild());
			if (second.getNextSibling() != null) {
				second.getNextSibling().setChild(second);
			}
			first.setLeftChild(second);
			return first;
		}
	}

	public int getmin() {
		if (root != null) {
			return root.getFrequency();
		}
		return -1;
	}

	public HuffmanPairingNode deleteMin() {
		if (isEmpty()) {
			return null;
		}
		HuffmanPairingNode x = root;
		if (root.getLeftChild() == null) {
			root = null;
		} else {
			root = combinesiblings(root.getLeftChild());
		}
		return x;
	}

	private HuffmanPairingNode combinesiblings(HuffmanPairingNode firstSibling) {
		if (firstSibling.getNextSibling() == null) {
			return firstSibling;
		}
		List<HuffmanPairingNode> pairarray = new ArrayList<HuffmanPairingNode>();
		while (firstSibling != null) {
			pairarray.add(firstSibling);
			firstSibling.getChild().setNextSibling(null);
			firstSibling = firstSibling.getNextSibling();
		}
		int i = 0;
		for (; i + 1 < pairarray.size(); i += 2) {
			pairarray.set(i, compareAndLink(pairarray.get(i), pairarray.get(i + 1)));
		}
		int j = i - 2;
		if (j == pairarray.size() - 3) {
			pairarray.set(j, compareAndLink(pairarray.get(j), pairarray.get(j + 2)));
		}
		for (; j >= 2; j -= 2) {
			pairarray.set(j - 2, compareAndLink(pairarray.get(j - 2), pairarray.get(j)));
		}
		return pairarray.get(0);
	}

	public int getHeapSize() {
		return heapSize;
	}

	public void setHeapSize(int heapSize) {
		this.heapSize = heapSize;
	}

	public static List<HuffmanPairingNode> getHeap() {
		return heap;
	}

	public static void setHeap(List<HuffmanPairingNode> heap) {
		PairingHeap.heap = heap;
	}
}