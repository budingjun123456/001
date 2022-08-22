package xyz.javaee.psychology_questionnaire.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.javaee.psychology_questionnaire.entity.Groups;
import xyz.javaee.psychology_questionnaire.entity.Groupsmap;

/**
 * 用户组持久层
 */
public interface GroupsmapMapper extends BaseMapper<Groupsmap> {

}