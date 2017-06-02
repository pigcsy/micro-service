package com.safty.manager;

import com.core.base.AbstractManager;
import com.safty.domain.request.RiskRequest;
import com.safty.domain.response.RiskResponse;
import com.safty.provider.client.RiskClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RiskManager extends AbstractManager {


    @Autowired
    RiskClient riskClient;

    public RiskResponse riskDataTrade(RiskRequest request) {
        return riskClient.riskDataTrade(request);
    }


}
