package com.ruoyi.common.utils;


import java.util.Random;

public class KeyUtils {


    /**
     * 主键生成，系统可以容忍少量冲突，所以暂时先这样，做个简易版的
     *
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }


    public static synchronized Integer genComCode() {
        Random random = new Random();
        return random.nextInt(90000) + 10000;
    }


}
