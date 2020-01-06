package foundation;

import util.CryptoTools;
import java.math.BigInteger;

public class A_Exhaustive {
	public static void main(String[] args) throws Exception
	{
		byte[] ct = CryptoTools.fileToBytes("C:\\Users\\Danie\\eclipse-workspace\\Labs\\EECS3481\\src\\data\\MSG3.ct");
		byte[] pt = new byte[ct.length];
		byte[] bk = new byte[ct.length];
		int[] freq;
		int dot = 0;
		int maxDot = 0;
		
		int alpha = 0;
		int beta = 0;
		int maxAlpha = 0;
		int maxBeta = 0;
		BigInteger alphabet;
		alphabet = new BigInteger("26");
		for(int a = 1; a < 26; a++) {
			for(int b = 0; b < 26; b++) {
				for(int i = 0; i < ct.length; i++) {
					if(alphabet.gcd(BigInteger.valueOf(a)).intValue() == 1) {
						pt[i] = (byte)(BigInteger.valueOf(a).modInverse(alphabet).intValue() *(ct[i] - 'A' - b + 26) %26 +'A');
					}
				}
				alpha = a;
				beta = b;
				freq = CryptoTools.getFrequencies(pt);
				for(int j = 0; j < 26; j++) {
					dot += freq[j]*CryptoTools.ENGLISH[j];
				}
				if(dot > maxDot) {
					maxDot = dot;
					maxAlpha = alpha;
					maxBeta = beta;
				}
				System.out.println("Alpha key: " + alpha + " - Beta key: " + beta + " - Dot Product: " + dot);
				dot = 0;
			}
		}
		System.out.println("Correct - Alpha key: " + maxAlpha + " - Beta key: " + maxBeta + " - Dot Product: " + maxDot);
		//-------------Decrypt-----------
		int alphaKey = 5;
		int betaKey = 14;
		for(int i = 0; i < ct.length; i++) {
			bk[i] = (byte)((ct[i] - 'A' - betaKey + 26)*(BigInteger.valueOf(alphaKey).modInverse(alphabet).intValue()) %26 + 'A');
		}
		System.out.println(new String(bk));
		
		//first word Election
	}
}
