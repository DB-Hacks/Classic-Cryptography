package foundation;

import util.CryptoTools;

public class C_Encrypt {
	public static void main(String[] args) throws Exception
	{
		int key = 19;
		byte[] raw = CryptoTools.fileToBytes("C:\\Users\\Danie\\eclipse-workspace\\Labs\\EECS3481\\src\\data\\MSG1.pt");
		byte[] clean = CryptoTools.clean(raw);
		CryptoTools.bytesToFile(clean, "C:\\Users\\Danie\\eclipse-workspace\\Labs\\EECS3481\\src\\data\\MSG1.clean");
		byte[] ct = new byte[clean.length];
		for (int i = 0; i < clean.length; i++)
		{
			ct[i] = (byte) ((clean[i] - 'A' + key) % 26 + 'A');
		}
		CryptoTools.bytesToFile(ct, "MSG1.ct");
		//---------------------------------------------- Print
		System.out.println("PT = " + new String(clean));
		System.out.println("CT = " + new String(ct));
		System.out.println("MD5 = " + new String(CryptoTools.getMD5(ct)));
	}
}
