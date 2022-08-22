package xyz.javaee.psychology_questionnaire.service.serviceImpl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.javaee.psychology_questionnaire.dao.UserMentalityMapper;
import xyz.javaee.psychology_questionnaire.entity.DTO.MentalityAndSolution;
import xyz.javaee.psychology_questionnaire.entity.UserMentality;
import xyz.javaee.psychology_questionnaire.service.UserMentalityService;

@Service
@AllArgsConstructor
public class UserMentalityServiceImpl extends ServiceImpl<UserMentalityMapper, UserMentality> implements UserMentalityService{

    private final UserMentalityMapper userMentalityMapper;

    @Override
    public int addUserMentality(Integer userId, Integer mentalityId) {
        UserMentality userMentality = new UserMentality();
        userMentality.setUserid(userId);
        userMentality.setMentalityid(mentalityId);

        DateTime date = DateUtil.date();
        userMentality.setGetTime(date);

        return userMentalityMapper.insert(userMentality);
    }

    @Override
    public List<MentalityAndSolution> getMentalityAndSolutionById(Integer userId) {
        return userMentalityMapper.getMentalityAndSolutionById(userId);
    }

    @Override
    public int deleteUserMentality(Integer userId, Integer mentalityId) {
        UserMentality userMentality = new UserMentality();
        userMentality.setMentalityid(mentalityId);
        userMentality.setUserid(userId);
        QueryWrapper<UserMentality> userMentalityQueryWrapper = new QueryWrapper<>(userMentality);
        return userMentalityMapper.delete(userMentalityQueryWrapper);
    }
}
