package foundation;

import util.CryptoTools;

public class C_Exhaustive {
	public static void main(String[] args) throws Exception
	{
		double dot = 0;
		byte[] ct = CryptoTools.fileToBytes("C:\\Users\\Danie\\eclipse-workspace\\Labs\\EECS3481\\src\\data\\MSG2.ct");

		byte[] bk = new byte[ct.length];
		for (int i = 1; i < 26; i++) {
			for (int j = 0; j < ct.length; j++)
			{
				int tmp = (ct[j] - 'A' - i) % 26;
				if (tmp < 0) tmp+= 26;
				bk[j] = (byte) (tmp + 'A');
			}
			int [] freq = CryptoTools.getFrequencies(bk);
			for(int k = 0; k < freq.length; k++) {
				dot += ((freq[k])*(CryptoTools.ENGLISH[k]));
			}
			System.out.println("BK[" + i + "] = " + new String(bk));
			System.out.println("Dot product [" + i + "] = " + dot);
			dot = 0;
		}
		
		//---------------------------------------------- Print
		System.out.println("CT = " + new String(ct));
		System.out.println("BK = " + new String(bk));
		
		//Look for the highest dot product to find the key
		//first word is "COURSE" key is 22
	}
}
