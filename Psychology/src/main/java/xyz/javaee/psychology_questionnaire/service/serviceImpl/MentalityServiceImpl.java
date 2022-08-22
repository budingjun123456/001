package xyz.javaee.psychology_questionnaire.service.serviceImpl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.javaee.psychology_questionnaire.dao.MentalityMapper;
import xyz.javaee.psychology_questionnaire.entity.Mentality;
import xyz.javaee.psychology_questionnaire.service.MentalityService;


@Service
public class MentalityServiceImpl extends ServiceImpl<MentalityMapper, Mentality> implements MentalityService {

}

