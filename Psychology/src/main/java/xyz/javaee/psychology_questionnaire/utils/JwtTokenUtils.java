package xyz.javaee.psychology_questionnaire.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import xyz.javaee.psychology_questionnaire.constants.SecurityConstants;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Zexing Zhang
 * @date 2022/5/13 12:11 AM
 * @Description JWT工具类
 */
public class JwtTokenUtils {
    /**
     * 生成足够的安全随机密钥，以适合符合规范的签名
     */
    private static final byte[] API_KEY_SECRET_BYTES = DatatypeConverter.parseBase64Binary(SecurityConstants.JWT_SECRET_KEY);
    //根据key生成密钥（会根据字节参数长度自动选择相应的 HMAC摘要 算法）
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(API_KEY_SECRET_BYTES);

    /**
     *
     * @param username 用户手机号
     * @param id 用户id
     * @param roles 角色权限 分管理员和使用用户
     * @param isRememberMe token有效期 false1小时过期 true7天过期
     * @return
     */
    public static String createToken(String username, String id, List<String> roles, boolean isRememberMe) {
        long expiration = isRememberMe ? SecurityConstants.EXPIRATION : SecurityConstants.EXPIRATION_REMEMBER;
        final Date CREATED_DATE = new Date();//创建token当前时间
        final Date EXPIRATION_DATE = new Date(CREATED_DATE.getTime() + expiration * 1000);//token销毁时间
        String tokenPrefix = Jwts.builder()//创建JWT对象，返回了一个 DefaultJwtBuilder()，包含了一些Header和Payload的一些常用设置方法
                .setHeaderParam("type", SecurityConstants.TOKEN_TYPE)//设置JWT的头部Header
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)//设置签名方法。两个参数分别是签名算法和自定义的签名Key（盐）
                .claim(SecurityConstants.ROLE_CLAIMS, String.join(",", roles))//如果builder中 Claims正文 声明属性为空，则创建DefaultClaims对象，并把键值放入；如果Claims属性不为空，获取之后判断键值，存在则更新，不存在则直接放入。
                .setId(id)//设置id
                .setIssuedAt(CREATED_DATE)// 设置签发时间
                .setSubject(username)//设置主题（声明信息）
                .setExpiration(EXPIRATION_DATE)// 设置过期时间
                .compact();//生成token（1.编码 Header 和 Payload 2.生成签名 3.拼接字符串）
        return SecurityConstants.TOKEN_PREFIX + tokenPrefix;// 添加 token 前缀 "Bearer ";
    }

    public static String getId(String token) {
        Claims claims = getClaims(token);
        return claims.getId();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Claims claims = getClaims(token);
        List<SimpleGrantedAuthority> authorities = getAuthorities(claims);
        System.out.println(authorities);
        String userName = claims.getSubject();
        return new UsernamePasswordAuthenticationToken(userName, token, authorities);
    }

    private static List<SimpleGrantedAuthority> getAuthorities(Claims claims) {
        String role = (String) claims.get(SecurityConstants.ROLE_CLAIMS);
        System.out.println(role);
        return Arrays.stream(role.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private static Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public static Boolean isVerify(String token) {
//        System.out.println(token);
//        System.out.println(token.startsWith("Bearer "));
        if(token == null){
            System.out.println("token为空");
            return false;
        }else if(!token.startsWith("Bearer ")){
            System.out.println("非法token");
            return false;
        }else {
            try {
                token = token.substring(7); // The part after "Bearer "
                System.out.println("得到的token内容"+token);
                final Claims claims = getClaims(token);
                System.out.println("解析后的token内容"+claims.toString());
                if(claims.get("rol").equals("ROLE_ADMIN")){
                    return true;
                }else{
                    System.out.println("用户权限不足");
                    return false;
                }

            } catch (Exception e) {
                System.out.println("token过期或签名错误");
                return false;
            }
        }
    }

    public static Key generateKey() {
        return new SecretKeySpec(API_KEY_SECRET_BYTES, SignatureAlgorithm.HS256.getJcaName());
    }
    /**
     * 解析token
     * @param token
     * @return
     */
    public static Map<String, Object> parseToken(String token) {
        return Jwts.parser()  // 得到DefaultJwtParser
                .setSigningKey(generateKey()) // 设置签名密钥
                .parseClaimsJws(token)//设置需要解析的jwt
                .getBody();
    }
}
