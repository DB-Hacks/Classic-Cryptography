package Caesar;

import Utility.CryptoTools;

public class C_Exhaustive {
	public static void main(String[] args) throws Exception
	{
		double dot = 0;
		int key = 0;
		double highest = 0;
		byte[] ct = CryptoTools.fileToBytes("Data/CT_MSG.txt");		//Test Case: NWNPXUNZZRENGGNPXVFZHPUZBERQVSSVPHYGGBQRGRPGORPNHFRBSGURSCTNFQVERPGNPPRFFGBFLFGRZERFBHEPRF ...
		byte[] bk = new byte[ct.length];
		
		//Loop through every possible key
		for (int i = 1; i < 26; i++) 
		{
			for (int j = 0; j < ct.length; j++)
			{
				int tmp = (ct[j] - 'A' - i) % 26;	//Shift each letter's position in the alphabet by the value of key 'i'
				if (tmp < 0) tmp+= 26;
				bk[j] = (byte) (tmp + 'A');
			}
			int [] freq = CryptoTools.getFrequencies(bk);
			for(int k = 0; k < freq.length; k++) 
			{
				dot += ((freq[k])*(CryptoTools.ENGLISH[k]));	//calculate the dot product of the decrypted message frequency with the English language frequency
			}
			if	(dot > highest)	{ 	//Highest dot product is the correct decryption
				highest = dot;
				key = i;
			}
			dot = 0;
		}
		
		//---------------------------------------------- Decrypt
		for (int i = 0; i < ct.length; i++)
		{
			int tmp = (ct[i] - 'A' - key) % 26;
			if (tmp < 0) tmp+= 26;		
			bk[i] = (byte) (tmp + 'A');
		}	
		System.out.println("BK = " + new String(bk)); 	//Test Case: AJACKHAMMERATTACKISMUCHMOREDIFFICULTTODETECTBECAUSEOFTHEFPGASDIRECTACCESSTOSYSTEMRESOURCES ...
		System.out.println("Key = " + key);		//Test Case: 13
	}
}
