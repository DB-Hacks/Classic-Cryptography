package foundation;

import java.io.ByteArrayOutputStream;

import util.CryptoTools;

public class VigDecrypt
{

	public static void main(String[] args) throws Exception
	{
		byte[] ct = CryptoTools.fileToBytes("C:\\Users\\Danie\\eclipse-workspace\\Labs\\EECS3481\\src\\data\\Election.ct");
		for (int k = 1; k < 50; k++)
		{
			byte[] sample = sample(ct, k);
			double ic = CryptoTools.getIC(sample, 10000);
			System.out.printf("Key length %2d --> IC = %.3f\n", k, ic);
		}
		
		int keyLength = 13;
        byte[] pt = new byte[ct.length];
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
            System.out.println( i + " key " + shift);
            for(int j =i; j < ct.length ; j = j+keyLength) {
                pt[j] = (byte)((ct[j] - 'A' - shift +26) %26 + 'A');
            }
        }
        System.out.println(new String(pt));
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