package com.aryun.gmall.ke.commons.utils;

import com.google.common.base.Strings;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.UrlBase64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

public final class SecurityUtils {
    private static Logger log = LoggerFactory.getLogger(SecurityUtils.class);

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    public static final String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";
    public static final String DEFAULT_TRIPPLE_DES_KEY = "MATRIX_@)!*WMYQCD!";

    /**
     * AES加密算法
     */
    public static class AES {
        /**
         * 加密算法
         */
        public static final String KEY_ALGORITHM = "AES";
        /**
         * 加密算法，分组模式，填充模式
         */
        public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
        public static final int KEY_LENGTH = 256;

        public static byte[] generKey() {
            try {
                KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);
                kgen.init(KEY_LENGTH);
                SecretKey secretKey = kgen.generateKey();
                return secretKey.getEncoded();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        public static byte[] generRandomKey() {
            try {
                KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);

                SecureRandom secureRandom = new SecureRandom();
                kgen.init(secureRandom);
                SecretKey secretKey = kgen.generateKey();
                return secretKey.getEncoded();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        public static String genarateRandomKeyWithBase64() {
            return new String(Base64.encode(generRandomKey()));
        }

        /**
         * @param content 明文数据
         * @param pwd     Base64解码后的密钥
         * @return 加密数据
         */
        public static byte[] encrypt(final byte[] content, final byte[] pwd) {
            return encryptService(content, pwd, Cipher.ENCRYPT_MODE, "BC");
        }

        public static byte[] encrypt(byte[] data, byte[] key, String providerName) {
            return encryptService(data, key, Cipher.ENCRYPT_MODE, providerName);
        }

        /**
         * AES\Base64 双重加密
         *
         * @param data 待加密的数据
         * @param key  AES加密密钥
         * @return
         */
        public static String encryptToBase64(String data, String key) {
            byte[] valueByte = encrypt(data.getBytes(DEFAULT_CHARSET), key.getBytes(DEFAULT_CHARSET), null);
            return new String(Base64.encode(valueByte));
        }

        /**
         * AES密钥使用Base64加密，先解密密钥，后加密报文
         * 报文使用AES加密后，在使用Base64加密
         *
         * @param data 待加密的报文
         * @param key  Base64加密的AES密钥
         * @return
         */
        public static String encryptWithKeyBase64(String data, String key) {
            byte[] valueByte = encrypt(data.getBytes(DEFAULT_CHARSET), Base64.decode(key.getBytes()));
            return new String(Base64.encode(valueByte));
        }

        /////////////////////////////////
        //
        //          Decryption
        //

        /**
         * @param content 密文数据
         * @param pwd     Base64解码后的密钥
         * @return 明文数据
         */
        public static byte[] decrypt(final byte[] content, final byte[] pwd) {
            return encryptService(content, pwd, Cipher.DECRYPT_MODE, "BC");
        }

        /**
         * 解密
         *
         * @param data 待解密内容
         * @param key  解密密钥
         * @return
         */
        public static byte[] decrypt(byte[] data, byte[] key, String providerName) {
            return encryptService(data, key, Cipher.DECRYPT_MODE, providerName);
        }

        /**
         * Base64->AES 两步解密
         *
         * @param data 待解密的数据
         * @param key  AES的解密密钥a
         * @return
         */
        public static String decryptFromBase64(String data, String key) {
            byte[] originalData = Base64.decode(data.getBytes());
            byte[] valueByte = decrypt(originalData, key.getBytes(DEFAULT_CHARSET), null);
            return new String(valueByte, DEFAULT_CHARSET);
        }

        /**
         * AES密钥使用Base64加密，先解密密钥，后解密报文
         *
         * @param data 待解密的报文
         * @param key  Base64加密的AES密钥
         * @return
         */
        public static String decryptWithKeyBase64(String data, String key) {
            byte[] originalData = Base64.decode(data.getBytes());
            byte[] valueByte = decrypt(originalData, Base64.decode(key.getBytes()));
            return new String(valueByte, DEFAULT_CHARSET);
        }

        static Key toKey(final byte[] key) {
            return new SecretKeySpec(key, KEY_ALGORITHM);
        }

        private static byte[] encryptService(final byte[] content, final byte[] pwd, int model, String providerName) {
            Objects.requireNonNull(content, "明文数据不能为空");
            Objects.requireNonNull(pwd, "密钥信息不能为空");
            try {
                Security.addProvider(new BouncyCastleProvider());

                Cipher cipher;
                if (!Strings.isNullOrEmpty(providerName))
                    cipher = Cipher.getInstance(CIPHER_ALGORITHM, providerName);
                else
                    cipher = Cipher.getInstance(CIPHER_ALGORITHM);

                cipher.init(model, toKey(pwd));
                return cipher.doFinal(content);
            } catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException |
                    InvalidKeyException
                    | IllegalBlockSizeException | BadPaddingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class TripleDESAlgorithm {
        // 算法名称
        private static final String KEY_ALGORITHM = "desede";
        // 算法名称/加密模式/填充方式
        private static final String CIPHER_ALGORITHM = "desede/CBC/PKCS7Padding";

        private static final byte[] KEY_IV = {1, 2, 3, 4, 5, 9, 7, 8};//初始化向量

        private static final String DEFAULT_ENCODING = "UTF-8";

        /**
         * CBC加密
         *
         * @param key   密钥
         * @param keyIV IV
         * @param data  明文
         * @return Base64编码的密文
         * @throws Exception
         */
        private static byte[] des3EncodeCBC(String key, byte[] keyIV, String data) throws Exception {
            Security.addProvider(new BouncyCastleProvider());
            Key desKey = keyGenerator(key);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec ips = new IvParameterSpec(keyIV);
            cipher.init(Cipher.ENCRYPT_MODE, desKey, ips);
            return cipher.doFinal(data.getBytes(DEFAULT_ENCODING));
        }

        /**
         * CBC加密
         *
         * @param key  密钥
         * @param data 明文
         * @return Base64编码的密文
         * @throws Exception
         */
        public static String des3EncodeCBC(String key, String data) throws Exception {
            byte[] result = des3EncodeCBC(key, KEY_IV, data);
            return new String(Base64.encode(result));
        }

        /**
         * CBC加密
         *
         * @param key  密钥
         * @param data 明文
         * @return Base64编码的密文
         * @throws Exception
         */
        public static String des3EncodeCBCForUrl(String key, String data) throws Exception {
            byte[] result = des3EncodeCBC(key, KEY_IV, data);
            return new String(UrlBase64.encode(result));
        }

        /**
         * ECB加密
         *
         * @param key       密钥
         * @param plainText 明文
         * @param charset   字符编码
         * @return Base64编码后的 ECB 密文
         * @throws Exception
         */
        public static String des3EncodeECB(String key, String plainText, String charset) throws Exception {
            SecretKey secretKey = new SecretKeySpec(key.getBytes(charset), "DESede");
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(1, secretKey);
            byte[] ciphertext = cipher.doFinal(plainText.getBytes(charset));
            return new String(Base64.encode(ciphertext));
        }

        /**
         * 生成密钥key对象
         *
         * @param keyStr 密钥字符串
         * @return 密钥对象
         * @throws Exception
         */
        private static Key keyGenerator(String keyStr) throws Exception {
            byte[] input = get3DesKey(keyStr);
            DESedeKeySpec KeySpec = new DESedeKeySpec(input);
            SecretKeyFactory KeyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
            return ((Key) (KeyFactory.generateSecret(((java.security.spec.KeySpec) (KeySpec)))));
        }

        private static int parse(char c) {
            if (c >= 'a') return (c - 'a' + 10) & 0x0f;
            if (c >= 'A') return (c - 'A' + 10) & 0x0f;
            return (c - '0') & 0x0f;
        }

        // 从十六进制字符串到字节数组转换
        static byte[] HexString2Bytes(String hexStr) {
            byte[] b = new byte[hexStr.length() / 2];
            int j = 0;
            for (int i = 0; i < b.length; i++) {
                char c0 = hexStr.charAt(j++);
                char c1 = hexStr.charAt(j++);
                b[i] = (byte) ((parse(c0) << 4) | parse(c1));
            }
            return b;
        }

        /**
         * 生成24字节的3DES密钥。
         * （不够24字节，则补0；超过24字节，则取前24字节。）
         *
         * @param key 密钥字符串
         * @return
         */
        static byte[] get3DesKey(String key) {
            byte[] keyBytes = new byte[24];
            byte[] originalBytes = key.getBytes(Charset.forName(DEFAULT_ENCODING));
            if (key.getBytes().length > 24) {
                System.arraycopy(originalBytes, 0, keyBytes, 0, 24);
            } else {
                for (int i = 0; i < 24; i++) {
                    if (i < key.getBytes().length) {
                        keyBytes[i] = originalBytes[i];
                    } else {
                        keyBytes[i] = 0x00;
                    }
                }
            }
            return keyBytes;
        }

        /**
         * CBC解密
         *
         * @param key  密钥
         * @param data Base64编码的密文
         * @return 明文
         * @throws Exception
         */
        public static String des3DecodeCBC(String key, String data) throws Exception {
            byte[] result = des3DecodeCBC(key, KEY_IV, Base64.decode(data));
            return new String(result);
        }


        /**
         * CBC解密
         *
         * @param key  密钥
         * @param data Base64编码的密文
         * @return 明文
         * @throws Exception
         */
        public static String des3DecodeCBCForUrl(String key, String data) throws Exception {
            byte[] result = des3DecodeCBC(key, KEY_IV, UrlBase64.decode(data));
            return new String(result);
        }

        /**
         * CBC解密
         *
         * @param key   密钥
         * @param keyiv IV
         * @param data  Base64编码的密文
         * @return 明文
         * @throws Exception
         */
        private static byte[] des3DecodeCBC(String key, byte[] keyiv, byte[] data) throws Exception {
            Security.addProvider(new BouncyCastleProvider());
            Key desKey = keyGenerator(key);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec ips = new IvParameterSpec(keyiv);
            cipher.init(Cipher.DECRYPT_MODE, desKey, ips);
            return cipher.doFinal(data);
        }

        /**
         * ECB 解密
         */
        public static String des3DecodeECB(String key, String cipherText, String charset) throws Exception {
            byte[] decodeBase64 = Base64.decode(cipherText);
            SecretKey secretKey = new SecretKeySpec(key.getBytes(charset), "DESede");
            Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            c1.init(2, secretKey);
            byte[] bytes = c1.doFinal(decodeBase64);
            return new String(bytes, charset);
        }
    }

    public static class SHA {
        /**
         * SHA-256 algorithm
         */
        public static String sha256Digest(String input, String charset) throws Exception {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(input.getBytes(charset));
            StringBuilder sb = new StringBuilder(64);

            for (int i = 0; i < bytes.length; ++i) {
                String hex = Integer.toHexString(255 & bytes[i]);
                if (hex.length() == 1) {
                    sb.append('0');
                }

                sb.append(hex);
            }

            return sb.toString();
        }

        /**
         * HMAC_SHA256 algorithm
         */
        public static String hmacSha256Digest(String secret, String charset, String input) throws Exception{
            Mac hmacSha256 = Mac.getInstance("HmacSHA256");
            byte[] keyBytes = secret.getBytes(charset);
            hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256"));

            return new String( Base64.toBase64String(
                    hmacSha256.doFinal( input.getBytes(charset) ) ) );
        }
    }

    public static class MD5 {

        private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        private static String toHexString(byte[] b) {
            StringBuilder sb = new StringBuilder(b.length * 2);
            for (int i = 0; i < b.length; i++) {
                sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
                sb.append(HEX_DIGITS[b[i] & 0x0f]);
            }
            return sb.toString();
        }

        public static String Bit32(String SourceString) throws Exception {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(SourceString.getBytes());
            byte messageDigest[] = digest.digest();
            return toHexString(messageDigest);
        }

        public static String Bit16(String SourceString) throws Exception {
            return Bit32(SourceString).substring(8, 24);
        }

        private static String md5Base64(String original) throws Exception {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md5Val = md5.digest(original.getBytes("UTF-8"));
            return new String(Base64Utils.encode(md5Val));
        }
    }

    public static class RSA {

        /**
         * 指定key的大小
         */
        private static int KEYSIZE = 1024;

        /**
         * 加密方法 source： 源数据
         */
        public static String encrypt(String source, String publicKey)
                throws Exception {
            Key key = getPublicKey(publicKey);
            /** 得到Cipher对象来实现对源数据的RSA加密 */
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] b = source.getBytes();
            /** 执行加密操作 */
            byte[] b1 = cipher.doFinal(b);
            return new String(Base64.encode(b1), DEFAULT_CHARSET);
        }

        /**
         * 解密算法 cryptograph:密文
         */
        public static String decrypt(String cryptograph, String privateKey)
                throws Exception {
            Key key = getPrivateKey(privateKey);
            /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] b1 = Base64.decode(cryptograph.getBytes());
            /** 执行解密操作 */
            byte[] b = cipher.doFinal(b1);
            return new String(b);
        }

        /**
         * 得到公钥
         *
         * @param key 密钥字符串（经过base64编码）
         * @throws Exception
         */
        public static PublicKey getPublicKey(String key) throws Exception {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec( Base64.decode(key.getBytes()) ); // replace base64
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        }

        /**
         * 得到私钥
         *
         * @param key 密钥字符串（经过base64编码）
         * @throws Exception
         */
        public static PrivateKey getPrivateKey(String key) throws Exception {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec( Base64.decode(key.getBytes()) );
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            return privateKey;
        }

        public static String sign (String content, String privateKey) throws Exception {
            PrivateKey priKey = getPrivateKey(privateKey);

            Signature signature = Signature.getInstance("SHA1WithRSA");

            signature.initSign(priKey);
            signature.update(content.getBytes(DEFAULT_CHARSET));

            byte[] signed = signature.sign();

            return new String(Base64.encode(signed));
        }

        public static boolean checkSign(String content, String sign, String publicKey) {
            try {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");

                byte[] encodedKey = Base64.decode(publicKey);
                PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));


                Signature signature = Signature.getInstance("SHA1WithRSA");

                signature.initVerify(pubKey);
                signature.update(content.getBytes(DEFAULT_CHARSET));



                return signature.verify(Base64.decode(sign));
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            return false;
        }
    }

}
