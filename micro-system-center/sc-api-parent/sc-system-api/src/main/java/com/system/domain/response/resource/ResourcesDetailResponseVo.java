package com.system.domain.response.resource;


import com.core.support.web.domain.BaseResponse;
import lombok.Data;

import java.util.List;


/**
 * 出库列表 ClassName: OutWarehouseResponse <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author csy
 * @date: 2016年11月10日 下午1:12:54 <br/>
 * @since JDK 1.8
 */
@Data
public class ResourcesDetailResponseVo extends BaseResponse {

    private Integer resourceId;

    private Integer parent;

    private String name;

    private Byte level;

    private String url;

    private Byte sort;

    private String code;

    private Byte type;

    private String page;

    private Byte status;

    private String system;

    private String levelName;

    private String statusName;

    private String typeName;

    private List<ResourcesResponseVo> resourcesResult;


    // public static ResourcesDetailResponseVo conver(ResourcesDetailRpcResponse rpcResponse) {
    // 	ResourcesDetailResponseVo response = new ResourcesDetailResponseVo();
    // 	response.setId(rpcResponse.getId());
    // 	response.setParent(rpcResponse.getParent());
    // 	response.setName(rpcResponse.getName());
    // 	response.setLevel(rpcResponse.getLevel());
    // 	response.setUrl(rpcResponse.getUrl());
    // 	response.setSort(rpcResponse.getSort());
    // 	response.setCode(rpcResponse.getCode());
    // 	response.setType(rpcResponse.getType());
    // 	response.setPage(rpcResponse.getPage());
    // 	response.setStatus(rpcResponse.getStatus());
    // 	response.setSystem(rpcResponse.getSystem());
    // 	response.setResourcesResult(rpcResponse.getResourcesResult());
    // 	response.setLevelName(rpcResponse.getLevelName());
    // 	response.setStatusName(rpcResponse.getStatusName());
    // 	response.setTypeName(rpcResponse.getTypeName());
    // 	return response;
    // }
}
