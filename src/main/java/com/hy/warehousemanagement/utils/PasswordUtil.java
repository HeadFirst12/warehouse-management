package com.hy.warehousemanagement.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author heyin
 */
public class PasswordUtil {

    /**
     * Base64加密
     * @param str 加密字符串
     * @return 加密结果字符串
     */
    public static String base64EncodeStr(String str) throws UnsupportedEncodingException {
        if(StringUtils.isBlank(str)) {
            throw new UnsupportedEncodingException();
        }
        String result = Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
        return result;
    }

    /**
     * Base64解密
     * @param str 解密字符串
     * @return 解密结果字符串
     */
    public static String base64DecodedStr(String str) throws UnsupportedEncodingException {
        if(StringUtils.isBlank(str)) {
            throw new UnsupportedEncodingException();
        }
        String result = new String(Base64.getDecoder().decode(str), StandardCharsets.UTF_8);
        return result;
    }

}
