package com.system.repositories;

import com.core.base.BaseRepository;
import com.system.domain.entity.AdminDepartEntity;

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
public interface DepartRepository extends BaseRepository<AdminDepartEntity, Integer> {


    // @Transactional
    // @Modifying
    // @Query(value = "SELECT a.staff_id AS staffId,a.code AS code,a.pwd AS pwd,a.staff_roster AS staffRoster,a.staff_name AS staffName,a.depart_id AS departId,a.staff_post AS staffPost,a.staff_phone AS staffPhone, a.staff_email AS staffEmail, a.ins_tm AS insTm, a.leader AS leader, a.status AS status, ad.name AS departName FROM admin_staff a LEFT JOIN admin_depart ad ON a.depart_id = ad.depart_id",nativeQuery = true)
    // public UserListResponseVo searchList(UserListRequestVo requestVo);

    // @Transactional
    // @Query(value = "SELECT a.staff_id AS staffId,a.code AS code,a.pwd AS pwd,a.staff_roster AS staffRoster,a.staff_name AS staffName,a.depart_id AS departId,a.staff_post AS staffPost,a.staff_phone AS staffPhone, a.staff_email AS staffEmail, a.ins_tm AS insTm, a.leader AS leader, a.status AS status FROM admin_staff a where a.staff_name = ?1 ", nativeQuery = true)
    // AdminStaffEntity queryByName(String staffName);

}
