package xyz.javaee.psychology_questionnaire.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import java.util.UUID;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import xyz.javaee.psychology_questionnaire.entity.JwtUser;
import xyz.javaee.psychology_questionnaire.entity.User;
import xyz.javaee.psychology_questionnaire.service.AuthService;
import xyz.javaee.psychology_questionnaire.service.UserService;
import xyz.javaee.psychology_questionnaire.utils.JwtTokenUtils;
import xyz.javaee.psychology_questionnaire.utils.Result;
import xyz.javaee.psychology_questionnaire.utils.ResultCode;


/**
 * @author Zexing Zhang
 * @date 2022/5/13 11:36 PM
 * @Description 认证授权
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "认证")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public Result login(@RequestBody User loginRequest,
                        @RequestParam String captchaVerification,
                        @RequestParam String code) {

        System.out.println("登录模块正在执行");
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost",6379);
        System.out.println("Redis连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        String captchaCode="";
        System.out.println("随机码为："+captchaVerification);
        if(jedis.ping().equals("PONG")){//redis状态正常
            //通过随机码获取验证码
            captchaCode = jedis.get(captchaVerification);
            //Redis中删除对应的验证码
            jedis.del(captchaVerification);
        }else{
            return Result.RCode(false,ResultCode.NO_REDIS);
        }
        System.out.println("正在验证验证码："+captchaCode);
        if (!code.equals("") && code.equals(captchaCode)) {
            JwtUser user = authService.createToken(loginRequest);
            System.out.println("创建用户登录态token中");
            if(user.getStatus().equals(ResultCode.SUCCESS)){
                //申请成功
                System.out.println("申请登录成功，返回用户数据");
                return Result.ok().data(user);
            }else{
                System.out.println("申请登录失败，返回错误信息");
                return Result.RCode(false, user.getStatus());
            }
        } else {
            System.out.println("验证码错误");
            return Result.RCode(false,ResultCode.USER_CAPTCHA_CODE_ERR);
        }
    }

    @PostMapping("/logout")
    @ApiOperation("退出")
    public Result logout() {
        authService.removeToken();
        return Result.ok();
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result register(User userRegister) {
        userService.register(userRegister);
        return Result.ok();
    }

    @PostMapping("/verify")
    @ApiOperation("token验证")
    public Result verify(@RequestBody String token) {
        System.out.println("token有效期验证中");
        try {
            token=token.substring(0, token.length()-2);
            token=token.substring(10,token.length());
            System.out.println("得到的要验证的token是"+token);
            return JwtTokenUtils.isVerify(token) ? Result.RCode(true,ResultCode.SUCCESS):Result.RCode(false,ResultCode.USER_NOT_LOGIN);
        }catch (Exception e){
            return Result.RCode(false,ResultCode.USER_NOT_LOGIN);
        }
    }
    @PostMapping("/captcha")
    @ApiOperation("获取验证码")
    public Result captcha() {
        System.out.println("获取验证码模块正在执行");
        String simpleUUID = UUID.randomUUID().toString().replaceAll("-","");//生成唯一UUID,防止产生重复覆盖
        System.out.println("simpleUUID="+simpleUUID);
        String captchaVerification = "captcha" + simpleUUID;
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost",6379);
        System.out.println("Redis连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(123, 38, 4, 20);//圆圈干扰验证码
        String code = captcha.getCode();//获取验证码图片的文字内容

        jedis.set(captchaVerification,code);// SET 命令用于设置给定 key 的值。如果 key 已经存储其他值， SET 就覆写旧值，且无视类型
        jedis.expire(captchaVerification, (long)60 * 5);//Expire 命令用于设置 key 的过期时间，key 过期后将不再可用。单位以秒计。5分钟后废弃该captchaVerification对应验证码
        if(jedis.ping().equals("PONG"))
            return Result.ok().data("img", captcha.getImageBase64Data())
                .data("captchaVerification", captchaVerification);
        else
//            return Result.error().data("err","redis服务运行失败");
            return Result.RCode(false,ResultCode.NO_REDIS);
    }

}
