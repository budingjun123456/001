package xyz.javaee.psychology_questionnaire.service;

import xyz.javaee.psychology_questionnaire.entity.JwtUser;
import xyz.javaee.psychology_questionnaire.entity.User;

/**
 * @author Zexing Zhang
 * @date 2022/5/14 12:38 AM
 * @Description 认证服务接口层
 */
public interface AuthService {
    /**
     * 创建已登录状态的token
     * @param loginRequest
     * @return
     */
    JwtUser createToken(User loginRequest);

    void removeToken();
}
