package xyz.javaee.psychology_questionnaire.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.javaee.psychology_questionnaire.dao.GroupsmapMapper;
import xyz.javaee.psychology_questionnaire.entity.Groupsmap;
import xyz.javaee.psychology_questionnaire.entity.User;
import xyz.javaee.psychology_questionnaire.service.GroupsmapService;
import xyz.javaee.psychology_questionnaire.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户组服务层实现
 */
@Service
public class GroupsmapServiceImpl extends ServiceImpl<GroupsmapMapper, Groupsmap> implements GroupsmapService {
    @Autowired
    UserService userService;
    @Override
    public Integer createMap(Integer groupId, String telephone) {
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("telephone",telephone);
        Integer count = Math.toIntExact(userService.getBaseMapper().selectCount(userQueryWrapper));
        if(count==0){
            return count;
        }else{
            Groupsmap groupsmap=new Groupsmap();
            groupsmap.setGroupId(groupId);
            User user = userService.login(telephone);
            groupsmap.setUserId(user.getUserid());
            this.baseMapper.insert(groupsmap);
            return 1;
        }
    }

    @Override
    public List<User> findUsers(Integer groupId) {
        QueryWrapper<Groupsmap> groupsmapQueryWrapper=new QueryWrapper<>();
        groupsmapQueryWrapper.eq("groupId",groupId);
        List<Groupsmap> groupsmapList=this.baseMapper.selectList(groupsmapQueryWrapper);
        List<User> userList=new ArrayList<>();
        for(int i=0;i<groupsmapList.size();i++){
            Groupsmap groupsmap=groupsmapList.get(i);
            userList.add(userService.getBaseMapper().selectById(groupsmap.getUserId()));
        }
        return userList;
    }
}
