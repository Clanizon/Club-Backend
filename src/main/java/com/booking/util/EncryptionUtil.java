package com.booking.util;

import java.io.ByteArrayOutputStream;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {
    
    public  String encryptCallBack(String key, String str_resp)
	{
			    
			ByteArrayOutputStream baos = new ByteArrayOutputStream(key.length() / 2);
				
		    for (int i = 0; i < key.length(); i += 2) {
		        String output = key.substring(i, i + 2);	       
		        int decimal = Integer.parseInt(output, 16);	        
		        baos.write(decimal);
		    }
			    
				
		    try {
				SecretKeySpec skeySpec = new SecretKeySpec(baos.toByteArray(), "AES");
			      
			    byte [] iv1 = new byte [] {(byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A, (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07,0x72, 0x6F, 0x5A};
				AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv1);

			    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			    cipher.init(1, skeySpec,paramSpec);
			     

			    byte[] encrypted = cipher.doFinal(str_resp.getBytes("UTF-8"));
			    
			    ByteArrayOutputStream os = new ByteArrayOutputStream();
			    os.write(iv1);
			    os.write(encrypted);
			   byte[] encryptedWithIV = os.toByteArray();
			 
			    //return new String(Base64.encode(os.toByteArray()));
			    String encryptedResult = Base64.getEncoder().encodeToString(encryptedWithIV);
			    return encryptedResult;
			} 
		    catch (Exception ex) 
		    {
			   ex.printStackTrace();
			}

			return null;
	}
			
	public String decryptCallBack(String key, String encrypted)
	{
			    
			ByteArrayOutputStream baos = new ByteArrayOutputStream(key.length() / 2);
					
			for (int i = 0; i < key.length(); i += 2) 
			{
				String output = key.substring(i, i + 2);	       
				int decimal = Integer.parseInt(output, 16);	        
				baos.write(decimal);
			}
				    
			try
			{
			    SecretKeySpec skeySpec = new SecretKeySpec(baos.toByteArray(), "AES");
			    
			    //byte[] encryptedIVandTextAsBytes = Base64.decode(encrypted);
			    byte[] encryptedIVandTextAsBytes = Base64.getDecoder().decode(encrypted);
			    byte[] iv = Arrays.copyOf(encryptedIVandTextAsBytes, 16);
			    byte[] ciphertextByte = Arrays.copyOfRange(encryptedIVandTextAsBytes, 16, encryptedIVandTextAsBytes.length);
			    
			    
			    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			    cipher.init(2, skeySpec, new IvParameterSpec(iv));
			    byte[] decryptedTextBytes = cipher.doFinal(ciphertextByte);	
			    
			    String original = new String(decryptedTextBytes, "UTF-8");

			    return original;
			}
			catch (Exception ex) 
			{
			     ex.printStackTrace();
			}

			return null;
	}
    
	/*
	 * public static void main(String args[]) {
	 * 
	 * EncryptionUtil m = new EncryptionUtil();
	 * 
	 * final String keyAsHexString = "7605FC85651F6330E4FA2770D04711D7"; String
	 * plainText =
	 * "{ \"mobileNumber\": \"483249c1d0e10d0762ff0ec55365a0f79e*****2f1f1ab08f2b2c2a70b7aaa3b\" }"
	 * ;
	 * 
	 * String encrptedString = m.encryptCallBack(keyAsHexString,"plainText");
	 * 
	 * System.out.println("Encrypted data = " + encrptedString);
	 * 
	 * String decrypt = m.decryptCallBack(keyAsHexString,
	 * "pN2FB5V7bjoO0mC3+7Pb/lYPpcxPS3Orj62ThA/hdl2UoocsWwFnS1aKA4jQhKF2hrzEzMJjzzWxilkWmb6tFtCYOl/Y0v6rTqkmtv1OICNshQ5Y+IIjucuzm0YD3aWp2qiIN8lEIfT3/p6aKLFN4eRux5Rf70hbq7S+zwrH58OrCBQon+jINUwVVBZB3VGfusbFigUhmX2pyT9eo0vlRnZvepWiSBhptMB5Cm0FZIBBEhHJQ03IFTrz3CEAypfb/pb4nXTaI/rpilse6ERwh00cNOP8X7Q4/QHEYGCAx2W9BhkCRdMzyRgLFonpO3YiXNDo1zsu6TwrHli6Y/TUldiwYfqaRxEpvllYcXzTGubs7o27wM76PyIfz5+rA4upmSeQkTYkFBaPkPti0vUQuTVZgkss+PwKpKkHc40OVbPBS/kOSsFrWkVWmjNKqKakXnnlPBBOoUZOZbZUyZ468caRBIbFrTB4lvx6XHmh8E8T0GJR3dryv2CiVJdL3r6ufSFhRoM7iaGl1cw06I9uBnIbFnZ8eikmfrLU2Gj9AoQ7JFPpf1G4aJXfy190Jwfsi0xXSaPnaswJqdeV063Df3J5JjZDfhPBza8+fYKhDpDM18gNoMrTeQEgKRZESaRsBL5vIicgawbQOzqe5Y6Abc2fjEnQSF14YD4VQN7xxnPMX1+chjk6RJld/DGfct4sIZXu26fSAj/jbGiAQmRpRA=="
	 * ); System.out.println("Decrypted data = " + decrypt);
	 * 
	 * }
	 */
}
