package com.safty.web.controller;

import com.safty.domain.request.RiskRequest;
import com.safty.manager.RiskManager;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
@RefreshScope
public class RiskController {

    @Autowired
    RiskManager riskManager;
    private Logger logger = LoggerFactory.getLogger(RiskController.class);

    @RequestMapping(value = "/risk/data/datagather", method = RequestMethod.POST)
    public void riskDataTrade(@RequestBody RiskRequest request) {
        logger.debug("收到数据{}", JSONObject.fromObject(request).toString());
        riskManager.riskDataTrade(request);
    }

}
