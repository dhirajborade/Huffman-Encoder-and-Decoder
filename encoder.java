import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

public class encoder {

	public static void main(String[] inputArguments) throws IOException, FileNotFoundException {

		System.out.println();
		System.out.println("Starting Huffman Encoding...");
		System.out.println();

		Huffman hM = new Huffman();
		FileReader inputFile = null;
		OutputStream os = null;
		inputFile = new FileReader(inputArguments[0]);
		os = new FileOutputStream("encoded.bin", true);

		System.out.println("Generating Code Table...");
		long startTimeCodeTable = System.currentTimeMillis();
		hM.generateWordCount(inputArguments[0]);
		long endTimeCodeTable = System.currentTimeMillis();
		long totalTimeTakenCodeTable = endTimeCodeTable - startTimeCodeTable;
		System.out.println("Code Table generated in " + totalTimeTakenCodeTable + " mS");
		System.out.println();

		// System.out.println("Huffman Tree generation using Binary Heap...");
		// long startTimeBinaryHeap = System.currentTimeMillis();
		// hM.Huffman_Tree_Binary_Heap();
		// long endTimeBinaryHeap = System.currentTimeMillis();
		// long totalTimeTakenBinaryHeap = endTimeBinaryHeap -
		// startTimeBinaryHeap;
		// System.out.println("Huffman Tree generated using Binary Heap in " +
		// totalTimeTakenBinaryHeap + " mS");
		// System.out.println();

		System.out.println("Huffman Tree generation using Four Way Heap...");
		long startTimeFourWayHeap = System.currentTimeMillis();
		hM.Huffman_Tree_FourWay_Heap();
		long endTimeFourWayHeap = System.currentTimeMillis();
		long totalTimeTakenFourWayHeap = endTimeFourWayHeap - startTimeFourWayHeap;
		System.out.println("Huffman Tree generated using Four Way Heap in " + totalTimeTakenFourWayHeap + " mS");
		System.out.println();

		// System.out.println("Huffman Tree generation using Pairing Heap...");
		// long startTimePairingHeap = System.currentTimeMillis();
		// hM.Huffman_Tree_Pairing_Heap();
		// long endTimePairingHeap = System.currentTimeMillis();
		// long totalTimeTakenPairingHeap = endTimePairingHeap -
		// startTimePairingHeap;
		// System.out.println("Huffman Tree generated using Pairing Heap in " +
		// totalTimeTakenPairingHeap + " mS");
		// System.out.println();

		System.out.println("Continuing Huffman Encoding using Four Way Heap...");
		long startTimeEncoding = System.currentTimeMillis();
		System.out.println();

		BufferedReader br = new BufferedReader(inputFile);
		String inputLine = null;
		StringBuilder sb = new StringBuilder();
		try {
			inputLine = br.readLine();
			while (inputLine != null) {
				if (hM.getSymbolTable().containsKey(inputLine)) {
					sb.append(hM.getSymbolTable().get(inputLine));
					while (sb.length() >= 8) {
						String str = sb.substring(0, 8);
						int temp = Integer.parseInt(str, 2);
						os.write(temp);
						sb.delete(0, 8);
					}
				}
				inputLine = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			os.close();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTimeEncoding = System.currentTimeMillis();
		long totalTimeEncoding = endTimeEncoding - startTimeEncoding;
		long encodingTime = totalTimeTakenCodeTable + totalTimeTakenFourWayHeap + totalTimeEncoding;

		System.out.println("Input file Encoded to encoded.bin");
		System.out.println("Huffman Encoding using Four Way Heap completed in " + encodingTime + " mS");
		System.out.println();

		double inputFileSize = (hM.getFileSize(inputArguments[0]));
		double encodedFileSize = (hM.getFileSize("encoded.bin"));
		System.out.println("Original File Size: " + (inputFileSize / 1024) + " Kilobytes");
		System.out.println("Encoded File Size: " + (encodedFileSize / 1024) + " Kilobytes");
		double reduction = ((encodedFileSize - inputFileSize) / inputFileSize) * 100;
		System.out.println("% Reduction = " + (-reduction));
		System.out.println();
	}
}
