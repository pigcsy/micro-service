package com.core.utils;/**
 * Created by maven on 2017/3/2.
 */

import com.google.common.io.Closeables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * json工具
 *
 * @author Maven
 *         <pre>
 *                                                             _oo0oo_
 *                                                            o8888888o
 *                                                            88" . "88
 *                                                            (| -_- |)
 *                                                            0\  =  /0
 *                                                          ___/`---'\___
 *                                                        .' \\|     |// '.
 *                                                       / \\|||  :  |||// \
 *                                                      / _||||| -:- |||||- \
 *                                                     |   | \\\  -  /// |   |
 *                                                     | \_|  ''\---/''  |_/ |
 *                                                     \  .-\__  '-'  ___/-. /
 *                                                   ___'. .'  /--.--\  `. .'___
 *                                                ."" '<  `.___\_<|>_/___.' >' "".
 *                                               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *                                               \  \ `_.   \_ __\ /__ _/   .-` /  /
 *                                           =====`-.____`.___ \_____/___.-`___.-'=====
 *                                                             `=---='
 *                                           ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *                                                     佛祖开光         永无BUG
 *                                         </pre>
 */
public class JsonUtil {
    private static final Logger log = LogManager.getLogger();

    public static byte[] object2Byte(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bo = null;
        ObjectOutputStream oo = null;
        try {
            // object to byte array
            bo = new ByteArrayOutputStream();
            oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);

            bytes = bo.toByteArray();
        } catch (Exception ex) {
            log.error("object to byte array occurs exception!", ex);
        } finally {
            try {
                Closeables.close(bo, true);
            } catch (Exception ex) {
            }
            try {
                Closeables.close(oo, true);
            } catch (Exception ex) {
            }
        }
        return bytes;
    }

    public static Object byte2Object(byte[] bytes) {
        Object obj = null;
        ByteArrayOutputStream bo = null;
        ObjectOutputStream oo = null;
        try {
            // byte array to object
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);

            obj = oi.readObject();
        } catch (Exception ex) {
            log.error("bytes to object occurs exception", ex);
        } finally {
            try {
                Closeables.close(bo, true);
            } catch (Exception ex) {
            }
            try {
                Closeables.close(oo, true);
            } catch (Exception ex) {
            }
        }
        return obj;
    }
}
