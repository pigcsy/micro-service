package com.mq.support.mq.tcp;
/**
 * Created by maven on 2017/3/2.
 */

import lombok.Data;

/**
 * @author Maven
 */
@Data
public class Entity<T> {
    private String topic;
    private String tag;
    private T body;
    private String type;

}
