import java.util.Arrays;

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
		Project2Solver solver = new Project2Solver(code);
		
		//Part 1
		//Step 1: Split the cipher into 7 strings
		int numSplits = 7;
		String[] substrings = solver.split(numSplits);
		solver.printSubstrings(substrings);
		System.out.println();

		//Step 2: Calculate the index of coincidence for each substring
		
		//Get frequency arrays for all substrings
		double[][] freqs = new double[numSplits][];
		for (int i = 0; i < substrings.length; i++) {
			freqs[i] = solver.findFreqs(substrings[i]);
			double indexOfCoincidence = solver.findIoC(substrings[i], freqs[i]);
			System.out.println("I(" + (i + 1) + ") = " + indexOfCoincidence);
		}
		
		//Step 3: Is this answer correct?
		/* 
		 * Since all the indexes of coincidence are very close to the value .065, we
		 * can conclude that key length m = 7 is correct
		 */
		
		
		//Part 2: Find the key
		String key = "";
		//Start with first substring
		
		//Find letter frequencies for this substring
		
		//Dot product the letter frequency array with an array of the known english
		//alphabet letter frequencies (should be a public static final array)
		
		//Shift the freq array elements one place to the left to and dot product again
		
		//Repeat this 26 times - the shift corresponding with the highest dot product 
		//represents the letter in the key word
		
		//Do this for all 7 substrings
		
		
		//Part 3: Decrypt the given cipher text
		//String plaintext = solver.decryptVigenere(key);
		//System.out.println(plaintext);
		
	}
}
