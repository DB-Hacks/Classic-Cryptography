package Caesar;

import Utility.CryptoTools;

public class C_Crypta {
	public static void main(String[] args) throws Exception
	{
		byte[] ct = CryptoTools.fileToBytes("Data/CT_MSG3.txt");	//Test Case: XKBTPQRAVPELTPQEXQQOXAFQFLKXIJXOHBOPLCXZLJMRQBOKBQTLOHPOBPFIFBKZBXOBKLQPLIBIVBCCBZQFSBFKABQBOJFKFKDFQPXYFIFQVQLXZZLJMIFPEJFPPFLKP ...
		byte[] bk = new byte[ct.length];	
		int[] freq = CryptoTools.getFrequencies(ct);
		double letr = 0;
		double highest = 0;
		int positionE = 0; //Where 'E' is located in the alphabet
		int key = 0;
		for(int i = 0; i < freq.length; i++) {
			letr = (double) (freq[i])/(ct.length); 	//Calculate the frequency of letters throughout the cipher
			if	(letr > highest) {		//Track the most frequent letter is most likely to be the letter 'E'
				highest = letr;
				positionE = i + 1;
			}
		}
		
		if	(positionE > 5) {
			key = -(5 - positionE);
			
			System.out.println("Key: " + key);
			
			for (int i = 0; i < ct.length; i++)
			{
				int tmp = (ct[i] - 'A' - key) % 26;
				if (tmp < 0) tmp+= 26;		
				bk[i] = (byte) (tmp + 'A');
			}
			
			System.out.println("BK: " + new String(bk));
		}
		
		else if(positionE < 5) { 	//If 'E' is before its position in the alphabet add 26 to the key to loop it around 
			key = positionE + 21;
			
			System.out.println("Key: " + key);		//Test Case: Key Option 2: 23
			
			for (int i = 0; i < ct.length; i++)
			{
				int tmp = (ct[i] - 'A' - key) % 26;
				if (tmp < 0) tmp+= 26;		
				bk[i] = (byte) (tmp + 'A');
			}
			
			System.out.println("BK: " + new String(bk)); 	//Test Case: ANEWSTUDYSHOWSTHATTRADITIONALMARKERSOFACOMPUTERNETWORKSRESILIENCEARENOTSOLELYEFFECTIVEINDETERMININGITSABILITYTOACCOMPLISHMISSIONS ...			
		}
		
		else {		//Not encrypted
			System.out.println("PT: " + new String(ct));
		}
	}
}
