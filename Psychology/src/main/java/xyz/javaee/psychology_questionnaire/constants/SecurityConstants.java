package xyz.javaee.psychology_questionnaire.constants;

/**
 * @author Zexing Zhang
 * @date 2022/5/12 11:55 PM
 * @Description Spring Security相关配置常量
 */
public final class SecurityConstants {

    // 角色的key
    public static final String ROLE_CLAIMS = "rol";
    // token 过期时间
    public static final long EXPIRATION = 60 * 60L;
    public static final long EXPIRATION_REMEMBER = 60 * 60 * 24 * 7L;

    // JWT签名密钥
    public static final String JWT_SECRET_KEY = "C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@McQfTjWnZr4u7w";

    // JWT token 默认配置
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

    // System WHITELIST
    public static final String[] SYSTEM_WHITELIST = {
            "/auth/*"
    };

}
