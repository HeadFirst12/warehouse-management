package com.hy.warehousemanagement.utils;

import com.hy.warehousemanagement.exception.WarehouseException;
import com.hy.warehousemanagement.model.SystemErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 生成订单号工具类
 *
 * @author hy
 */
public class CreatOrderIdUtil {

    private CreatOrderIdUtil() {

    }

    private static final Integer TIME_LEN = 18;

    /**
     * 不间断时间格式
     */
    public static final String NONSTOP_DATE_FORMAT = "yyyyMMddHHMM";

    public static String creatOrderId(String prefix, Integer length) {
        //格式化当前时间
        SimpleDateFormat sfDate = new SimpleDateFormat(NONSTOP_DATE_FORMAT);
        String strDate = sfDate.format(new Date());

        //生成长度小于时间长度，拒绝
        if (length < TIME_LEN) {
            throw new WarehouseException(SystemErrorCodeEnum.CREAT_ORDER_ID_ERROR);
        }

        //根据需要的长度不足长度
        String random = getRandomNumber(length - (prefix + strDate).length());

        //最后得到20位订单编号。
        return prefix + strDate + random;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < 100; i++) {
            String e = creatOrderId("E", 20);
            if(map.containsKey(e)) {
                count++;
            }
            map.put(e,"1");
        }
        System.out.println(count);
    }

    /**
     * 获取1-8位的随机位数数字
     *
     * @param length 想要生成的长度
     * @return result
     */
    public static String getRandomNumber(int length) {
        //小于0直接抛异常
        if (length < 0 || length > 8) {
            throw new WarehouseException(SystemErrorCodeEnum.CREAT_ORDER_ID_ERROR);
        }
        double v = Math.random() * 9 + 1;
        double pow = Math.pow(10, length - 1);
        int rs = (int) (v * pow);
        return String.valueOf(rs);
    }

    /**
     * 生成库存编码
     */
    public static String getGoodsId() {
        String str = UUID.randomUUID().toString();
        str = str.replace("-", "");
        str = str.toUpperCase();
        str = StringUtils.substring(str, 0, 22);
        return str;
    }
}
