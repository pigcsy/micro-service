package com.system.service;

import com.core.base.AbstractService;
import com.core.base.BaseRepository;
import com.core.exception.LogicException;
import com.core.utils.Random;
import com.core.utils.Regular;
import com.system.domain.DefaultRequestDto;
import com.system.domain.entity.OauthClientDetailsEntity;
import com.system.domain.entity.OauthSystemEntity;
import com.system.domain.request.external.ExternalListRequestDto;
import com.system.domain.request.external.ExternalRequestDto;
import com.system.domain.response.external.ExternalListResponseDto;
import com.system.domain.response.external.ExternalResult;
import com.system.repositories.OathSystemRepository;
import com.system.repositories.OauthClientDetailsRepository;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author maven
 * @date 2017/2/23 上午9:50
 * <pre>
 *                     _oo0oo_
 *                    o8888888o
 *                    88" . "88
 *                    (| -_- |)
 *                    0\  =  /0
 *                  ___/`---'\___
 *                .' \\|     |// '.
 *               / \\|||  :  |||// \
 *              / _||||| -:- |||||- \
 *             |   | \\\  -  /// |   |
 *             | \_|  ''\---/''  |_/ |
 *             \  .-\__  '-'  ___/-. /
 *           ___'. .'  /--.--\  `. .'___
 *        ."" '<  `.___\_<|>_/___.' >' "".
 *       | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *       \  \ `_.   \_ __\ /__ _/   .-` /  /
 *   =====`-.____`.___ \_____/___.-`___.-'=====
 *                     `=---='
 *   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *             佛祖开光         永无BUG
 * </pre>
 */
@Service
public class ExternalService extends AbstractService<OauthSystemEntity, Integer> {

    @Autowired
    OathSystemRepository oathSystemRepository;

    @Autowired
    OauthClientDetailsRepository oauthClientDetailsRepository;


    @Override
    protected BaseRepository getRepository() {
        return oathSystemRepository;
    }


