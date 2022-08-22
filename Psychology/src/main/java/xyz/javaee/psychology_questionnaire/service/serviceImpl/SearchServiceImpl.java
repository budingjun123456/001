package xyz.javaee.psychology_questionnaire.service.serviceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.javaee.psychology_questionnaire.entity.Search;
import xyz.javaee.psychology_questionnaire.dao.SearchMapper;
import xyz.javaee.psychology_questionnaire.service.SearchService;

@Service
public class SearchServiceImpl extends ServiceImpl<SearchMapper, Search> implements SearchService{

}
