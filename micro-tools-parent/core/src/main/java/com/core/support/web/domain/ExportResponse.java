package com.core.support.web.domain;

import lombok.Data;

@Data
public class ExportResponse extends BaseResponse {
    private String url;

    public ExportResponse() {
        super();
    }

    public ExportResponse(String url) {
        super();
        this.url = url;
    }


}
