
package com.core.support.web.domain;


import com.core.constants.AppConstant;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class BasePageRequest extends BaseRequest {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int currentPage = 0; // 当前页数

    private int pageSize = AppConstant.DEFAULT_PAGE_SIZE; // 每页显示记录的条数

    public BasePageRequest(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }
}