    /**
     * 用户列表 list:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public ExternalListResponseDto list(ExternalListRequestDto request) {
        List<String> whereClause = new ArrayList<>();
        ExternalListResponseDto externalListResponseDto = new ExternalListResponseDto();
        Pageable pageable = new PageRequest(request.getCurrentPage() - 1, request.getPageSize());
        List<Object> params = Lists.newArrayList();
        String sql = "SELECT os.system_id AS systemId, os.system_name AS systemName, os.system_remark AS systemRemark,os.system_type AS systemType,ocd.access_token_validity AS accessTokenValidity, ocd.client_id AS clientId, ocd.client_secret AS clientSecret,ocd.refresh_token_validity AS refreshTokenValidity,ocd.scope AS scope FROM oauth_system os LEFT JOIN oauth_client_details ocd ON os.client_id= ocd.client_id ";
        String countSql = "select count(1) FROM oauth_system os LEFT JOIN oauth_client_details ocd ON os.client_id = ocd.client_id";
        if (request.getSystemId() != null) {
            params.add(request.getSystemId());
            whereClause.add("and os.system_id=?" + params.size());
        }
        String sbSql = Regular.SplicedSql(sql, whereClause);
        String sbCountSql = Regular.SplicedSql(countSql, whereClause);
        Page<ExternalResult> page = oathSystemRepository.findByNative(sbSql, sbCountSql, params, pageable, ExternalResult.class); //userRepository.findByNative()
        externalListResponseDto.setCurrentPage(page.getNumber());
        externalListResponseDto.setNextPage(page.hasNext());
        externalListResponseDto.setList(page.getContent());
        externalListResponseDto.setTotal(page.getTotalElements());
        externalListResponseDto.setTotalPage(page.getTotalPages());
        return externalListResponseDto;
    }


    /**
     * 用户详情 detail:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public ExternalResult detail(DefaultRequestDto request) {
        List<ExternalResult> externalResultList = new ArrayList<>();
        ExternalResult externalResult = new ExternalResult();
        List<Object> params = Lists.newArrayList();
        params.add(request.getEntityId());
        List<ExternalResult> oauthSystemEntityList = this.findByNative("SELECT os.system_id AS systemId, os.system_name AS systemName, os.system_remark AS systemRemark,os.system_type AS systemType,ocd.access_token_validity AS accessTokenValidity, ocd.client_id AS clientId, ocd.client_secret AS clientSecret,ocd.refresh_token_validity AS refreshTokenValidity,ocd.scope AS scope FROM oauth_system os LEFT JOIN oauth_client_details ocd ON os.client_id= ocd.client_id where os.system_id=?1", params, ExternalResult.class);
        if (CollectionUtils.isNotEmpty(oauthSystemEntityList)) {
            for (ExternalResult externalResultEntity : oauthSystemEntityList) {
                externalResult.setSystemId(externalResultEntity.getSystemId());
                externalResult.setSystemName(externalResultEntity.getSystemName());
                externalResult.setSystemRemark(externalResultEntity.getSystemRemark());
                externalResult.setSystemType(externalResultEntity.getSystemType());
                externalResult.setAccessTokenValidity(externalResultEntity.getAccessTokenValidity());
                externalResult.setClientId(externalResultEntity.getClientId());
                externalResult.setClientSecret(externalResultEntity.getClientSecret());
                externalResult.setRefreshTokenValidity(externalResultEntity.getRefreshTokenValidity());
                externalResult.setScope(externalResultEntity.getScope());
            }
        } else {
            throw new LogicException("未查到数据");
        }
        return externalResult;
    }

    /**
     * 用户编辑或新增 editOrInsertUser:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public void edit(ExternalRequestDto request) {
        if (request.getSystemId() != null) {
            String clientId = Random.getRandomString(10);
            OauthSystemEntity oauthSystemEntity = new OauthSystemEntity();
            oauthSystemEntity.setSystemName(request.getSystemName());
            oauthSystemEntity.setSystemId(request.getSystemId());
            oauthSystemEntity.setSystemRemark(request.getSystemRemark());
            oauthSystemEntity.setClientId(clientId);
            oauthSystemEntity.setSystemType(request.getSystemType());
            oauthSystemEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            oathSystemRepository.update(oauthSystemEntity);
            OauthClientDetailsEntity oauthClientDetailsEntity = new OauthClientDetailsEntity();
            oauthClientDetailsEntity.setClientId(request.getClientId());
            Example example = Example.of(oauthClientDetailsEntity);
            List<OauthClientDetailsEntity> oauthClientDetailsEntityList = oathSystemRepository.findAll(example);
            for (OauthClientDetailsEntity as : oauthClientDetailsEntityList) {
                oauthClientDetailsEntity.setId(as.getId());
            }
            oauthClientDetailsEntity.setAccessTokenValidity(request.getAccessTokenValidity());
            oauthClientDetailsEntity.setClientSecret(Random.getRandomString(16));
            oauthClientDetailsEntity.setRefreshTokenValidity(request.getRefreshTokenValidity());
            oauthClientDetailsEntity.setScope(request.getScope());
            oauthClientDetailsEntity.setClientId(clientId);
            oauthClientDetailsEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            oauthClientDetailsRepository.update(oauthClientDetailsEntity);
        } else {
            // 验系统名
            OauthSystemEntity oauthSystemEntity = new OauthSystemEntity();
            BeanUtils.copyProperties(request, oauthSystemEntity);
            boolean vail = checkSystem(oauthSystemEntity);
            if (!vail) {
                logger.error("系统添加重复，code:" + request.getSystemId());
                throw new LogicException("系统名称重复");
            } else {
                String clientId = Random.getRandomString(10);
                OauthSystemEntity oauthSystemEntitys = new OauthSystemEntity();
                oauthSystemEntitys.setSystemName(request.getSystemName());
                oauthSystemEntitys.setSystemRemark(request.getSystemRemark());
                oauthSystemEntitys.setSystemType(request.getSystemType());
                oauthSystemEntitys.setClientId(clientId);
                oauthSystemEntitys.setClientId(clientId);
                oauthSystemEntitys.setCreateTime(new Timestamp(System.currentTimeMillis()));
                oathSystemRepository.save(oauthSystemEntitys);
                OauthClientDetailsEntity oauthClientDetailsEntitys = new OauthClientDetailsEntity();
                oauthClientDetailsEntitys.setAccessTokenValidity(request.getAccessTokenValidity());
                oauthClientDetailsEntitys.setClientSecret(Random.getRandomString(16));
                oauthClientDetailsEntitys.setClientId(clientId);
                oauthClientDetailsEntitys.setRefreshTokenValidity(request.getRefreshTokenValidity());
                oauthClientDetailsEntitys.setScope(request.getScope());
                oauthClientDetailsEntitys.setCreateTime(new Timestamp(System.currentTimeMillis()));
                oauthClientDetailsRepository.save(oauthClientDetailsEntitys);
            }
        }
    }

    public boolean checkSystem(OauthSystemEntity oauthSystemEntity) {
        OauthSystemEntity oauthSystem = new OauthSystemEntity();
        oauthSystem.setSystemName(oauthSystemEntity.getSystemName());
        Example example = Example.of(oauthSystem);
        List<OauthSystemEntity> oauthSystemEntityList = oathSystemRepository.findAll(example);
        if (CollectionUtils.isEmpty(oauthSystemEntityList))
            return true;
        if (oauthSystemEntity.getSystemId() == null)
            return false;
        for (OauthSystemEntity as : oauthSystemEntityList) {
            if (!oauthSystemEntity.getSystemId().equals(as.getSystemId())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 用户限制 disable:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public void delete(DefaultRequestDto request) {
        if (request == null) {
            throw new LogicException("系统id未取得");
        }
        OauthSystemEntity result = oathSystemRepository.findOne(request.getEntityId());
        if (result == null) {
            throw new LogicException("系统已删除，请刷新页面。");
        }
        oathSystemRepository.delete(request.getEntityId());
        List<Object> params = Lists.newArrayList();
        params.add(request.getEntityId());
        List<ExternalResult> oauthSystemEntityList = this.findByNative("SELECT ocd.client_id AS clientId FROM oauth_system os LEFT JOIN oauth_client_details ocd ON os.client_id= ocd.client_id where os.system_id=?1", params, ExternalResult.class);
        if (CollectionUtils.isNotEmpty(oauthSystemEntityList)) {
            for (ExternalResult externalResultEntity : oauthSystemEntityList) {
                oauthClientDetailsRepository.delete(externalResultEntity.getClientId());
            }
        }
    }


}
