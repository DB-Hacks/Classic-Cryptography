package Vigenere;

import Utility.CryptoTools;

public class Vigenere {
	public static void main(String[] args) throws Exception
	{
		byte[] pt = CryptoTools.fileToBytes("Data/PT_MSG6.txt");	//Test Case: It highlights the unique aspects of the specialized applications and protocols that ICS system operators typically use. ...
		pt = CryptoTools.clean(pt);		//Test Case: ITHIGHLIGHTSTHEUNIQUEASPECTSOFTHESPECIALIZEDAPPLICATIONSANDPROTOCOLSTHATICSSYSTEMOPERATORSTYPICALLYUSE ...
		byte[] ct = new byte[pt.length];
		byte[] bk = new byte[pt.length];
		byte[] ky = "SECURITY".getBytes();
		//--------------------------------------------------Encrypt
		for(int i = 0; i < pt.length; i++) {
			ct[i] = (byte) ((pt[i] + ky[i%8] + 26) % 26 + 'A');
		}
		
		System.out.println("CT = " + new String(ct));	//Test Case: AXJCXPEGYLVMKPXSFMSOVILNWGVMFNMFWWRYTQTJADGXRXIJAGCNZWGQSRFJIWMMUSNMKPTRAGUMPAMCESRYIIMMJWVSGQVYDPAOJM ...
		
		//--------------------------------------------------Decrypt
		for(int i = 0; i < ct.length; i++) {
			bk[i] = (byte) ((ct[i] - ky[i%8] + 26) % 26 + 'A');
		}
		
		System.out.println("BK = " + new String(bk));	//Test Case: ITHIGHLIGHTSTHEUNIQUEASPECTSOFTHESPECIALIZEDAPPLICATIONSANDPROTOCOLSTHATICSSYSTEMOPERATORSTYPICALLYUSE ...
	}
}
