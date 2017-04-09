import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class FourWayHeap {

	private int d;
	private int heapSize;
	private static List<HuffmanFourWayNode> heap;

	public FourWayHeap(int capacity, int numChild) {
		d = numChild;
		heapSize = 3;
		heap = new ArrayList<HuffmanFourWayNode>(capacity);
		heap.add(new HuffmanFourWayNode(-1, null));
		heap.add(new HuffmanFourWayNode(-1, null));
		heap.add(new HuffmanFourWayNode(-1, null));
	}

	public boolean isEmpty() {
		return heapSize == 3;
	}

	public boolean isFull() {
		return heapSize == heap.size();
	}

	public void clear() {
		heapSize = 3;
	}

	private int parent(int i) {
		return (int) (i / d) + 2;
	}

	private int kthChild(int i, int k) {
		return d * i + k - 9;
	}

	public void insert(HuffmanFourWayNode huffmanFourWayNode) {
		/** Percolate up **/
		heapSize++;
		heap.add(huffmanFourWayNode);
		heapifyUp(heapSize - 1);
	}

	public int findMin() {
		if (isEmpty()) {
			throw new NoSuchElementException("Underflow Exception");
		}
		return heap.get(3).getFrequency();
	}

	public HuffmanFourWayNode delete(int ind) {
		if (isEmpty()) {
			throw new NoSuchElementException("Underflow Exception");
		}
		HuffmanFourWayNode keyItem = heap.get(ind);
		heap.set(3, heap.get(heapSize - 1));
		heap.remove(heapSize - 1);
		heapSize--;
		if (!heap.isEmpty()) {
			heapifyDown(ind);
		}
		return keyItem;
	}

	public HuffmanFourWayNode deleteMin() {
		HuffmanFourWayNode keyItem = heap.get(3);
		delete(3);
		return keyItem;
	}

	private void heapifyUp(int childInd) {
		if (childInd <= 3) {
			return;
		}
		HuffmanFourWayNode tmp = heap.get(childInd);
		while (childInd > 3 && tmp.getFrequency() < (heap.get(parent(childInd)).getFrequency())) {
			heap.set(childInd, heap.get(parent(childInd)));
			childInd = parent(childInd);
		}
		heap.set(childInd, tmp);
	}

	private void heapifyDown(int ind) {
		int child;
		if (heapSize <= 4) {
			return;
		}
		HuffmanFourWayNode temp = heap.get(ind);
		while (kthChild(ind, 1) < heapSize) {
			child = minChild(ind);
			if ((heap.get(child).getFrequency() <= temp.getFrequency())) {
				heap.set(ind, heap.get(child));
			} else {
				break;
			}
			ind = child;
		}
		heap.set(ind, temp);
	}

	private int minChild(int ind) {
		int bestChild = kthChild(ind, 1);
		int k = 2;
		int pos = kthChild(ind, k);
		while ((k <= d) && (pos < heapSize)) {
			if ((heap.get(pos).getFrequency()) < (heap.get(bestChild).getFrequency())) {
				bestChild = pos;
			}
			pos = kthChild(ind, ++k);
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