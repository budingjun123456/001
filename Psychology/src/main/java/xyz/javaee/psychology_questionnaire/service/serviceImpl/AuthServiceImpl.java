package xyz.javaee.psychology_questionnaire.service.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import xyz.javaee.psychology_questionnaire.entity.JwtUser;
import xyz.javaee.psychology_questionnaire.entity.User;
import xyz.javaee.psychology_questionnaire.service.AuthService;
import xyz.javaee.psychology_questionnaire.service.UserService;
import xyz.javaee.psychology_questionnaire.utils.CurrentUserUtils;
import xyz.javaee.psychology_questionnaire.utils.JwtTokenUtils;
import xyz.javaee.psychology_questionnaire.utils.ResultCode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zexing Zhang
 * @date 2022/5/14 12:38 AM
 * @Description 认证服务层
 */
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Autowired
    private final UserService userService;
    @Autowired
    private final StringRedisTemplate stringRedisTemplate;
    @Autowired
    private final CurrentUserUtils currentUserUtils;

    public JwtUser createToken(User loginRequest) {
        User user = userService.login(loginRequest.getTelephone());//查询到登录手机号对应数据库中user表行数据，并转储User类中
        System.out.println("数据库中查找手机号对应的用户");
        JwtUser jwtUser = new JwtUser(user);
        System.out.println("创建带状态和令牌的用户");
        if (user == null) {
            System.out.println("用户在数据库中不存在");
            jwtUser.setStatus(ResultCode.USER_ACCOUNT_NOT_EXIST);
            return jwtUser;
        }

        if (!userService.check(loginRequest.getPassword(), user.getPassword())) {
            System.out.println("输入的密码不正确");
            jwtUser.setStatus(ResultCode.USER_CREDENTIALS_ERROR);
            return jwtUser;
        }

        if (!jwtUser.isEnabled()) {
            System.out.println("无效用户");
            jwtUser.setStatus(ResultCode.USER_NO_USE);
            return jwtUser;
        }
        try{
            List<String> authorities = jwtUser.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)//GrantedAuthority对象转换为string对象
                    .collect(Collectors.toList());
            System.out.println("动态获取到该用户拥有的权限为："+authorities);
            String token = JwtTokenUtils.createToken(user.getTelephone(), user.getUserid().toString(), authorities, false);
            System.out.println("创建token成功");
            stringRedisTemplate.opsForValue().set(user.getUserid().toString(), token);
            jwtUser.setToken(token);
            jwtUser.setStatus(ResultCode.SUCCESS);
        }catch (Exception e){
            jwtUser.setStatus(ResultCode.DATABASE_ERROR);
        }
        return jwtUser;
    }

    public void removeToken() {
        stringRedisTemplate.delete(currentUserUtils.getCurrentUser().getUserid().toString());
    }
}
