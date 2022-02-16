
public class Driver {

	public static void main(String[] args) {

		String code = "XCIUIHTVOQVRLHJEYJXAVICEJFWXRVRUAAEPVPNEQLFZGFQEBXOIUXGIVJXVGLBRBXYIDB"
				+ "FZVKCSGHNITYJBXTSWXZHWZAYSEPINIIZWBRMITIFMQJRKLKASRIMIJPICEMIGGEIINWUT"
				+ "PZWVIFFEDRJXJXEHTISVNGOWRRVLIMZZGWLHZWZKFXHDRRASRXCEKKAOINYJIJLWJPVGGG"
				+ "XMSCSNXVVGTIKLXJXYIAKHVXREKTVZWLPLEERIEJGKGZQVRLBWNSDILBQZWLRSUPZXFVWV"
				+ "SQIIXZXGJRKIFMSAICIUMVJRZGUHQHYEMUTXDSEWXKSHXYILXGCRFPGZCKVFZAWIMIMIFB"
				+ "RMIJTGGWZXFEUHYMXFVVXVJVUYDREPXYSJBDZHNEJKEIXZWKNIYFPEXXHZVRPBNHBIWSJX"
				+ "BVQGPWFEICTSEFYIMTELBSIWJIJOMXIJRGPIIGICHMGZVKEAGGJQDYFBGVXZSFLFTHVJSN"
				+ "POAZXZMLZOVCFXGZWJEJRXJHVGJRTOXYIUHQHYEMUTXDSEWKHPZPPMFMLZLRRVLSAXYIWG"
				+ "HPWVVLAMNEGTDBINFFXZPLZRKLWWEOEZWAGQJXZSFHZZVVPWVXMSEMUGIOAFVCLSMEKVWL"
				+ "XJRRRWEIXXISFBGYIMMUXMAXYIUHQHYEMUTXDSEWHKSQMUIJBWNIIZWWADXYEOTVMEEXKX"
				+ "IFMEKLASNITSEFYIMTELBSIWKLWIVJZZHWKGVRESLIVJZZHWMLZHRXSUIXELWWBXCEJHWL"
				+ "MBRVHLAIOITLFHPJKPWMVLOLRXAMGVRESLUIVGTIKLIYFPEFRXCMIHHTVOCNIVHRJXYENX"
				+ "EICJMDOIMFLPDXXNEEHLAIYMJGMLWDSEWOBXCMEXZXISITYLBZZFIEFVLVVVWLBPGSEKGB"
				+ "RBAYMDXXCIIIZTWISKCWMFZIEEVXGDWZSFPLZXYIJMSNIVODXKDWCELBSIAVQMLXRSIOOB"
				+ "XCGFRYKINWZRVNWOVPEUTHZQZGKIVDZRGQZVJYGWSGHJXYIJLXJGIEXMEIEGTJHEXLKLSM"
				+ "EYHIIKLINECPGYXCIDYDMMKPVGGFTZXZRYVSIGVVFLXCEKLSOIWIVRLAIASTYKHJNSDYUA"
				+ "HZFRXWUYOAVGSGEGPRKJXIOLRXOXADPCRWXHJRXSAGKCSEIKMEIHZRXHVHIUTMUPDGUITT"
				+ "XZESSMMLJASIKMXJTISLXGOPZFWKXTEEHKXGPVZXQBRWSKLGNVGENWSGHJYIXWVLISCSYR";
		/**
		 * "DBMOKWWODJPAOPEPHGQAHWVUJNXDZA"+
		 * "IAQMRPNEMHGPERZOVKRVLWUGAEOPXE"+
		 * "HMXDVBXDDVKOYQHJJBLWKXIJDVUQDB"+
		 * "IPCMAWTBLATKSIZJEYFBSIZBSZVG";
		 * "DBMOKWWODJPAOPEPHGQAHWVUJNXDZAIAQMRPNEMHGPERZOVKRVLWUGAEOPXEHMXDVBXDDVKOYQHJJBLWKXIJDVUQDBIPCMAWTBLATKSIZJEYFBSIZBSZVG";
		 **/
		Project2Solver solver = new Project2Solver(code);

		// Part 1
		// Step 1: Split the cipher into 7 strings
		int numSplits = 7;
		String[] substrings = solver.split(numSplits);
		solver.printSubstrings(substrings);
		System.out.println();

		// Step 2: Calculate the index of coincidence for each substring

		// Get frequency arrays for all substrings
		double[][] freqs = new double[numSplits][];
		for (int i = 0; i < substrings.length; i++) {
			freqs[i] = solver.findFreqs(substrings[i]);
			double indexOfCoincidence = solver.findIoC(substrings[i], freqs[i]);
			System.out.println("I(" + (i + 1) + ") = " + indexOfCoincidence);
		}

		// Step 3: Is this answer correct?
		/*
		 * Since all the indexes of coincidence are very close to the value .065, we
		 * can conclude that key length m = 7 is correct
		 */

		// Part 2: Find the key
		String key = "";
		final double[] alphabet_frequency = { 0.082, 0.015, 0.028, 0.043, 0.127, 0.022, 0.020, 0.061, 0.070, 0.002,
				0.008, 0.040, 0.024, 0.067, 0.075, 0.019, 0.001,
				0.060, 0.063, 0.091, 0.028, 0.010, 0.023, 0.001, 0.020, 0.001 };

		double[][] table = new double[26][numSplits];
		// Start with first substring
		for (int i = 0; i < numSplits; i++) {
			// Find letter frequencies for this substring
			double[] substringFreq = solver.findFreqs(substrings[i]);
			for (int n = 0; n < 26; n++) {
				double mg = (1 / (double) substrings[i].length())
						* solver.dotProduct(alphabet_frequency, substringFreq, 1);
				table[n][i] = mg;
				substringFreq = solver.shiftLeftByOne(substringFreq);
			}

		}

		// Print Formatted Table
		System.out.printf("%2s|%9s|%9s|%9s|%9s|%9s|%9s|%9s\n", "G", "M1", "M2", "M3", "M4", "M5", "M6", "M7");
		System.out.println("-".repeat(75));

		for (int r = 0; r < 26; r++)// Cycles through rows

		{
			System.out.printf("%2d|", r);

			for (int col = 0; col < numSplits; col++) {
				System.out.printf("%10.3f", table[r][col] * 100);

			}
			System.out.println(); // Makes a new row
		}
		// Dot product the letter frequency array with an array of the known english
		// alphabet letter frequencies (should be a public static final array)

		// Shift the freq array elements one place to the left to and dot product again

		// Repeat this 26 times - the shift corresponding with the highest dot product
		// represents the letter in the key word

		// Do this for all 7 substrings

		key = solver.getKey(table);
		System.out.println("Key is: " + key);
		// Part 3: Decrypt the given cipher text
		String plaintext = solver.decryptVigenere(key);
		System.out.println(plaintext);

	}
}
