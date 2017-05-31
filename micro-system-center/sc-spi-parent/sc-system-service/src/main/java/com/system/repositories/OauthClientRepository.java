package com.system.repositories;

import com.core.base.BaseRepository;
import com.system.domain.entity.OauthClientDetailsEntity;

/**
 * ${DESCRIPTION}
 *
 * @author maven
 * @date 2017/2/23 上午9:49
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
public interface OauthClientRepository extends BaseRepository<OauthClientDetailsEntity, String> {

    // @Transactional
    // @Modifying
    // @Query(value = "SELECT a.staff_id AS staffId,a.code AS code,a.pwd AS pwd,a.staff_roster AS staffRoster,a.staff_name AS staffName,a.depart_id AS departId,a.staff_post AS staffPost,a.staff_phone AS staffPhone, a.staff_email AS staffEmail, a.ins_tm AS insTm, a.leader AS leader, a.status AS status FROM admin_staff a where a.staffName = ?1 ", nativeQuery = true)
    // UserEntity queryByName(String staffName);
    //
    // @Query(value = "SELECT o.url AS url FROM admin_staff_role asr LEFT JOIN admin_role_resource arr ON asr.role_id = arr.role_id LEFT JOIN oauth_resource o ON o.resource_id = arr.resource_id WHERE asr.staff_id= ?1 ", nativeQuery = true)
    // List<Object[]> queryResourcesById(Integer staffId);
    //
    // @Query(value = "SELECT ocd.resource_ids AS resourceIds,ocd.client_id AS clientId,ocd.client_secret AS clientSecret,ocd.scope AS scope,ocd.authorized_grant_types AS authorizedGrantTypes,ocd.web_server_redirect_uri AS webServerRedirectUri,ocd.authorities AS authorities,ocd.access_token_validity AS accessTokenValidity,ocd.refresh_token_validity AS refreshTokenValidity,ocd.additional_information AS additionalInformation,ocd.autoapprove AS autoapprove,ocd.create_time AS createTime,ocd.update_time AS updateTime FROM oauth_client_details ocd  WHERE ocd.client_id= ?1 ", nativeQuery = true)
    // OauthClientDetailsResponseVo queryClientDetailsById(Integer clientId);
    //
    // @Query(value = "SELECT o.url AS url FROM oauth_system_resource osr LEFT JOIN oauth_resource o ON osr.resource_id = o.resource_id WHERE osr.system_id= ?1 ", nativeQuery = true)
    // List<Object> queryResourcesBySysId(Integer systemId);
    //
    // @Query(value = "SELECT arr.resource_id AS resourceId,arr.role_id AS roleId FROM admin_role ar left join admin_role_resource arr on arr.role_id=ar.role_id")
    // List<Object[]> queryAllRole();
    //
    // @Query(value = "SELECT osr.resource_id AS resourceId,osr.role_id AS roleId FROM oauth_system_resource osr", nativeQuery = true)
    // List<Object> querySysRole();
    //
    // @Query(value = "SELECT asr.role_id AS roleId,asr.staff_id AS staffId FROM admin_staff_role admin_staff_role WHERE asr.staff_id= ?1 ", nativeQuery = true)
    // List<Object[]> queryRoleByStaffId(Integer staffId);
}
