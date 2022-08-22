package xyz.javaee.psychology_questionnaire.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import xyz.javaee.psychology_questionnaire.dao.GroupsMapper;
import xyz.javaee.psychology_questionnaire.entity.*;
import xyz.javaee.psychology_questionnaire.service.GroupsService;
import xyz.javaee.psychology_questionnaire.service.GroupsmapService;
import xyz.javaee.psychology_questionnaire.service.QuestionnaireService;
import xyz.javaee.psychology_questionnaire.service.QuestionnairesmapService;
import xyz.javaee.psychology_questionnaire.utils.Result;

import java.util.List;

/**
 * @author Zexing Zhang
 * @date 2022/5/13 12:30 AM
 * @Description 用户组相关控制器
 */
@RestController
@RequestMapping("/groups")
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class GroupsController {
    @Autowired
    private final GroupsService groupsService;
    @Autowired
    private final GroupsmapService groupsmapService;
    @Autowired
    private final QuestionnairesmapService questionnairesmapService;

    /**
     * 获取所有自己创建的用户组
     * @param current
     * @param size
     * @param telephone
     * @return
     */
    @PostMapping("/getList")
    public Result getAllQuestionnaires(@RequestParam(required = false, defaultValue = "1") Integer current,
                                             @RequestParam(required = false, defaultValue = "6") Integer size,
                                       @RequestParam String telephone) {
        System.out.println("进入获取用户组控制器"+telephone);
        Page<Groups> page=new Page<>(current,size);
        IPage<Groups> groupsIPage = groupsService.getList(page,telephone);
        return Result.ok().data("groups", groupsIPage.getRecords()).data("total", groupsIPage.getTotal());
    }

    /**
     * 下发问卷时 创建用户组和问卷的映射
     * @param groupId
     * @param questionnaireId
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestParam Integer groupId, @RequestParam Integer questionnaireId) {
        questionnairesmapService.createMap(groupId,questionnaireId);
        return Result.ok();
    }

    /**
     * 根据组id删除用户组
     * @param groupId
     * @return
     */
    @PostMapping("/deleteGroup")
    public Result deleteGroup(@RequestParam Integer groupId) {
        try {
            System.out.println("进入删除用户组控制层");
            System.out.println(groupId);
            groupsService.deleteGroup(groupId);
            return Result.ok().message("删除成功！");
        }catch (NullPointerException e){
            System.out.println(e);
            return Result.error().message("删除失败!");
        }
    }

    /**
     * 根据管理员手机号查询到管理员id创建其所属的用户组
     * @param groupName
     * @param telephone
     * @return
     */
    @PostMapping("/createGroup")
    public Result createGroup(@RequestParam String groupName, @RequestParam String telephone) {
        System.out.println("进入创建用户组控制层，接受前端的参数为"+groupName+telephone);
        Groups groups = groupsService.createGroup(groupName,telephone);
        return Result.ok().data("group", groups);
    }

    /**
     * 创建新映射（被导入的用户和用户组之间）
     * @param groupId
     * @param telephone
     * @return
     */
    @PostMapping("/createMap")
    public Result createMap(@RequestParam Integer groupId, @RequestParam String telephone) {
        System.out.println("进入创建用户组映射控制层，接受前端的参数为"+groupId.toString()+telephone);
        Integer count = groupsmapService.createMap(groupId,telephone);
        if(count==0)
            return Result.ok().data("groupmap", "false");
        else
            return Result.ok().data("groupmap", "true");
    }
    /**
     * 根据组id查询所属所有用户
     * @param groupId
     * @return
     */
    @PostMapping("/findUsers")
    public Result findUsers(@RequestParam Integer groupId) {
        List<User> userList = groupsmapService.findUsers(groupId);
        return Result.ok().data("list", userList);
    }

}
