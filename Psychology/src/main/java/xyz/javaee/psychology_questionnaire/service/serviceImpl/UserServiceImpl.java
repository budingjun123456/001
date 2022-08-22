package xyz.javaee.psychology_questionnaire.service.serviceImpl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.javaee.psychology_questionnaire.dao.UserMapper;
import xyz.javaee.psychology_questionnaire.dao.UserMentalityMapper;
import xyz.javaee.psychology_questionnaire.entity.DTO.MentalityAndSolution;
import xyz.javaee.psychology_questionnaire.entity.DTO.UserList;
import xyz.javaee.psychology_questionnaire.entity.User;
import xyz.javaee.psychology_questionnaire.entity.UserMentality;
import xyz.javaee.psychology_questionnaire.entity.VO.UserVO;
import xyz.javaee.psychology_questionnaire.service.UserService;

import java.util.List;


/**
 * @author Zexing Zhang
 * @date 2022/5/14 1:03 AM
 * @Description 用户服务实现层
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User login(String telephone) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        System.out.println("进入login");
        userQueryWrapper.eq("telephone", telephone);
        return this.baseMapper.selectOne(userQueryWrapper);
    }

    @Override
    public boolean check(String currentPassword, String password) {
        return this.bCryptPasswordEncoder.matches(currentPassword, password);//spring security的密码匹配接口  true 用户密码和输入密码一致 false 不一致
    }

    @Override
    public int register(User userRegister) {
        boolean b = userNameIfExist(userRegister.getTelephone());
        if (b) {
            return -1;
        } else {
            userRegister.setPassword(bCryptPasswordEncoder.encode(userRegister.getPassword()));
            return userMapper.register(userRegister);
        }
    }

    private boolean userNameIfExist(String username) {
        User user = login(username);
        return user != null;
    }


    @Override
    public IPage<UserList> findUserPage(Integer current, Integer size, UserVO userVO) {
        Page<UserList> page = new Page<>(current, size);

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper
                .like(StringUtils.isNotBlank(userVO.getName()), "u.name", userVO.getName())
                .like(StringUtils.isNotBlank(userVO.getTelephone()), "u.telephone",userVO.getTelephone())
                .eq(userVO.getMentalityId() != null, "um." + UserMentality.COL_MENTALITYID, userVO.getMentalityId())
                .eq("u.admin",0);

        return userMapper.findUserPage(page, userQueryWrapper);
    }


    @Override
    public void deleteUser(Integer userid) {
        userMapper.deleteById(userid);
//        QueryWrapper<UserMentality> userMentalityQueryWrapper = new QueryWrapper<>();
//        userMentalityQueryWrapper.eq(UserMentality.COL_USERID, userid);
//        userMentalityMapper.delete(userMentalityQueryWrapper);
    }

    @Override
    public IPage<UserList> findMentalityUser(Page<UserList> page, Integer mentalityId) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        System.out.println("服务层得到的心理id");
        System.out.println(mentalityId);
        userQueryWrapper.eq("mentalityId",mentalityId);

        return userMapper.findUserPageDESC(page, userQueryWrapper);
    }

    @Override
    public IPage<UserList> findUserWarn(Integer current, Integer size) {
        Page<UserList> page = new Page<>(current, size);

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.eq(UserMentality.COL_MENTALITYID,2).or().eq(UserMentality.COL_MENTALITYID,3).or().eq(UserMentality.COL_MENTALITYID,4);
        return userMapper.findUserPage(page, userQueryWrapper);
    }
    @Override
    public long findUserWarnCount() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq(UserMentality.COL_MENTALITYID,2).or().eq(UserMentality.COL_MENTALITYID,3).or().eq(UserMentality.COL_MENTALITYID,4);
        return userMapper.selectCount(userQueryWrapper);
    }
}

