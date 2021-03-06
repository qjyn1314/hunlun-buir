package com.hulunbuir.parent.tool;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * Explain:加密解密器
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-08 13:28
 */
public class DateAsc {

    //自定义密钥-用于加密APPID和secrity使用的密钥
    private static final DateAsc DATA_DES = new DateAsc("ICIYUN20200108");
    //默认密钥
    private static String defaultSecretKey = "iciyun12320200108";
    //加密器
    private Cipher encryptCipher = null;

    public DateAsc() throws Exception {
        this(defaultSecretKey);
    }

    /**
     * @param secretKey 加密解密使用的密钥
     */
    public DateAsc(String secretKey) {
        Key key;
        try {
            key = getKey(secretKey.getBytes());
            encryptCipher = Cipher.getInstance("DES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            decryptCipher = Cipher.getInstance("DES");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密 (逻辑: 1. 将要加密的字符串转换为字节数组(byte array)<br/>
     *            2. 将第一步的字节数组作为输入使用加密器(Cipher)的doFinal方法进行加密, 返回字节数组<br/>
     *            3. 把加密后的字节数组转换成十六进制的字符串)<br/>
     * @param strIn 要加密的字符串
     * @return 返回加密后的十六进制字符串
     * @throws Exception
     */
    public String encrypt(String strIn) throws Exception {
        return byteArr2HexStr(encrypt(strIn.getBytes()));
    }

    public byte[] encrypt(byte[] arrB) throws Exception {
        return encryptCipher.doFinal(arrB);
    }

    /**
     * 解密 (逻辑: 1. 把加密后的十六进制字符串转换成字节数组(byte array)<br/>
     *            2. 将第一步的字节数组作为输入使用加密器(Cipher)的doFinal方法进行解密, 返回字节数组(byte array)<br/>
     *            3. 把解密后的字节数组转换成字符串)<br/>
     * @param strIn
     * @return
     * @throws Exception
     */
    public String decrypt(String strIn) throws Exception {
        return new String(decrypt(hexStr2ByteArr(strIn)));
    }

    public byte[] decrypt(byte[] arrB) throws Exception {
        return decryptCipher.doFinal(arrB);
    }

    public static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    public static byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;
        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    private Key getKey(byte[] arrBTmp) throws Exception {
        // 创建一个空的8位字节数组（默认值为0）
        byte[] arrB = new byte[8];
        // 将原始字节数组转换为8位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        // 生成密钥
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
        return key;
    }
    //解密器
    private Cipher decryptCipher = null;

    /**
     * 对字符串进行加密
     * @author wangjunming
     * @since 2020/1/8 13:36
     * @param str:
     * @return java.lang.String
     */
    public static String encryptStr(String str){
        try {
            System.out.println("加密前的字符：" + str);
            String encrypt = DATA_DES.encrypt(str);
            System.out.println("加密后的字符：" + encrypt);
            return encrypt;
        } catch (Exception e) {
            System.out.println("加密失败"+e);
            return "";
        }
    };

    /**
     * 对字符串进行解密
     * @author wangjunming
     * @since 2020/1/8 13:36
     * @param str:
     * @return java.lang.String
     */
    public static String decryptStr(String str){
        try {
            System.out.println("解密前的字符：" + str);
            String decrypt = DATA_DES.decrypt(str);
            System.out.println("解密后的字符：" + decrypt);
            return decrypt;
        } catch (Exception e) {
            System.out.println("解密失败!!!"+e);
            return "";
        }
    };

    /**
     * MD5--进行加密
     * @author wangjunming
     * @since 2020/6/11 15:52
     */
    public static String Md5Encryption(String inputEncryption) {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(inputEncryption.getBytes(StandardCharsets.UTF_8));
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
            // Convert message digest into hex value
            final StringBuilder hashText = new StringBuilder(no.toString(16));
            while (hashText.length() < 32) {
                hashText.insert(0, "0").append(hashText);
            }
            return hashText.toString();
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        //appKey+私钥(测试环境是123456,上线前由我司提供正式私钥)+时间戳字符串的MD5加密
        final String ypTest1234561611650478 = Md5Encryption("yp_test1234561611650478");
        System.out.println(ypTest1234561611650478);

    }





    /**
     * 用法实例
     */
    public void mainas(String[] args) {
        try {
//            String APPID = "wxd664398c8d7e4f6b";
//            String APPIDs = encryptStr(APPID);
//            String SECRITY = "ee33fcbf85f1f28a70a7e6c62e4c4c28";
//            String SECRITYs = encryptStr(SECRITY);
//            System.out.println("-------------");
//            String sappid = decryptStr(APPIDs);
//            String ssecrity = decryptStr(SECRITYs);

            String inputEncryption = "yonghuming"+"mima";
            final String encryption = Md5Encryption(inputEncryption);
            System.out.println(encryption);
            //ef2a4479289d43c1ba71dfb9b64aeebe

            String validationEncryption = "yonghuming"+"mimwa";
            final String validation = Md5Encryption(validationEncryption);
            System.out.println(validation);

            System.out.println("-----");
            System.out.println(encryption.equals(validation));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
