package Caesar;

import Utility.CryptoTools;

public class Caesar
{

	public static void main(String[] args) throws Exception
	{
		int key = 3;	//A key can be any number between 1-25 which will be used to determine the shift in letters of the alphabet
		byte[] raw = CryptoTools.fileToBytes("Data/PT_MSG.txt");	//Test Case: Major US cities including Baltimore and New Orleans were both struck by ransomware attacks over the last few months. ...
		byte[] pt = CryptoTools.clean(raw);		//The raw message has to to be cleaned of non-letters in order to properly encrypt with ASCII values
		byte[] ct = new byte[pt.length];
		
		for (int i = 0; i < pt.length; i++)
		{
			ct[i] = (byte) ((pt[i] - 'A' + key) % 26 + 'A');	//Shift each letter's position in the alphabet by the value of the key
		}
		CryptoTools.bytesToFile(ct, "CT_MSG.txt");
		
		System.out.println("PT = " + new String(pt));	//Test Case: MAJORUSCITIESINCLUDINGBALTIMOREANDNEWORLEANSWEREBOTHSTRUCKBYRANSOMWAREATTACKSOVERTHELASTFEWMONTHS ...
		System.out.println("CT = " + new String(ct));	//Test Case: PDMRUXVFLWLHVLQFOXGLQJEDOWLPRUHDQGQHZRUOHDQVZHUHERWKVWUXFNEBUDQVRPZDUHDWWDFNVRYHUWKHODVWIHZPRQWKV ...
		
		//----------------------------------------------Decrypt
		byte[] bk = new byte[ct.length];
		
		for (int i = 0; i < ct.length; i++)
		{
			int tmp = (ct[i] - 'A' - key) % 26;
			if (tmp < 0) tmp+= 26;		//if the ASCII value falls below zero, 26 is added to loop it back around
			bk[i] = (byte) (tmp + 'A');
		}	
		
		System.out.println("BK = " + new String(bk)); //Test Case: MAJORUSCITIESINCLUDINGBALTIMOREANDNEWORLEANSWEREBOTHSTRUCKBYRANSOMWAREATTACKSOVERTHELASTFEWMONTHS ...
	}
}