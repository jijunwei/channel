package rst.util.base64;

import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.util.Arrays;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;  
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;  
 



import org.bouncycastle.crypto.BlockCipher;  
import org.bouncycastle.crypto.Mac;  
import org.bouncycastle.crypto.engines.DESEngine;  
import org.bouncycastle.crypto.macs.CBCBlockCipherMac;  
import org.bouncycastle.crypto.params.KeyParameter;  
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
 
public class ShfftDes {  
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    //验证用密钥  
    private static byte[]              key         = "000000000000000000000000".getBytes();  
 
    //    private byte[]              key         = Hex.decode("00000000");  
 
    private byte[]              ivs         = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 };  
 
    private static final String DES_EDE     = "DESede/ECB/NoPadding";               //定义 加密算法,可用 DES,DESede,Blowfish        //keybyte为加密密钥，长度为24字节    //src为被加密的数据缓冲区（源）  
 
    private static final String DES_EDE_CBC = "DESede/CBC/NoPadding";               //定义 加密算法,可用 DES,DESede,Blowfish        //keybyte为加密密钥，长度为24字节    //src为被加密的数据缓冲区（源）  
 
    private static final String DES_CBC     = "DES/CBC/NoPadding";  
 
    private static final String DES_ECB     = "DES/ECB/PKCS5Padding";  
 
     
    public byte[] CryptByDes(byte[] content, int mode) throws Exception {  
        Cipher cipher = Cipher.getInstance(DES_ECB);  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
        SecretKey secretKey = keyFactory.generateSecret(new DESKeySpec(key));  
        cipher.init(mode, secretKey);  
        return cipher.doFinal(content);  
    }  
 
     
    public static byte[] CryptBy3Des(byte[] content, int mode, byte[] keyx) throws Exception {  
        Cipher cipher = Cipher.getInstance(DES_EDE);  
        SecretKey secretKey = new SecretKeySpec(keyx, "DESede");  
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);  
        return cipher.doFinal(content);  
    }  
 
	public static byte[] decryptBy3Des(byte[] content, byte[] keyx) throws Exception{
		Cipher cipher = Cipher.getInstance(DES_EDE); 
		SecretKey secretKey = new SecretKeySpec(keyx, "DESede"); 
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		return cipher.doFinal(content);
	}  
 
     
    public byte[] CryptBy3DesCbc(byte[] content, int mode) throws Exception {  
        Cipher cipher = Cipher.getInstance(DES_EDE_CBC);  
        SecretKey secureKey = new SecretKeySpec(key, "DESede");  
        IvParameterSpec iv = new IvParameterSpec(ivs);  
        cipher.init(mode, secureKey, iv);  
        return cipher.doFinal(content);  
    }  
 
     
    public byte[] CryptByDesCbcMac(byte[] content) throws Exception {  
        BlockCipher engine = new DESEngine();  
        Mac mac = new CBCBlockCipherMac(engine, 64);  
        byte[] macText = new byte[engine.getBlockSize()];  
        mac.init(new KeyParameter(key));  
        mac.update(Padding(content, 64), 0, content.length);  
        mac.update(content, 0, content.length);  
        mac.doFinal(macText, 0);  
        return macText;  
    }  
 
     

 
    public byte[] mac(byte[] content) throws Exception {  
        int len;  
        byte plainData[];  
        byte encryptedData[];  
        len = (content.length / 8 + (content.length % 8 != 0 ? 1 : 0)) * 8;  
        plainData = new byte[len];  
        encryptedData = new byte[8];  
        Arrays.fill(plainData, (byte) 32);  
        System.arraycopy(content, 0, plainData, 0, content.length);  
        SecureRandom sr = new SecureRandom();  
        DESKeySpec dks = new DESKeySpec(key);  
        SecretKeyFactory keyFactory = null;  
        keyFactory = SecretKeyFactory.getInstance("DES");  
        SecretKey secretKey = keyFactory.generateSecret(dks);  
        Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");  
        IvParameterSpec iv = new IvParameterSpec(new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 });  
        cipher.init(1, secretKey, iv, sr);  
        System.arraycopy(cipher.doFinal(plainData), len - 8, encryptedData, 0, 8);  
        return encryptedData;  
    }  
 
     
    public byte[] Padding(byte[] content, int block) {  
        int contentLength = content.length;  
        int mod = contentLength % block;  
        if (mod != 0) {  
            int size = contentLength + block - mod;  
            //            String s = new String(content);  
            //            StringUtil.alignLeft(s, size, " ");  
            byte[] s = new byte[size];  
            System.arraycopy(content, 0, s, 0, content.length);  
            for (int i = content.length; i < size; i++) {  
                s[i] = 32;  
            }  
            return s;  
        }  
        return content;  
    }  
 
 
     
    public void println(byte[] bs) {  
        for (byte b : bs) {  
            System.out.print(b + " ");  
        }  
        logger.debug("/n");  
    }  
 
     
    public void printlnByte(byte[] bs) {  
        for (byte b : bs) {  
            if (b < 0) {  
                System.out.print((int) b + 256 + " ");  
            } else {  
                System.out.print(b + " ");  
            }  
        }  
        logger.debug("/n");  
    }  
 
     
    public void printlnByteInt16(byte[] bs) {  
        for (byte b : bs) {  
            System.out.print(Integer.toHexString((int) b) + " ");  
        }  
        logger.debug("/n");    
    }  
 
     
    public String dumpBytes(byte[] bytes) {  
        int i;  
        StringBuffer sb = new StringBuffer();  
        for (i = 0; i < bytes.length; i++) {  
            int n = bytes[i] >= 0 ? bytes[i] : 256 + bytes[i];  
            String s = Integer.toHexString(n);  
            if (s.length() < 2) {  
                s = "0" + s;  
            }  
            if (s.length() > 2) {  
                s = s.substring(s.length() - 2);  
            }  
            sb.append(s);  
        }  
        return sb.toString().toUpperCase();  
        //return new BASE64Encoder().encode(bytes);  
    }  
 
    // 一下程序将每2位16进制整数组装成一个字节  
    private String hexString = "0123456789ABCDEF";  
 
    public byte[] decode(String bytes) {  
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);  
        for (int i = 0; i < bytes.length(); i += 2)  
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes  
                .charAt(i + 1))));  
        return baos.toByteArray();  
    }  

    public byte[] getKey() {  
        return key;  
    }  
 
    public void setKey(byte[] key) {  
        this.key = key;  
    }  
 
    public byte[] getIvs() {  
        return ivs;  
    }  
 
    public void setIvs(byte[] ivs) {  
        this.ivs = ivs;  
    }  
 
} 