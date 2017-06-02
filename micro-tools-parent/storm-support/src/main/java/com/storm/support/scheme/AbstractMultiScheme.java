package com.storm.support.scheme;

import backtype.storm.spout.MultiScheme;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMultiScheme<T> implements MultiScheme {
    private static final long serialVersionUID = 1L;
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Iterable<List<Object>> deserialize(byte[] ser) {
        if (ser == null || ser.length == 0) {
            return null;
        }
        String inputStr;
        try {
            inputStr = new String(ser, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("Cannot encode byte with utf-8");
            return null;
        }
        ArrayList<List<Object>> lines = Lists.newArrayList();
        // split string
        try {
            for (String line : Splitter.on("\n").trimResults().omitEmptyStrings().split(inputStr)) {
                // logger.info("------Abstract收到消息："+line);
                Values value = buildValues(line);
                if (value != null) {
                    lines.add(value);
                }
            }
        } catch (Exception e) {
            logger.error("Cannot split the provided message!,{}", inputStr);
        }
        return lines;
    }

    public abstract Values buildValues(String input);

    public abstract Fields getOutputFields();
}
