package xyz.javaee.psychology_questionnaire.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.javaee.psychology_questionnaire.dao.GroupsMapper;
import xyz.javaee.psychology_questionnaire.dao.WarningMapper;
import xyz.javaee.psychology_questionnaire.entity.Groups;
import xyz.javaee.psychology_questionnaire.entity.User;
import xyz.javaee.psychology_questionnaire.entity.Warning;
import xyz.javaee.psychology_questionnaire.service.GroupsService;
import xyz.javaee.psychology_questionnaire.service.UserService;
import xyz.javaee.psychology_questionnaire.service.WarningService;

/**
 * 预警信息服务层实现
 */
@Service
public class WarningServiceImpl extends ServiceImpl<WarningMapper, Warning> implements WarningService {


}
