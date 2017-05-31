package com.core.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public abstract class AbstractController implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public Logger logger = LoggerFactory.getLogger(this.getClass());
}
