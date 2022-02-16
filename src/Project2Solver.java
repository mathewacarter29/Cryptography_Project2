

public class Project2Solver {

	public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	private String code;
	/**
	 * Constructor for the Solver - initializes a "code" field variable representing 
	 * what needs to be decrypted
	 * @param code	Code to be decrypted
	 */
	public Project2Solver(String code) {
		this.code = code;
	}
	
	/**
	 * This method breaks the code into (keyLength) separate strings and returns
	 * an array of these strings
	 * @param keyLength		The guess for the length of the key
	 * @return		An array containing the split up strings
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
	 * @param keyLength		The length of the key word
	 */
	public void printSubstrings(String[] substrings) {
		for (int i = 0; i < substrings.length; i++) {
			System.out.println("y" + (i + 1) + " = " + substrings[i]);
		}
	}
	
	/**
	 * Gets an array of the letter frequencies in the string
	 * Letter Frequency = # of occurances / length of string
	 * @param str	The string we want to find the letter frequencies of
	 * @return	An array of length 26 of letter frequencies (arr[0] = freq of 'A',
	 * 			arr[1] = freq of 'B', etc.)
	 */
	public double[] findFreqs(String str) {
		double[] freqs = new double[26];
		
		//1. Get frequencies of all letters in the substring
		for (int i = 0; i < str.length(); i++) {
			freqs[ALPHABET.indexOf(str.charAt(i))] += 1;
		}
		
		return freqs;
	}
	
	/**
	 * Finds the index of coincidence for a given string
	 * @param str	The string we want the IoC for
	 * @param frequencyArray	The array of letter frequencies for this string
	 * @return		The index of coincidence for the given substring
	 */
	public double findIoC(String str, double[] frequencyArray) {		
		
		//2. Sum f(i) * (f(i) - 1)
		double total = 0;
		for (int j = 0; j < frequencyArray.length; j++) {
			total += frequencyArray[j] * (frequencyArray[j] - 1);
		}
		
		//3. IoC = total / n(n-1) where n = length of substring
		return total / (str.length() * (str.length() - 1));
		
	}
	
	/**
	 * This method decrypts a Vigenere (block) cipher when given the key
	 * @param key	The key for decrypting the code
	 */
	public String decryptVigenere(String key) {
		String result = "";
		for (int i = 0; i < this.code.length(); i++) {
			char codeLetter = code.charAt(i);
			char keyLetter = key.charAt((i % key.length()));
			int codeIndex = ALPHABET.indexOf(codeLetter);
			int keyIndex = ALPHABET.indexOf(keyLetter);
			//System.out.print(ALPHABET.charAt((26 + codeIndex - keyIndex) % 26));
			result += ALPHABET.charAt((26 + codeIndex - keyIndex) % 26);
		}
		return result;
	}
	
}
