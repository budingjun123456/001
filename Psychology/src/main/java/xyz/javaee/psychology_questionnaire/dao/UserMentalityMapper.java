package xyz.javaee.psychology_questionnaire.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import xyz.javaee.psychology_questionnaire.entity.DTO.MentalityAndSolution;
import xyz.javaee.psychology_questionnaire.entity.UserMentality;

import java.util.List;


public interface UserMentalityMapper extends BaseMapper<UserMentality> {

    List<MentalityAndSolution> getMentalityAndSolutionById(@Param("userId") Integer userId);
}
