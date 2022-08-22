package xyz.javaee.psychology_questionnaire.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.javaee.psychology_questionnaire.entity.DTO.UserList;
import xyz.javaee.psychology_questionnaire.entity.User;

import java.util.Map;

/**
 * @author Zexing Zhang
 * @date 2022/5/14 1:08 AM
 * @Description 用户数据持久层
 */
public interface UserMapper extends BaseMapper<User> {
    Integer register(User userRegister);

    IPage<UserList> findUserPage(Page page, @Param("ew") QueryWrapper<User> userQueryWrapper);

    IPage<UserList> findUserPageDESC(Page page, @Param("ew") QueryWrapper<User> userQueryWrapper);

}
