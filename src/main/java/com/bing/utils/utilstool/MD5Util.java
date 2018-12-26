package com.bing.utils.utilstool;


import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

public class MD5Util {

  public static String md5(String pwd, String salt) {
    Validate.notBlank(pwd, "pwd cant't be null");
    Hasher hasher = Hashing.md5().newHasher().putBytes(pwd.getBytes());
    if (StringUtils.isNotBlank(salt)) {
      hasher.putBytes(salt.getBytes());
    }
    return hasher.hash().toString();
  }

  public static String md5(String pwd) {
    return md5(pwd, null);
  }
}
