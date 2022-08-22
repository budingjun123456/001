package xyz.javaee.psychology_questionnaire.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.javaee.psychology_questionnaire.entity.Groupsmap;
import xyz.javaee.psychology_questionnaire.entity.User;

import java.util.List;

/**
 * 用户组服务接口层
 */
public interface GroupsmapService extends IService<Groupsmap>{


    Integer createMap(Integer groupId, String telephone);

    List<User> findUsers(Integer groupId);
}
