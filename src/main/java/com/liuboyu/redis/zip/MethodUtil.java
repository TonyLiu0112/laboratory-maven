package com.liuboyu.redis.zip;

import com.kiisoo.aegis.common.cache.hash.CashKeyHashMapper;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class MethodUtil {

    /**
     * 加
     */
    public static float add(Float a, Float b, int size) {
        if (a == null) a = 0f;
        if (b == null) b = 0f;

        BigDecimal p1 = new BigDecimal(Float.toString(a));
        BigDecimal p2 = new BigDecimal(Float.toString(b));
        return p1.add(p2).setScale(size, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 减
     */
    public static float subfloat(Float m1, Float m2, int size) {
        if (m1 == null) m1 = 0f;
        if (m2 == null) m2 = 0f;

        BigDecimal p1 = new BigDecimal(Float.toString(m1));
        BigDecimal p2 = new BigDecimal(Float.toString(m2));
        return p1.subtract(p2).setScale(size, BigDecimal.ROUND_HALF_UP).floatValue();
    }


    /**
     * 乘
     */
    public static float mul(Float m1, Float m2, int size) {
        if (m1 == null) m1 = 0f;
        if (m2 == null) m2 = 0f;

        BigDecimal p1 = new BigDecimal(Float.toString(m1));
        BigDecimal p2 = new BigDecimal(Float.toString(m2));
        return p1.multiply(p2).setScale(size, BigDecimal.ROUND_HALF_UP).floatValue();
    }


    /**
     * 除
     */
    public static Float div(Float m1, float m2, int scale, int size) {
        if (scale < 0) {
            return null;
        }
        BigDecimal p1 = new BigDecimal(Float.toString(m1));
        BigDecimal p2 = new BigDecimal(Float.toString(m2));
        return p1.divide(p2, scale, BigDecimal.ROUND_HALF_UP).floatValue();
    }


    /**
     * 加
     */
    public static BigDecimal addB(BigDecimal a, Float b, int size) {
        if (a == null) a = new BigDecimal(0);
        if (b == null) b = 0f;

        BigDecimal p2 = new BigDecimal(Float.toString(b));
        return a.add(p2).setScale(size, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 减
     */
    public static BigDecimal subB(BigDecimal m1, Float m2, int size) {
        if (m1 == null) m1 = new BigDecimal(0);
        if (m2 == null) m2 = 0f;

        BigDecimal p2 = new BigDecimal(Float.toString(m2));
        return m1.subtract(p2).setScale(size, BigDecimal.ROUND_HALF_UP);
    }


    /**
     * 乘
     */
    public static BigDecimal mulB(BigDecimal m1, Float m2, int size) {
        if (m1 == null) m1 = new BigDecimal(0);
        if (m2 == null) m2 = 0f;

        BigDecimal p2 = new BigDecimal(Float.toString(m2));
        return m1.multiply(p2).setScale(size, BigDecimal.ROUND_HALF_UP);
    }


    /**
     * 除
     */
    public static BigDecimal divB(BigDecimal m1, float m2, int scale, int size) {
        if (scale < 0) {
            return null;
        }
        BigDecimal p2 = new BigDecimal(Float.toString(m2));
        return m1.divide(p2, scale, BigDecimal.ROUND_HALF_UP);
    }

    private static int bit = 28;

    /**
     * 加密
     */
    public static String getBucketId(String value) throws Exception {
        return CashKeyHashMapper.getBucketId(value, bit);
    }

    /**
     * 返回值处理
     */
    public static long changeResult(String value) {
        long respos = 0;
        if (StringUtils.isNotBlank(value)) {
            respos = NumberUtils.toLong(value);
        }
        return respos;
    }


    /**
     * 将值拼接成字符串
     */
    public static String dataToString(Object... data) {
        StringBuffer sb = new StringBuffer();
        for (Object object : data) {
            if (object == null) {
                sb.append("0");
            } else {
                sb.append(object.toString());
            }
            sb.append("_");
        }
        return sb.toString();
    }

    /**
     * 比较两个数据是否相等，相等返回true，不等反回flase
     */
    public static boolean compareData(String lastData, String thisData) {
        boolean eq = false;
        if (thisData.equals(lastData)) {
            //数据相等，则相等
            eq = true;
        }

        return eq;
    }


}
