package com.core.utils;

/**
 * Created by maven on 2017/3/29.
 * 多条件拼接sql
 */

public class Random {

    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(4);
            long result = 0;
            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append(String.valueOf((char) result));
                    break;
                case 1:
                    result = Math.round(Math.random() * 25 + 97);
                    sb.append(String.valueOf((char) result));
                    break;
                case 2:
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }

        }
        return sb.toString();
    }

    private int nextInt(int count) {
        return (int) Math.round(Math.random() * (count));
    }


}
