import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class decoder {

	public static DecoderNode decoderNode = new DecoderNode(null, "-1", null, null);

	public static void main(String[] args) throws FileNotFoundException, IOException {

		Huffman hM = new Huffman();
		System.out.println();

		System.out.println("Starting Huffman Decoding...");
		long startTimeDecoding = System.currentTimeMillis();

		if (args.length == 2 && !args[1].equals(null) && !args[0].equals(null)) {
			try {
				String encodedBinFile = args[0];
				String codeTableFile = args[1];

				Decode(encodedBinFile, codeTableFile);

				long endTimeDecoding = System.currentTimeMillis();
				long totalTimeDecoding = endTimeDecoding - startTimeDecoding;
				System.out.println("Input file Decoded to decoded.txt");
				System.out.println();
				System.out.println("Huffman Decoding completed in " + totalTimeDecoding + " mS");
				System.out.println();
				double decodedFileSize = (hM.getFileSize("decoded.txt"));
				System.out.println("Original File Size: " + (decodedFileSize / 1024) + " Kilobytes");
				System.out.println();

			} catch (IOException exceptionio) {

				exceptionio.printStackTrace();
			}
		}

		else {
			System.out.println("Please Enter Valid Arguments");
		}

	}

	private static void Decode(String encodedBinFile, String codeTableFile) throws FileNotFoundException, IOException {

		System.out.println();
		System.out.println("Generating Decoder Tree...");
		long startTimeDecoderTree = System.currentTimeMillis();

		FileReader inFile = new FileReader(codeTableFile);
		BufferedReader br = new BufferedReader(inFile);
		String inputLine = "";

		inputLine = br.readLine();
		while (inputLine != null) {
			String str = inputLine;
			String[] parts = str.split(" ");
			DecoderNode dNode1 = decoderNode;
			BuildTree(parts[0], parts[1], dNode1);
			inputLine = br.readLine();
		}

		long endTimeDecoderTree = System.currentTimeMillis();
		long totalTimeDecoderTree = endTimeDecoderTree - startTimeDecoderTree;
		System.out.println("Decoder Tree generated in " + totalTimeDecoderTree + " mS");

		System.out.println();

		System.out.println("Continuing Huffman Decoding...");

		FileInputStream fis = new FileInputStream(encodedBinFile);
		BufferedInputStream bis = new BufferedInputStream(fis);

		FileWriter fw = new FileWriter("decoded.txt", true);
		BufferedWriter bw = new BufferedWriter(fw);
		int byteRead;
		StringBuilder sb = new StringBuilder();
		DecoderNode dNode2 = decoderNode;

		while ((byteRead = bis.read()) != -1) {
			String str = String.format("%1s", Integer.toBinaryString((byteRead + 256) % 256));
			sb.append(String.format("%8s", str).replace(" ", "0"));

			if (sb.length() > 1000000) {
				int i = 0;
				while (i < sb.length()) {

					if (sb.charAt(i) == '0') {

						if (dNode2.getLeftChild() == null && dNode2.getRightChild() == null) {

							bw.write(dNode2.getKey());
							bw.newLine();
							dNode2 = decoderNode;
						}
						dNode2 = dNode2.getLeftChild();
					} else {
						if (dNode2.getRightChild() == null && dNode2.getRightChild() == null) {

							bw.write(dNode2.getKey());
							bw.newLine();
							dNode2 = decoderNode;
						}
						dNode2 = dNode2.getRightChild();
					}
					i++;
				}
				sb = new StringBuilder();
			}
		}

		int i = 0;
		while (i < sb.length()) {
			if (sb.charAt(i) == '0') {
				if (dNode2.getLeftChild() == null && dNode2.getRightChild() == null) {
					bw.write(dNode2.getKey());
					bw.newLine();
					dNode2 = decoderNode;
				}
				dNode2 = dNode2.getLeftChild();
			} else {
				if (dNode2.getRightChild() == null && dNode2.getRightChild() == null) {
					bw.write(dNode2.getKey());
					bw.newLine();
					dNode2 = decoderNode;
				}
				dNode2 = dNode2.getRightChild();
			}
			i++;
		}
		if (dNode2.getKey() != null) {
			bw.write(dNode2.getKey() + "\n");
		}
		br.close();
		bw.close();
		bis.close();
	}

	private static void BuildTree(String key, String code, DecoderNode root) {

		int i = 0;
		while (i < code.length()) {
			if (i == code.length() - 1) {
				if (code.charAt(i) == '0') {
					root.setLeftChild(new DecoderNode(key, code, null, null));
				} else if (code.charAt(i) == '1') {
					root.setRightChild(new DecoderNode(key, code, null, null));
				}
			} else {
				if (code.charAt(i) == '0' && root.getLeftChild() == null) {
					root.setLeftChild(new DecoderNode("-1", "-1", null, null));
					root = root.getLeftChild();
				} else if (root.getLeftChild() != null && code.charAt(i) != '1') {
					root = root.getLeftChild();
				} else if (code.charAt(i) == '1' && root.getRightChild() == null) {
					root.setRightChild(new DecoderNode("-1", "-1", null, null));
					root = root.getRightChild();
				} else if (root.getRightChild() != null && code.charAt(i) != '0') {
					root = root.getRightChild();
				}
			}
			i++;
		}
	}
}