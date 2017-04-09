import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Huffman {

	private HuffmanBinaryNode huffmanBinaryHeapRoot = null;
	private HuffmanFourWayNode huffmanFourWayHeapRoot = null;
	private HuffmanPairingNode huffmanPairingHeapRoot = null;
	private TreeMap<String, Integer> treeMapWordCount = new TreeMap<String, Integer>();
	private HashMap<String, String> symbolTable = new HashMap<String, String>();

	public void generateWordCount(String inputArgs) throws FileNotFoundException, IOException {
		FileReader inputFile = new FileReader(inputArgs);
		BufferedReader br = new BufferedReader(inputFile);
		String inputLine = "";
		try {
			inputLine = br.readLine();
			while (inputLine != null) {
				Integer weight;
				if (!inputLine.isEmpty()) {
					if (treeMapWordCount.containsKey(inputLine) == false)
						weight = new Integer(1);
					else
						weight = treeMapWordCount.get(inputLine) + 1;
					treeMapWordCount.put(inputLine, weight);
				}
				inputLine = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Huffman_Tree_Binary_Heap() throws FileNotFoundException, IOException {

		BinaryHeap binHeap = new BinaryHeap(0);
		for (Map.Entry<String, Integer> map : treeMapWordCount.entrySet()) {
			if (!map.getKey().isEmpty()) {
				HuffmanBinaryNode hNode = new HuffmanBinaryNode(map.getValue(), map.getKey());
				hNode.setToLeaf();
				binHeap.insert(hNode);
			}
		}

		while (!binHeap.isEmpty()) {
			HuffmanBinaryNode temp1 = binHeap.deleteMin();
			HuffmanBinaryNode temp2 = null;
			if (!binHeap.isEmpty()) {
				temp2 = binHeap.deleteMin();
			} else {
				break;
			}
			HuffmanBinaryNode newNode = new HuffmanBinaryNode();
			newNode.setFrequency(temp1.getFrequency() + temp2.getFrequency());
			newNode.setLeft(temp1);
			newNode.setRight(temp2);
			newNode.getLeft().setIncomingEdge(0);
			newNode.getRight().setIncomingEdge(1);
			huffmanBinaryHeapRoot = newNode;
			if (!binHeap.isEmpty()) {
				binHeap.insert(newNode);
			}
		}

		File file = new File("code_table.txt");
		FileWriter fw = new FileWriter(file, true);
		generateCodeHuffmanBinaryNode(huffmanBinaryHeapRoot, fw);
		fw.close();
	}

	public void Huffman_Tree_FourWay_Heap() throws FileNotFoundException, IOException {

		FourWayHeap fourWayHeap = new FourWayHeap(0, 4);
		for (Map.Entry<String, Integer> map : treeMapWordCount.entrySet()) {
			if (!map.getKey().isEmpty()) {
				HuffmanFourWayNode hNode = new HuffmanFourWayNode(map.getValue(), map.getKey());
				hNode.setToLeaf();
				fourWayHeap.insert(hNode);
			}
		}

		while (!fourWayHeap.isEmpty()) {
			HuffmanFourWayNode temp1 = fourWayHeap.deleteMin();
			HuffmanFourWayNode temp2 = null;
			if (!fourWayHeap.isEmpty()) {
				temp2 = fourWayHeap.deleteMin();
			} else {
				break;
			}
			HuffmanFourWayNode newNode = new HuffmanFourWayNode();
			newNode.setFrequency(temp1.getFrequency() + temp2.getFrequency());
			newNode.setLeft(temp1);
			newNode.setRight(temp2);
			newNode.getLeft().setIncomingEdge(0);
			newNode.getRight().setIncomingEdge(1);
			huffmanFourWayHeapRoot = newNode;
			if (!fourWayHeap.isEmpty()) {
				fourWayHeap.insert(newNode);
			}
		}

		File file = new File("code_table.txt");
		FileWriter fw = new FileWriter(file, true);
		generateCodeHuffmanFourWayNode(huffmanFourWayHeapRoot, fw);
		fw.close();
	}

	public void Huffman_Tree_Pairing_Heap() throws FileNotFoundException, IOException {

		PairingHeap pairingHeap = new PairingHeap(0);
		for (Map.Entry<String, Integer> map : treeMapWordCount.entrySet()) {
			if (!map.getKey().isEmpty()) {
				HuffmanPairingNode hNode = new HuffmanPairingNode(map.getValue(), map.getKey());
				hNode.setToLeaf();
				pairingHeap.insert(hNode);
			}
		}

		while (!pairingHeap.isEmpty()) {
			HuffmanPairingNode temp1 = pairingHeap.deleteMin();
			HuffmanPairingNode temp2 = null;
			if (!pairingHeap.isEmpty()) {
				temp2 = pairingHeap.deleteMin();
			} else {
				break;
			}
			HuffmanPairingNode newNode = new HuffmanPairingNode();
			newNode.setFrequency(temp1.getFrequency() + temp2.getFrequency());
			newNode.setLeft(temp1);
			newNode.setRight(temp2);
			newNode.getLeft().setIncomingEdge(0);
			newNode.getRight().setIncomingEdge(1);
			huffmanPairingHeapRoot = newNode;
			if (!pairingHeap.isEmpty()) {
				pairingHeap.insert(newNode);
			}
		}

		File file = new File("code_table.txt");
		FileWriter fw = new FileWriter(file, true);
		generateCodeHuffmanPairingNode(huffmanPairingHeapRoot, fw);
		fw.close();
	}

	public void generateCodeHuffmanBinaryNode(HuffmanBinaryNode huffmanBinaryNode, FileWriter writer)
			throws FileNotFoundException, IOException {
		int symbolCodeTable[] = new int[1000];
		generateCodeRecursivelyHuffmanBinaryNode(huffmanBinaryNode, symbolCodeTable, 0, writer);
	}

	public void generateCodeRecursivelyHuffmanBinaryNode(HuffmanBinaryNode huffmanBinaryNode, int symbolCodeTable[],
			int pathLength, FileWriter writer) throws FileNotFoundException, IOException {
		if (huffmanBinaryNode == null) {
			return;
		}
		symbolCodeTable[pathLength] = huffmanBinaryNode.getIncomingEdge();
		pathLength++;
		if (huffmanBinaryNode.isLeaf()) {
			printPath(symbolCodeTable, pathLength, huffmanBinaryNode.getKey(), writer);
		} else {
			generateCodeRecursivelyHuffmanBinaryNode(huffmanBinaryNode.getLeft(), symbolCodeTable, pathLength, writer);
			generateCodeRecursivelyHuffmanBinaryNode(huffmanBinaryNode.getRight(), symbolCodeTable, pathLength, writer);
		}
	}

	public void generateCodeHuffmanFourWayNode(HuffmanFourWayNode huffmanFourWayNode, FileWriter writer)
			throws FileNotFoundException, IOException {
		int symbolCodeTable[] = new int[1000];
		generateCodeRecursivelyHuffmanFourWayNode(huffmanFourWayNode, symbolCodeTable, 0, writer);
	}

	public void generateCodeRecursivelyHuffmanFourWayNode(HuffmanFourWayNode huffmanFourWayNode, int symbolCodeTable[],
			int pathLength, FileWriter writer) throws FileNotFoundException, IOException {
		if (huffmanFourWayNode == null) {
			return;
		}
		symbolCodeTable[pathLength] = huffmanFourWayNode.getIncomingEdge();
		pathLength++;
		if (huffmanFourWayNode.isLeaf()) {
			printPath(symbolCodeTable, pathLength, huffmanFourWayNode.getKey(), writer);
		} else {
			generateCodeRecursivelyHuffmanFourWayNode(huffmanFourWayNode.getLeft(), symbolCodeTable, pathLength,
					writer);
			generateCodeRecursivelyHuffmanFourWayNode(huffmanFourWayNode.getRight(), symbolCodeTable, pathLength,
					writer);
		}
	}

	public void generateCodeHuffmanPairingNode(HuffmanPairingNode huffmanPairingNode, FileWriter writer)
			throws FileNotFoundException, IOException {
		int symbolCodeTable[] = new int[1000];
		generateCodeRecursivelyHuffmanPairingNode(huffmanPairingNode, symbolCodeTable, 0, writer);
	}

	public void generateCodeRecursivelyHuffmanPairingNode(HuffmanPairingNode huffmanPairingNode, int symbolCodeTable[],
			int pathLength, FileWriter writer) throws FileNotFoundException, IOException {
		if (huffmanPairingNode == null) {
			return;
		}
		symbolCodeTable[pathLength] = huffmanPairingNode.getIncomingEdge();
		pathLength++;
		if (huffmanPairingNode.isLeaf()) {
			printPath(symbolCodeTable, pathLength, huffmanPairingNode.getKey(), writer);
		} else {
			generateCodeRecursivelyHuffmanPairingNode(huffmanPairingNode.getLeft(), symbolCodeTable, pathLength,
					writer);
			generateCodeRecursivelyHuffmanPairingNode(huffmanPairingNode.getRight(), symbolCodeTable, pathLength,
					writer);
		}
	}

	public void printPath(int symbolCodeTable[], int pathLength, String key, FileWriter writer)
			throws FileNotFoundException, IOException {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < pathLength; i++) {
			sb.append(symbolCodeTable[i]);
		}
		getSymbolTable().put(key, sb.toString());
		writer.write(key + " " + sb.toString() + "\n");
	}

	public HashMap<String, String> getSymbolTable() {
		return this.symbolTable;
	}

	public void setSymbolTable(HashMap<String, String> symbolTable) {
		this.symbolTable = symbolTable;
	}

	public long getFileSize(String filename) {
		File file = new File(filename);
		if (!file.exists() || !file.isFile()) {
			System.out.println("File doesn\'t exist");
			return -1;
		}
		return file.length();
	}
}
