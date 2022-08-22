package xyz.javaee.psychology_questionnaire.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.javaee.psychology_questionnaire.entity.Submit;

import java.util.List;


public interface SubmitMapper extends BaseMapper<Submit> {
    List<Submit> getConsultList();
    void delete(Integer id);
    List<Submit> getUserAllSubmit(Integer id);
    List getAll();
}
