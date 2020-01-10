package Utility;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CryptoTools
{
	//English language frequency
	public static final double[] ENGLISH = 
{8.12,1.49,2.71,4.32,12.02,2.3,2.03,5.92,7.31,0.1,0.69,3.98,2.61,6.95,7.68,1.82,0.11,6.02,6.28,9.1,2.88,1.11,2.09,0.17,2.11,0.07};
	
	//Empty to prevent instantiation
	private CryptoTools()
	{
	}

	//Clean away all the non-letters from a byte array and return a new byte array with letters all in upper-case.
	public static byte[] clean(byte[] in)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		for (int i = 0; i < in.length; i++)
		{
			char c = (char) (in[i] & ~32);
			if (c >= 'A' && c <= 'Z')
				bos.write(c);
		}
		return bos.toByteArray();
	}
	
	//Read the content of a given file and return it as an array of bytes.
	public static byte[] fileToBytes(String filename) throws Exception
	{
		FileInputStream fis = new FileInputStream(filename);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte b;
		do
		{
			b = (byte) fis.read();
			if (b != -1) bos.write(b);
		} while (b != -1);
		fis.close();
		return bos.toByteArray();
	}

	//Write the bytes in a given byte array to a file of a specified name.
	public static void bytesToFile(byte[] data, String filename) throws Exception
	{
		FileOutputStream fos = new FileOutputStream(filename);
		fos.write(data);
		fos.close();
	}

	//Compute the frequencies of letters in a byte array.
	public static int[] getFrequencies(byte[] ar)
	{
		int[] freq = new int[26];
		for (int i = 0; i < ar.length; i++)
		{
			freq[ar[i] - 'A']++;
		}
		return freq;
	}

	//Compute the Index of Coincidence of the given array.
	public static double getIC(byte[] ar, double trials)
	{
		byte[] pt = CryptoTools.clean(ar);

		int count = 0;
		int i;
		for (i = 1; i <= trials; i++)
		{
			int pos2;
			int pos1 = (int) (pt.length * Math.random());
			do
			{
				pos2 = (int) (pt.length * Math.random());
			} while (pos2 == pos1);
			if (pt[pos1] == pt[pos2]) count++;
		}
		return (count / (double) i);
	}
}