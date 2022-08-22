package xyz.javaee.psychology_questionnaire.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.javaee.psychology_questionnaire.entity.Groups;
import xyz.javaee.psychology_questionnaire.entity.Questions;

/**
 * 用户组服务接口层
 */
public interface GroupsService extends IService<Groups>{

    IPage<Groups> getList(Page<Groups> page,String telephone);

    Groups createGroup(String groupName, String telephone);

    void deleteGroup(Integer groupId);
}
