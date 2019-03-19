package rst.util.base64;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class des {
	public static byte[] decrypt3DES(byte byteData[], byte key[]) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		Key k = new SecretKeySpec(key, "DESede");
		Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
		cipher.init(2, k);
		return cipher.doFinal(byteData);
	}
	
	public static byte[] encrypt3DES(byte data[], byte key[]) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		Key k = new SecretKeySpec(key, "DESede");
		Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
		cipher.init(1, k);
		return cipher.doFinal(data);
	}	
}
