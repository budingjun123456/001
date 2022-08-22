package xyz.javaee.psychology_questionnaire.service;

import xyz.javaee.psychology_questionnaire.entity.DTO.MentalityAndSolution;
import xyz.javaee.psychology_questionnaire.entity.UserMentality;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UserMentalityService extends IService<UserMentality> {


    int addUserMentality(Integer userId, Integer mentalityId);

    List<MentalityAndSolution> getMentalityAndSolutionById(Integer userId);

    int deleteUserMentality(Integer userId, Integer mentalityId);
}
