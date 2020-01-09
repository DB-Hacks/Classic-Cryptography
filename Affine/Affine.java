package Affine;

import java.math.BigInteger;
import Utility.CryptoTools;

public class Affine {
	public static void main(String[] args) throws Exception
	{
		byte[] pt = CryptoTools.fileToBytes("Data/PT_MSG4.txt");	//Test Case: The attack closely resembles the modus operandi of known Iranian state-sponsored hackers. ...
		pt = CryptoTools.clean(pt);		//Test Case: THEATTACKCLOSELYRESEMBLESTHEMODUSOPERANDIOFKNOWNIRANIANSTATESPONSOREDHACKERS ...
		byte[] ct = new byte[pt.length];
		byte[] bk = new byte[pt.length];
		int alphaKey = 7;
		int betaKey = 16;
		BigInteger alphabet = new BigInteger("26");
		//------------------------------------------------------------------Encrypt
		for(int i = 0; i < pt.length; i++) {
			ct[i] = (byte)(((alphaKey*(pt[i] - 'A')) + betaKey) %26 + 'A');
		}
		
		System.out.println("CT = " + new String(ct));		//Test Case: KARFKKFLJLMVHRMZERHRPIMRHKARPVONHVYREFSODVUJSVTSDEFSDFSHKFKRHYVSHVEROAFLJREH ...
		
		//------------------------------------------------------------------Decrypt
		for(int i = 0; i < ct.length; i++) {
			bk[i] = (byte)((ct[i] - 'A' - betaKey + 26)*(BigInteger.valueOf(alphaKey).modInverse(alphabet).intValue()) %26 + 'A');
		}
		
		System.out.println("BK = " + new String(bk));		//Test Case: THEATTACKCLOSELYRESEMBLESTHEMODUSOPERANDIOFKNOWNIRANIANSTATESPONSOREDHACKERS ...
	}
}
