package utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Util {
    /**
     * MD5加密
     */
    public static String md5(String str, String salt) {
        return new Md5Hash(str,salt).toString();
    }

    public static void main(String[] args) {
        System.out.println(md5("admin","java_suj"));
    }
}
