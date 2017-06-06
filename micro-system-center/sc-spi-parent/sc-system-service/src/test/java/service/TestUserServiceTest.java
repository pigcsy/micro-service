package service;

import com.system.service.impl.OauthServiceImpl;
import com.system.service.PreLoadMetadataSource;
import com.system.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sample.SampleJUnitTest;


/**
 * ${DESCRIPTION}
 *
 * @author csy
 * @date 2017/2/23 上午9:59
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
public class TestUserServiceTest extends SampleJUnitTest {
    private static Logger logger = LoggerFactory.getLogger(TestUserServiceTest.class);
    @Autowired
    private OauthServiceImpl oauthService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PreLoadMetadataSource preLoadMetadataSource;

    // @Test
    // public void testSave() throws Exception {
    //     List<User> userList = Lists.newArrayList();
    //
    //     for (int i = 0; i < 10; i++) {
    //         User entity = new User();
    //         entity.setName("csy" + i);
    //         entity.setSex(i % 2 == 0 ? "1" : "0");
    //         entity.setCompany("车蚂蚁" + i);
    //         entity.setBirthday(new Date(System.currentTimeMillis()));
    //         entity.setJob("java开发" + i);
    //         entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
    //
    //         userList.add(entity);
    //     }
    //
    //     List<User> result = testUserService.save(userList);
    //
    //     logger.info("**** 返回结果 {} ****", JSON.toJSONString(result));
    // }
    //
    // @Test
    // public void testUpdate() {
    //     User entity = new User();
    //     entity.setId(1);
    //     entity.setCompany("联线电子商务");
    //     User result = testUserService.save(entity);
    //
    //     logger.info("**** 返回结果 {} ****", JSON.toJSONString(result));
    // }
    //
    // @Test
    // public void testDelete() {
    //     testUserService.delete(1);
    // }
    //
    //
    // @Test
    // public void findAll() {
    //     Pageable pageable = new PageRequest(0, 3, new Sort(Sort.Direction.DESC, "createTime"));
    //
    //     User user = new User();
    //     user.setSex("1");
    //     Page<User> page = testUserService.findAll(user, pageable);
    //
    //     logger.info("**** 返回结果 {} ****", JSON.toJSONString(page));
    // }
    //
    // @Test
    // public void findExample() {
    //     List<User> list = testUserService.findExample("0");
    //     logger.info("**** 返回结果 {} ****", JSON.toJSONString(list));
    //
    // }

    // @Test
    // public void updateExample() {
    //     oauthService.queryResourcesByRole();
    //
    //     logger.info("********** 执行完毕 **********");
    // }

    // @Test
    // public void testre() {
    //     preLoadMetadataSource.loading();
    //
    //     logger.info("********** 执行完毕 返回结果 ==>> {} **********");
    // }
    // @Test
    // public void testList() {
    //     UserEntity list = oauthService.queryByName("123");
    //
    //     logger.info("********** 执行完毕 返回结果 ==>> {} **********", JSON.toJSONString(list));
    // }
    // @Test
    // public void queryByName() {
    //     List<ResourcesResponseVo>  list = oauthService.queryResourcesById(2);
    //
    //     logger.info("********** 执行完毕 返回结果 ==>> {} **********");
    // }
    // @Test
    // public void queryClientDetailsById() {
    //     OauthClientDetailsResponseVo list = oauthService.queryClientDetailsById(456);
    //
    //     logger.info("********** 执行完毕 返回结果 ==>> {} **********");
    // }
    // @Test
    // public void queryByName() {
    //     List<ResourcesResponseVo> list = oauthService.queryResourcesBySysId(1);
    //
    //     logger.info("********** 执行完毕 返回结果 ==>> {} **********");
    // }

    // @Test
    // public void list() {
    //     UserListRequestVo search = new UserListRequestVo(1, 10);
    //     UserListResponseVo list = userService.list(search);
    //
    //     logger.info("********** 执行完毕 返回结果 ==>> {} **********");
    // }

    // @Test
    // public void queryRoleByStaffId() {
    //     List<String> list = oauthService.queryRoleByStaffId(123);
    //
    //
    //     logger.info("********** 执行完毕 返回结果 ==>> {} **********");
    // }

    // @Test
    // public void queryTestByName() {
    //     UserResult list = oauthService.queryByName("123");
    //
    //
    //     logger.info("********** 执行完毕 返回结果 ==>> {} **********");
    // }


}
