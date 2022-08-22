package xyz.javaee.psychology_questionnaire.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.javaee.psychology_questionnaire.dao.GroupsMapper;
import xyz.javaee.psychology_questionnaire.dao.QuestionsMapper;
import xyz.javaee.psychology_questionnaire.entity.Groups;
import xyz.javaee.psychology_questionnaire.entity.Questions;
import xyz.javaee.psychology_questionnaire.entity.QuestionsBank;
import xyz.javaee.psychology_questionnaire.entity.User;
import xyz.javaee.psychology_questionnaire.service.GroupsService;
import xyz.javaee.psychology_questionnaire.service.QuestionsService;
import xyz.javaee.psychology_questionnaire.service.UserService;

/**
 * 用户组服务层实现
 */
@Service
public class GroupsServiceImpl extends ServiceImpl<GroupsMapper, Groups> implements GroupsService {
    @Autowired
    private GroupsMapper groupsMapper;
    @Autowired
    private UserService userService;
    @Override
    public IPage<Groups> getList(Page<Groups> page,String telephone) {
        System.out.println("进入获取用户组列表服务层");
        QueryWrapper<Groups> groupsQueryWrapper = new QueryWrapper<>();
        User admin=userService.login(telephone);
        System.out.println("获取到的后端的管理员信息为"+admin.toString());
        groupsQueryWrapper.eq("adminId",admin.getUserid());
        IPage<Groups> page2 = groupsMapper.getList(page,groupsQueryWrapper);
        return page2;
    }

    @Override
    public Groups createGroup(String groupName, String telephone) {
        System.out.println("进入创建用户组服务层");
        User admin=userService.login(telephone);
        Groups groups=new Groups();
        groups.setGroupName(groupName);
        groups.setAdminId(admin.getUserid());
        this.baseMapper.insert(groups);
        return groups;
    }

    @Override
    public void deleteGroup(Integer groupId) {
        this.baseMapper.deleteById(groupId);
    }
}
