package xyz.javaee.psychology_questionnaire;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Zexing Zhang
 * @date 2022/5/14 9:28 AM
 *
 * @Description 启动入口
 */
@SpringBootApplication
@MapperScan("xyz.javaee.psychology_questionnaire.dao")
public class PsychologyQuestionnaireApplication {

    public static void main(String[] args) {
        SpringApplication.run(PsychologyQuestionnaireApplication.class, args);
    }

}
