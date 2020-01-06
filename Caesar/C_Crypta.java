package foundation;

import util.CryptoTools;

public class C_Crypta {
	public static void main(String[] args) throws Exception
	{
		byte[] ct = CryptoTools.fileToBytes("C:\\Users\\Danie\\eclipse-workspace\\Labs\\EECS3481\\src\\data\\MSG2.ct");
		int[] freq = CryptoTools.getFrequencies(ct);
		double letr = 0;
		for(int i = 0; i < freq.length; i++) {
			letr = (double) (freq[i])/(ct.length);
			System.out.println("key "+ (i+1) +":" + letr*100);
		}
		//find E highest number than subtract or add to get the shift
		//in this case we have to add 4 to shift it to E or subtract 22
		//when they originally did it they shifted it by 22
	}
}
