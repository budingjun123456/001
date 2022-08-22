package xyz.javaee.psychology_questionnaire.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.javaee.psychology_questionnaire.entity.DTO.MentalityAndSolution;
import xyz.javaee.psychology_questionnaire.entity.DTO.UserList;
import xyz.javaee.psychology_questionnaire.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.javaee.psychology_questionnaire.entity.VO.UserVO;

import java.util.List;

/**
 * @author Zexing Zhang
 * @date 2022/5/14 1:02 AM
 * @Description 用户服务接口层
 */
public interface UserService extends IService<User> {


    User login(String telephone);

    boolean check(String currentPassword, String password);

    int register(User userRegister);

    IPage<UserList> findUserPage(Integer current, Integer size, UserVO userVO);

    void deleteUser(Integer userid);

    IPage<UserList> findMentalityUser(Page<UserList> page, Integer mentalityId);

    IPage<UserList> findUserWarn(Integer current, Integer size);

    long findUserWarnCount();
}

