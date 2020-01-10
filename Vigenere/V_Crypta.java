package Vigenere;

import Utility.CryptoTools;
import java.io.ByteArrayOutputStream;

public class V_Crypta {
	public static void main(String[] args) throws Exception
	{
		byte[] ct = CryptoTools.fileToBytes("Data/CT_MSG7.txt");	//Test Case: PLBPFRTHVCEHFQYOBGIZFBYVYWIXPRNWEELETLRUWTUEEVERBBJFHVQLTJCE ...
		for (int k = 1; k < 50; k++)
		{
			byte[] sample = sample(ct, k);
			double ic = CryptoTools.getIC(sample, 10000);
			System.out.printf("Key length %2d --> IC = %.3f\n", k, ic);
		}
		/*keyLength is found using the loop above, search for a repeating pattern to find the length
		 Test Case:
		 Key length  9 --> IC = 0.067
		 Key length 18 --> IC = 0.063
		 Key length 27 --> IC = 0.071
		 Key length 36 --> IC = 0.070
		 Key length 45 --> IC = 0.067
		 */
		int keyLength = 9;
        byte[] pt = new byte[ct.length];
        byte[] key = new byte[keyLength];
        for(int i =0; i < keyLength; i++) {
            int[] test = new int[26];
            for(int j =i; j < ct.length ; j = j+keyLength) {
                test[ct[j] -'A']++;
            }
            int max = 0;
    		int maxIn = 0;
            for(int m = 0; m < test.length; m++) {
            	if(test[m] > max) {
            		max = test[m];
            		maxIn = m;
            	}
            }
            int shift = (maxIn - 4 + 26) % 26;
            if(i < keyLength)
            	key[i] = (byte) (shift + 'A');
            for(int j =i; j < ct.length ; j = j+keyLength) {
                pt[j] = (byte)((ct[j] - 'A' - shift +26) %26 + 'A');
            }
        }
        
        System.out.println("Key = " + new String(key));		//Test Case: ELABORATE
        System.out.println("PT = " + new String(pt));		//Test Case: LABORATORYTHECHOICEOFAKEYDETERMINESAPARTICULARTRANSFORMATION ...
	}
	
	public static byte[] sample(byte[] ar, int skip)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		for (int i = 0; i < ar.length; i = i + skip)
		{
			baos.write(ar[i]);
		}
		return baos.toByteArray();
	}
}
