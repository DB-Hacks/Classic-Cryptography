package Caesar;

import Utility.CryptoTools;

public class C_Crypta {
	public static void main(String[] args) throws Exception
	{
		byte[] ct = CryptoTools.fileToBytes("C:\\Users\\Danie\\eclipse-workspace\\Labs\\EECS3481\\src\\data\\MSG2.ct");
		int[] freq = CryptoTools.getFrequencies(ct);
		double letr = 0;
		double highest = 0;
		int positionE = 0;
		int key = 0;
		for(int i = 0; i < freq.length; i++) {
			letr = (double) (freq[i])/(ct.length); 	//Calculate the frequency of letters throughout the cipher
			if	(letr > highest) {		//Track the most frequent letter is most likely to be the letter 'E'
				highest = letr;
				positionE = i + 1;
			}
		}
		key = 5 - positionE;
		if	(key < 0)
			key = -key;
		
		System.out.println("Key Option 1: " + key); 	//Test Case: Key Option 1: 4
		
		key = positionE - 5;
		if	(key < 0)
			key = key + 26;
		
		System.out.println("Key Option 2: " + key);		//Test Case: Key Option 2: 22
	}
}
