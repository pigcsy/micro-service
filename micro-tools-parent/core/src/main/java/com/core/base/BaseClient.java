package com.core.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public interface BaseClient extends Serializable {
    public Logger logger = LoggerFactory.getLogger(BaseClient.class);

}