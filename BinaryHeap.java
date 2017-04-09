import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryHeap {

	private static final int d = 2;
	private int heapSize;
	private static List<HuffmanBinaryNode> heap;

	public BinaryHeap(int capacity) {
		heapSize = 0;
		heap = new ArrayList<HuffmanBinaryNode>(0);
	}

	public boolean isEmpty() {
		return heapSize == 0;
	}

	public boolean isFull() {
		return heapSize == heap.size();
	}

	public void makeEmpty() {
		heapSize = 0;
	}

	private int parent(int i) {
		return (i - 1) / d;
	}

	private int kthChild(int i, int k) {
		return d * i + k;
	}

	public void insert(HuffmanBinaryNode huffmanBinaryNode) {
		heapSize++;
		heap.add(huffmanBinaryNode);
		heapifyUp(heapSize - 1);
	}

	public int findMin() {
		if (isEmpty()) {
			throw new NoSuchElementException("Underflow Exception");
		}
		return heap.get(0).getFrequency();
	}

	public HuffmanBinaryNode deleteMin() {
		HuffmanBinaryNode keyItem = heap.get(0);
		delete(0);
		return keyItem;
	}

	public HuffmanBinaryNode delete(int ind) {
		if (isEmpty()) {
			throw new NoSuchElementException("Underflow Exception");
		}
		HuffmanBinaryNode keyItem = heap.get(ind);
		heap.set(0, heap.get(heapSize - 1));
		heap.remove(heapSize - 1);
		heapSize--;
		if (!heap.isEmpty()) {
			heapifyDown(ind);
		}
		return keyItem;
	}

	private void heapifyUp(int childInd) {
		if (childInd == 0) {
			return;
		}
		HuffmanBinaryNode tmp = heap.get(childInd);
		while (childInd > 0 && tmp.getFrequency() < (heap.get(parent(childInd)).getFrequency())) {
			heap.set(childInd, heap.get(parent(childInd)));
			childInd = parent(childInd);
		}
		heap.set(childInd, tmp);
	}

	private void heapifyDown(int ind) {
		int child;
		HuffmanBinaryNode tmp = heap.get(ind);
		while (kthChild(ind, 1) < heapSize) {
			child = minChild(ind);
			if ((heap.get(child).getFrequency() <= tmp.getFrequency()))
				heap.set(ind, heap.get(child));
			else
				break;
			ind = child;
		}
		heap.set(ind, tmp);
	}

	private int minChild(int ind) {
		int bestChild = kthChild(ind, 1);
		int k = 2;
		int pos = kthChild(ind, k);
		while ((k <= d) && (pos < heapSize)) {
			if ((heap.get(pos).getFrequency()) < (heap.get(bestChild).getFrequency()))
				bestChild = pos;
			pos = kthChild(ind, k++);
		}
		return bestChild;
	}

	public void printHeap() {
		System.out.print("\nHeap = ");
		for (int i = 0; i < heapSize; i++) {
			System.out.print(heap.get(i) + " ");
		}
		System.out.println();
	}
}