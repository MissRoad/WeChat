package com.bing.utils.utilstool;

import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse.Credentials;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DESUtil {

  public static final String KEY = "Lemo1234";

  public static String encrypt(String message, String key) {
    String result = "";
    try {
      Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
      IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
      // result = Base64Utils.encode(cipher.doFinal(message.getBytes("UTF-8")));
      result = toHexString(cipher.doFinal(message.getBytes("UTF-8")));
    } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
        | UnsupportedEncodingException | InvalidKeySpecException
        | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
      e.printStackTrace();
    }
    return result;
  }

  public static String encryptECB(String message, String key) {
    String result = "";
    try {
      DESKeySpec desKey = new DESKeySpec(key.getBytes());
      // 创建一个密匙工厂，然后用它把DESKeySpec转换成
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      SecretKey securekey = keyFactory.generateSecret(desKey);
      // Cipher对象实际完成加密操作
      Cipher cipher = Cipher.getInstance("DES");
      // 用密匙初始化Cipher对象
      cipher.init(Cipher.ENCRYPT_MODE, securekey);
      result = Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes("UTF-8")));
    } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
        | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException
        | InvalidKeySpecException e) {
      e.printStackTrace();
    }
    return result;
  }

  public static String decryptECB(String message, String key) {
    String result = "";
    try {
      byte[] bytesrc = Base64.getDecoder().decode(message);
      DESKeySpec desKey = new DESKeySpec(key.getBytes());
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      SecretKey securekey = keyFactory.generateSecret(desKey);
      // Cipher对象实际完成加密操作
      Cipher cipher = Cipher.getInstance("DES");
      // 用密匙初始化Cipher对象
      cipher.init(Cipher.DECRYPT_MODE, securekey);
      byte[] retByte = cipher.doFinal(bytesrc);
      result = new String(retByte);
    } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
        | InvalidKeySpecException | IllegalBlockSizeException | BadPaddingException e) {
      e.printStackTrace();
    }
    return result;
  }

  public static String decrypt(String message, String key) {
    String result = "";
    try {
      byte[] bytesrc = convertHexString(message);
      Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
      IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
      cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
      byte[] retByte = cipher.doFinal(bytesrc);
      result = new String(retByte);
    } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
        | UnsupportedEncodingException | InvalidKeySpecException
        | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
      e.printStackTrace();
    }
    return result;
  }

  public static byte[] convertHexString(String ss) {
    byte digest[] = new byte[ss.length() / 2];
    for (int i = 0; i < digest.length; i++) {
      String byteString = ss.substring(2 * i, 2 * i + 2);
      int byteValue = Integer.parseInt(byteString, 16);
      digest[i] = (byte) byteValue;
    }

    return digest;
  }

  public static String toHexString(byte b[]) {
    StringBuffer hexString = new StringBuffer();
    for (int i = 0; i < b.length; i++) {
      String plainText = Integer.toHexString(0xff & b[i]);
      if (plainText.length() < 2) {
        plainText = "0" + plainText;
      }
      hexString.append(plainText);
    }
    return hexString.toString();
  }

  /**
   * @Description Credentials加密
   * @author 韦志康
   * @date 2016年6月23日 下午3:19:09
   * @action encrypt
   */
  public static Credentials encrypt(Credentials credentials, String key) {
    String expiration = credentials.getExpiration();
    String accessKeyIdSTS = credentials.getAccessKeyId();
    String accessKeySecretSTS = credentials.getAccessKeySecret();
    String securityToken = credentials.getSecurityToken();
    String expirationDES = DESUtil.encryptECB(expiration, key);
    String accessKeyIdSTSDES = DESUtil.encryptECB(accessKeyIdSTS, key);
    String accessKeySecretSTSDES = DESUtil.encryptECB(accessKeySecretSTS, key);
    String securityTokenDES = DESUtil.encryptECB(securityToken, key);
    credentials.setExpiration(expirationDES);
    credentials.setSecurityToken(securityTokenDES);
    credentials.setAccessKeySecret(accessKeySecretSTSDES);
    credentials.setAccessKeyId(accessKeyIdSTSDES);
    return credentials;
  }

  /**
   * @Description Credentials解密
   * @author 韦志康
   * @date 2016年6月23日 下午3:18:54
   * @action decrypt
   */
  public static Credentials decrypt(Credentials credentials, String key) {
    String expiration = credentials.getExpiration();
    String accessKeyIdSTS = credentials.getAccessKeyId();
    String accessKeySecretSTS = credentials.getAccessKeySecret();
    String securityToken = credentials.getSecurityToken();
    String expirationDES = DESUtil.decryptECB(expiration, key);
    String accessKeyIdSTSDES = DESUtil.decryptECB(accessKeyIdSTS, key);
    String accessKeySecretSTSDES = DESUtil.decryptECB(accessKeySecretSTS, key);
    String securityTokenDES = DESUtil.decryptECB(securityToken, key);
    credentials.setExpiration(expirationDES);
    credentials.setSecurityToken(securityTokenDES);
    credentials.setAccessKeySecret(accessKeySecretSTSDES);
    credentials.setAccessKeyId(accessKeyIdSTSDES);
    return credentials;
  }


  public static void main(String[] args) {
    System.out.println(DESUtil.decrypt("adssadfe", KEY));
  }
}
