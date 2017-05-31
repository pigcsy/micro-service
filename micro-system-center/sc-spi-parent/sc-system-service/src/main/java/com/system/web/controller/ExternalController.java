package com.system.web.controller;

import com.core.base.AbstractController;
import com.core.support.web.ResponseJson;
import com.system.domain.DefaultRequestDto;
import com.system.domain.request.external.ExternalListRequestDto;
import com.system.domain.request.external.ExternalRequestDto;
import com.system.domain.response.external.ExternalListResponseDto;
import com.system.domain.response.external.ExternalResult;
import com.system.service.ExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping(value = "/external", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ExternalController extends AbstractController {

    private static final long serialVersionUID = -1847490836635164716L;

    @Autowired
    ExternalService externalService;


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    ExternalListResponseDto list(@RequestBody ExternalListRequestDto request) {
        return externalService.list(request);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    ExternalResult detail(@RequestBody DefaultRequestDto request) {
        return externalService.detail(request);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    void edit(@RequestBody ExternalRequestDto request) {
        externalService.edit(request);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    @ResponseJson
    void delete(@RequestBody DefaultRequestDto request) {
        externalService.delete(request);
    }

}
