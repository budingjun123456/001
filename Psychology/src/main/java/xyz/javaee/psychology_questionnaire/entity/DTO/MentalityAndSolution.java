package xyz.javaee.psychology_questionnaire.entity.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class MentalityAndSolution {


    /**
     * 心理状态唯一标识
     */
    private Integer mentalityId;


    /**
     * 心理状态内容，如自杀危机
     */
    private String mentalityType;

    /**
     * 应对方案的内容
     */
    private String solutionContent;


    /**
     * 方案标题
     */
    private String solutionTitle;

    /**
     * 获得心理状态分析的时间
     */

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date getTime;

}
