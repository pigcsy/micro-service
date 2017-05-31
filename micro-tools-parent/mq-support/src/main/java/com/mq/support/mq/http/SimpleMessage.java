package com.mq.support.mq.http;

import lombok.Data;

@Data
public class SimpleMessage {
    private String body;
    private String msgId;
    private String bornTime;
    private String msgHandle;
    private int reconsumeTimes;
    private String tag;


    @Override
    public String toString() {
        return "SimpleMessage [body=" + body + ", msgId=" + msgId + ", bornTime=" + bornTime + ", msgHandle="
                + msgHandle + ", reconsumeTimes=" + reconsumeTimes + ", tag=" + tag + "]";
    }

}
