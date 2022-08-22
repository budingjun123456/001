package xyz.javaee.psychology_questionnaire.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.javaee.psychology_questionnaire.entity.Analysis;
import xyz.javaee.psychology_questionnaire.entity.Mentality;
import xyz.javaee.psychology_questionnaire.entity.Submit;
import xyz.javaee.psychology_questionnaire.service.AnalysisService;
import xyz.javaee.psychology_questionnaire.service.MentalityService;
import xyz.javaee.psychology_questionnaire.service.SubmitService;
import xyz.javaee.psychology_questionnaire.utils.HttpUtil;
import xyz.javaee.psychology_questionnaire.utils.Result;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @author Zexing Zhang
 * @date 2022/5/14 2:55 PM
 * @Description 心理状态控制类
 */
@RestController
@RequestMapping("/mentality")
@AllArgsConstructor
public class MentalityController {
    // webapi接口地址
    private static final String WEBTTS_URL = "http://ltpapi.xfyun.cn/v1/ke";
    // 应用ID
    private static final String APPID = "20b352ca";
    // 接口密钥
    private static final String API_KEY = "6956e9d7bf3968c983312f2c0150ea21";
    // 文本
    private static String TEXT = "";
    private static final String TYPE = "dependent";

    private final MentalityService mentalityService;
    private final AnalysisService analysisService;
    private final SubmitService submitService;

    @PostMapping("/getMentalityList")
    @ApiOperation("获取心理状态")
    public Result getMentalityList(){
        List<Mentality> list = mentalityService.list();
        return Result.ok().data(list);
    }
    @PostMapping("/getMentalityCount")
    @ApiOperation("获取心理分析次数")
    public Result getMentalityCount(){
        long count=analysisService.count();
        return Result.ok().data(count);
    }
    @PostMapping("/getSubmitCount")
    @ApiOperation("获取咨询次数")
    public Result getSubmitCount(){
        long count=submitService.count();
        return Result.ok().data(count);
    }
    @PostMapping("/getDataMap")
    @ApiOperation("获取心理分析数据")
    public Result getDataMap(){
        Map<String,List<Integer>> map=getMap(5);
        return Result.ok().data(map);
    }

    /**
     * 得到最近day天的心理分析分类数据
     * @param day
     * @return Map<String,List<Integer>>
     */
    private Map<String,List<Integer>> getMap(Integer day){
        List<Analysis> list=analysisService.list();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        Map<String,List<Integer>> map=new HashMap<>();
        List<String> stringList=new ArrayList<>();
        Date today=new Date();
        for(int i=0;i<day;i++){
            stringList.add(getDateAfter(today,0-i));
        }
        for (Analysis analysis : list) {
            Date date = new Date(analysis.getTime().getTime());
            String time=ft.format(date);
            if(!stringList.contains(time)){
                continue;
            }
            List<Integer> temp;
            if(map.get(time)==(null)){
                temp=new ArrayList<>();
                for(int i=0;i<5;i++){
                    temp.add(0);
                }
                map.put(time,temp);
            }
            int i = map.get(time).get(analysis.getMentalityId()) + 1;
            map.get(time).set(analysis.getMentalityId(),i);
        }
        for(String string:stringList){
            if(!map.containsKey(string)){
                List<Integer> temp=new ArrayList<>();
                for(int i=0;i<5;i++){
                    temp.add(0);
                }
                map.put(string,temp);
            }
        }
        return map;
    }

