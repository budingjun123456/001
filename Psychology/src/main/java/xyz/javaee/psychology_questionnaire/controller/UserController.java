package xyz.javaee.psychology_questionnaire.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import xyz.javaee.psychology_questionnaire.entity.DTO.MentalityAndSolution;
import xyz.javaee.psychology_questionnaire.entity.DTO.UserList;
import xyz.javaee.psychology_questionnaire.entity.User;
import xyz.javaee.psychology_questionnaire.entity.VO.UserVO;
import xyz.javaee.psychology_questionnaire.service.UserMentalityService;
import xyz.javaee.psychology_questionnaire.service.UserService;
import xyz.javaee.psychology_questionnaire.utils.Result;

import java.util.List;

@RestController
@Api(tags = "用户")
@RequestMapping("/user")
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
public class UserController {

    private final UserService userService;
    private final UserMentalityService userMentalityService;

    @PostMapping("/findUserPage")
    @ApiOperation("获取用户列表")
    public Result findUserPage(@ApiParam("页数") @RequestParam(defaultValue = "1") Integer current,
                               @ApiParam("页大小") @RequestParam(defaultValue = "6") Integer size,
                               @ApiParam("查询条件") @RequestBody UserVO userVO) {

        IPage<UserList> userPage = userService.findUserPage(current, size, userVO);

        return Result.ok().data("userList", userPage.getRecords()).data("total", userPage.getTotal());
    }
    @PostMapping("/findAUser")
    @ApiOperation("获取用户")
    public Result findAUser(@RequestParam Integer userId) {
        User user= userService.getBaseMapper().selectById(userId);
        return Result.ok().data(user);
    }
    @PostMapping("/editMentality")
    @ApiOperation("根据分析结果动态改变用户绑定的心理状态")
    public Result editMentality(@RequestParam Integer userId,@RequestParam Integer mentalityId){
        User user =userService.getBaseMapper().selectById(userId);
        user.setMentalityId(mentalityId);
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("userId",userId);
        userService.getBaseMapper().update(user,userQueryWrapper);
        return Result.ok();
    }
    @PostMapping("/findUserWarnCount")
    @ApiOperation("获取预警用户列表")
    public Result findUserWarn() {

        long count= userService.findUserWarnCount();

        return Result.ok().data("total", count);
    }
    @PostMapping("/findUserWarn")
    @ApiOperation("获取预警用户列表")
    public Result findUserWarn(@RequestParam(defaultValue = "1") Integer current,
                               @RequestParam(defaultValue = "6") Integer size) {

        IPage<UserList> userPage = userService.findUserWarn(current,size);

        return Result.ok().data("userList",userPage.getRecords()).data("total", userPage.getTotal());
    }
    @PostMapping("/deleteUser")
    @ApiOperation("删除用户")
    public Result deleteUser(@ApiParam("用户Id") @RequestParam(required = true) Integer userid) {
        System.err.println(userid);
        userService.deleteUser(userid);
        return Result.ok().message("删除成功");
    }
    @PostMapping("/findMentalityUser")
    @ApiOperation("找到某心理状态下的一页用户")
    public Result findMentalityUser(@ApiParam("页数") @RequestParam(defaultValue = "1") Integer current,
                             @ApiParam("页大小") @RequestParam(defaultValue = "6") Integer size,
                             @ApiParam("查询条件") @RequestBody UserVO userVO) {
        System.err.println("进入用户控制层,得到要获取的心理状态id为");
        System.out.println(userVO.getMentalityId());
        Page<UserList> page = new Page<>(current, size);
        IPage<UserList> userPage=userService.findMentalityUser(page,userVO.getMentalityId());
        return Result.ok().data("userList", userPage.getRecords()).data("total", userPage.getTotal());
    }
    @PostMapping("/addUserMentality")
    @ApiOperation("给用户添加心理状态")
    public Result addUserMentality(@ApiParam("用户Id") @RequestParam(required = true) Integer userId,
                                   @ApiParam("心理状态Id") @RequestParam(required = true) Integer mentalityId) {

        try {
            userMentalityService.addUserMentality(userId, mentalityId);
        } catch (Exception e) {
            return Result.error().message("心理状态已存在").data(e.getMessage());
        }
        return Result.ok().message("添加成功");
    }

    @PostMapping("/getMentalityAndSolutionById")
    @ApiOperation("获取用户心理状态和解决方案")
    public Result getMentalityAndSolutionById(@ApiParam("用户Id") @RequestParam(required = true) Integer userId) {

        List<MentalityAndSolution> mentalityAndSolutionById = userMentalityService.getMentalityAndSolutionById(userId);
        return Result.ok().data(mentalityAndSolutionById);
    }

    @PostMapping("/deleteUserMentality")
    @ApiOperation("删除用户绑定的心理状态")
    public Result deleteUserMentality(@ApiParam("用户Id") @RequestParam(required = true) Integer userId,
                                      @ApiParam("心理状态Id") @RequestParam(required = true) Integer mentalityId) {
        int i = userMentalityService.deleteUserMentality(userId, mentalityId);
        if (i > 0) {
            return Result.ok().message("删除成功");
        } else {
            return Result.error().message("删除失败");
        }
    }

    @PostMapping("/editUserInfo")
    @ApiOperation("修改用户信息")
    public Result findUserPage(@ApiParam("查询条件") @RequestBody UserVO userVO) {

            return Result.ok().message("修改成功");

    }

//    @PostMapping("/addGroup")
//    @ApiOperation("增加用户组")
//    public Result addGroup(@ApiParam("管理员id") @RequestParam Integer adminId){
//
//    }
}
