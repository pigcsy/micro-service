package com.core.support.web.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class BasePageResponse<T> extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private boolean nextPage;
    private List<T> list;
    private long total;
    private long totalPage;
    private long currentPage;

    public BasePageResponse() {
        super();
    }

    public BasePageResponse(boolean nextPage, long total, long totalPage, long currentPage) {
        super();
        this.nextPage = nextPage;
        this.total = total;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
    }

    public BasePageResponse(boolean nextPage, List<T> list, long total, long totalPage, long currentPage) {
        super();
        this.nextPage = nextPage;
        this.list = list;
        this.total = total;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
    }

    public boolean isNextPage() {
        return this.nextPage;
    }

    public void setNextPage(boolean nextPage) {
        this.nextPage = nextPage;
    }

}
