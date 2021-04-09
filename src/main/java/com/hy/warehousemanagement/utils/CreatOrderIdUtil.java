package com.hy.warehousemanagement.utils;

import com.hy.warehousemanagement.exception.WarehouseException;
import com.hy.warehousemanagement.model.SystemErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 生成订单号工具类
 */
public class CreatOrderIdUtil {

    private final static Integer TIME_LEN = 18;

    //不间断时间格式
    public static final String NONSTOP_DATE_FORMAT = "yyyyMMddHHmmssSSS";

    public static String creatOrderId(String prefix, Integer length) {
        //格式化当前时间
        SimpleDateFormat sfDate = new SimpleDateFormat(NONSTOP_DATE_FORMAT);
        String strDate = sfDate.format(new Date());

        //length长度不足17位不能生成订单号
        if (length < 17) {
            throw new WarehouseException(SystemErrorCodeEnum.CREAT_ORDER_ID_ERROR);
        }

        //根据需要的长度不足长度
        String random = getRandom620(length - TIME_LEN);

        //最后得到20位订单编号。
        String orderId = prefix + strDate + random;

        return orderId;
    }

    /**
     * 获取6-10 的随机位数数字
     *
     * @param length 想要生成的长度
     * @return result
     */
    public static String getRandom620(Integer length) {
        String result = "";
        Random rand = new Random();
        int n = 20;
        if (null != length && length > 0) {
            n = length;
        }
        int randInt;
        for (int i = 0; i < n; i++) {
            randInt = rand.nextInt(10);

            result += randInt;
        }
        return result;
    }

    /**
     * 生成库存编码
     */
    public static String getGoodsId() {
        String str = UUID.randomUUID().toString();
        str = str.replaceAll("-", "");
        str = str.toUpperCase();
        str = StringUtils.substring(str,0,22);
        return str;
    }
}
