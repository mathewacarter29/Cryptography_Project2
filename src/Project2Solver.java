import java.util.Arrays;
import java.util.Collections;

public class Project2Solver {

	public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private String code;

	/**
	 * Constructor for the Solver - initializes a "code" field variable representing
	 * what needs to be decrypted
	 * 
	 * @param code Code to be decrypted
	 */
	public Project2Solver(String code) {
		this.code = code;
	}

	/**
	 * This method breaks the code into (keyLength) separate strings and returns
	 * an array of these strings
	 * 
	 * @param keyLength The guess for the length of the key
	 * @return An array containing the split up strings
	 */
	public String[] split(int keyLength) {
		String[] result = new String[keyLength];
		for (int i = 0; i < this.code.length(); i++) {
			if (result[i % keyLength] == null) {
				result[i % keyLength] = "";
			}
			result[i % keyLength] += this.code.charAt(i);
		}
		return result;
	}

	/**
	 * Splits the code into substrings based on their position and outputs them
	 * 
	 * @param keyLength The length of the key word
	 */
	public void printSubstrings(String[] substrings) {
		for (int i = 0; i < substrings.length; i++) {
			System.out.println("y" + (i + 1) + " = " + substrings[i]);
		}
	}

	/**
	 * Gets an array of the letter frequencies in the string
	 * Letter Frequency = # of occurances / length of string
	 * 
	 * @param str The string we want to find the letter frequencies of
	 * @return An array of length 26 of letter frequencies (arr[0] = freq of 'A',
	 *         arr[1] = freq of 'B', etc.)
	 */
	public double[] findFreqs(String str) {
		double[] freqs = new double[26];

		// 1. Get frequencies of all letters in the substring
		for (int i = 0; i < str.length(); i++) {
			freqs[ALPHABET.indexOf(str.charAt(i))] += 1;
		}

		return freqs;
	}

	/**
	 * Finds the index of coincidence for a given string
	 * 
	 * @param str            The string we want the IoC for
	 * @param frequencyArray The array of letter frequencies for this string
	 * @return The index of coincidence for the given substring
	 */
	public double findIoC(String str, double[] frequencyArray) {

		// 2. Sum f(i) * (f(i) - 1)
		double total = 0;
		for (int j = 0; j < frequencyArray.length; j++) {
			total += frequencyArray[j] * (frequencyArray[j] - 1);
		}

		// 3. IoC = total / n(n-1) where n = length of substring
		return total / (str.length() * (str.length() - 1));

	}

	/**
	 * Finds the dot product of two arrays. Must input arrays of equal length.
	 * 
	 * @param first  first double array
	 * @param second second double array
	 * @return a double value that is the dotPoduct of two arrays
	 */
	public double dotProduct(double[] first, double[] second, /** set to 1 if unwanted */
	double multiplier) {

		double prod = 0;
		for (int i = 0; i < first.length; i++) {
			prod += multiplier * first[i] * second[i];
		}
		return prod;
	}

	/**
	 * Cyclically shifts array left by one (value in index 0 goes to the end of the array)
	 * @param arr input array
	 * @return shifted array
	 */
	public double[] shiftLeftByOne(double[] arr) {
		double temp = arr[0];
		for (int i = 1; i < arr.length; i++) {
			arr[i - 1] = arr[i];
		}
		arr[arr.length - 1] = temp;
		return arr;
	}

	/**
	 * Loop through each column in the table to get max value in each column and the
	 * corresponding letter in the alphabet
	 * @param freqTable conatining coincedences index
	 * @return key based on the max values in the table
	 */
	public String getKey(double[][] freqTable) {
		String key = "";
		// private <T> T[] getColumn(int address, T[][] from) {
		for (int col = 0; col < freqTable[0].length; col++) {
			// (T[]) Arrays.stream(freqTable).map(x -> x[getCol]).toArray(Object[]::new);
			// }
			Double[] currColumn = getColumn(freqTable, col);
			Double max = Collections.max(Arrays.asList(currColumn));
			int index = Arrays.asList(currColumn).indexOf(max);
			key += ALPHABET.charAt(index);
		}
		return key;

	}

	/**
	 * Creates a Double[] array for the column indicated by the input
	 * @param matrix 
	 * @param col must be within the matrix size
	 * @return Double[] corresponding to the indicated column
	 */
	public Double[] getColumn(double[][] matrix, int col) {
		Double[] column = new Double[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			column[i] = (Double) matrix[i][col];
		}
		return column;
	}

	/**
	 * This method decrypts a Vigenere (block) cipher when given the key
	 * 
	 * @param key The key for decrypting the code
	 */
	public String decryptVigenere(String key) {
		String result = "";
		for (int i = 0; i < this.code.length(); i++) {
			char codeLetter = code.charAt(i);
			char keyLetter = key.charAt((i % key.length()));
			int codeIndex = ALPHABET.indexOf(codeLetter);
			int keyIndex = ALPHABET.indexOf(keyLetter);
			// System.out.print(ALPHABET.charAt((26 + codeIndex - keyIndex) % 26));
			result += ALPHABET.charAt((26 + codeIndex - keyIndex) % 26);
		}
		return result;
	}

}