    /**
     * 获取某年某月心理状态和对应检测出人次数
     * @return
     */
    @PostMapping("/getMonthMentalityAndCount")
    public Result getMonthMentalityAndCount(Integer year,Integer month){
        List<Analysis> analysisList = analysisService.list();
        HashMap<String,Integer> hashMap=new HashMap<>();
        hashMap.put("积极健康人群",0);
        hashMap.put("潜在心理问题人群",0);
        hashMap.put("心理问题人群",0);
        hashMap.put("重度心理问题人群",0);
        hashMap.put("普通人群",0);
        for (Analysis analysis : analysisList) {
            Integer id=analysis.getMentalityId();
            Timestamp time=analysis.getTime();
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(new Date(time.getTime()));
            Integer analysisMonth=calendar.get(Calendar.MONTH);
            Integer analysisYear=calendar.get(Calendar.YEAR);
            if(analysisMonth.equals(month-1)&&analysisYear.equals(year)){
                if (id == 0) {
                    hashMap.replace("积极健康人群", hashMap.get("积极健康人群") + 1);
                } else if (id == 1) {
                    hashMap.replace("潜在心理问题人群", hashMap.getOrDefault("潜在心理问题人群", 0) + 1);
                } else if (id == 2) {
                    hashMap.replace("心理问题人群", hashMap.getOrDefault("心理问题人群", 0) + 1);
                } else if (id == 3) {
                    hashMap.replace("重度心理问题人群", hashMap.getOrDefault("重度心理问题人群", 0) + 1);
                } else if (id == 4) {
                    hashMap.replace("普通人群", hashMap.getOrDefault("普通人群", 0) + 1);
                }
            }
        }
        return Result.ok().data(hashMap);
    }
    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public String getDateAfter(Date d, int day) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);//+后 -前
        return format.format(now.getTime());
    }
    @PostMapping("/getWarnCount")
    @ApiOperation("获取心理分析次数")
    public Result getWarnCount(){
        QueryWrapper<Analysis> analysisQueryWrapper=new QueryWrapper<>();
        analysisQueryWrapper.eq("mentalityId",3);
        long count = analysisService.count(analysisQueryWrapper);
        return Result.ok().data(count);
    }
    @PostMapping("/getMonthData")
    @ApiOperation("获取最近一月心理分析分类数据")
    public Result getMonthData(){
        Map<String,List<Integer>> map=getMap(30);
        return Result.ok().data(map);
    }
    @PostMapping("/getSaveCount")
    @ApiOperation("获取备份次数")
    public Result getSaveCount() throws ParseException {
        Calendar rightNow = Calendar.getInstance();
        Calendar bzTime = Calendar.getInstance();
        Date myDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String Time = sdf.format(myDate);
        Date nowTime = sdf.parse(Time);//获取现在时间
        Date barques =  sdf.parse("2021-09-17");//获取规定日期；
        long syDay = nowTime.getTime()-barques.getTime();
        long days = syDay/60/60/1000/24;//获取间距天数
        return Result.ok().data(days);
    }
    @PostMapping("/getMentalityAndCount")
    @ApiOperation("获取心理状态和对应检测出人次数")
    public Result getMentalityAndCount(){
        List<Analysis> list = analysisService.list();
        HashMap<String,Integer> hashMap=new HashMap<>();
        hashMap.put("积极健康人群",0);
        hashMap.put("潜在心理问题人群",0);
        hashMap.put("心理问题人群",0);
        hashMap.put("重度心理问题人群",0);
        hashMap.put("普通人群",0);
        for (Analysis analysis : list) {
            Integer id=analysis.getMentalityId();
            switch (id) {
                case 0:
                    hashMap.replace("积极健康人群", hashMap.get("积极健康人群") + 1);
                    break;
                case 1:
                    hashMap.replace("潜在心理问题人群", hashMap.getOrDefault("潜在心理问题人群",0) + 1);
                    break;
                case 2:
                    hashMap.replace("心理问题人群", hashMap.getOrDefault("心理问题人群",0) + 1);
                    break;
                case 3:
                    hashMap.replace("重度心理问题人群", hashMap.getOrDefault("重度心理问题人群",0) + 1);
                    break;
                case 4:
                    hashMap.replace("普通人群", hashMap.getOrDefault("普通人群",0) + 1);
                    break;
            }
        }
        return Result.ok().data(hashMap);
    }
    /**
     * 组装http请求头
     */
    private static Map<String, String> buildHttpHeader() throws UnsupportedEncodingException {
        String curTime = System.currentTimeMillis() / 1000L + "";
        String param = "{\"type\":\"" + TYPE +"\"}";
        String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
        String checkSum = DigestUtils.md5Hex(API_KEY + curTime + paramBase64);
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        header.put("X-Param", paramBase64);
        header.put("X-CurTime", curTime);
        header.put("X-CheckSum", checkSum);
        header.put("X-Appid", APPID);
        return header;
    }

    /**
     * 根据咨询的所有问题分析并返回关键词列表
     * @return
     * @throws UnsupportedEncodingException
     */
    @PostMapping("/getCloudWords")
    public Result getCloudWords() throws UnsupportedEncodingException {
        List list=submitService.getAll();
        Map<String, String> header = null;
        try {
            header = buildHttpHeader();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List allRes=new ArrayList();
        int tag=0;
        for (Object o : list) {
            try {
                tag++;
                String result = HttpUtil.doPost1(WEBTTS_URL, header, "text=" + URLEncoder.encode(((Submit)o).getSubmitContent(), "utf-8"));
                JSONObject jsonObject= JSONObject.parseObject(result);
                Map<String,Object> map = jsonObject;
    //            Object code = map.get("code");
                Object data=map.get("data");
                Map<String,Object> map2 = (Map<String,Object>)data;
                List list1=(List)map2.get("ke");
                for (Object o1 : list1) {
                    allRes.add(o1);
                }
            }catch (Exception e){
                //System.out.println();
            }
            if(tag==20){
                break;
            }
        }
        Collections.sort(allRes, new Comparator<Map<String, String>>() {
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                String sort1=o1.get("score")+o1.get("word");//从list中拿出来第一个的id和name拼接到一起
                String sort2=o2.get("score")+o2.get("word");//从list中拿出来第二个的id和name拼接到一起
                return sort2.compareTo(sort1);//利用String类的compareTo方法
            }
        });
        System.out.println(allRes);
        Set<String> res=new HashSet<>();
        for (Object allRe : allRes) {
            Map<String,String> map = (Map<String,String>)allRe;
            res.add(map.get("word"));
            if(res.size()==16)
                break;
        }
        return Result.ok().data(res);
    }
}
