package life.kobefengfeng.community.community.schedule;

import life.kobefengfeng.community.community.cache.HotTagCache;
import life.kobefengfeng.community.community.mapper.QuestionMapper;
import life.kobefengfeng.community.community.model.Question;
import life.kobefengfeng.community.community.model.QuestionExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/7/19 19:40
 * @Version 1.0
 */
@Component
@Slf4j
public class HotTagTasks {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private HotTagCache hotTagCache;

    @Scheduled(fixedRate = 1000*60*60*3)//3小时更新一次
    //@Scheduled(cron = "0 0 1 * * *")//每天凌晨1点执行
    public void hotTagSchedule() {
        int offset = 0;
        int limit = 5;
        log.info("hotTagSchedule start {}", new Date());
        List<Question> list = new ArrayList<>();
        //将标签和其优先级存入map 优先级最高的tag应该放在前面
        Map<String, Integer> priorities = new HashMap<>();
        //当第一页或者热门话题页面满了以后 接着显示更多
        while(offset == 0 || list.size() == limit){
            //分页查询问题列表
            list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,limit));
            for (Question question : list) {

                String[] tags = StringUtils.split(question.getTag(), ",");
                for (String tag : tags) {
                    Integer priority = priorities.get(tag);
                    if(priority != null){
                        priorities.put(tag,priority + 5 + question.getCommentCount());
                    }else {
                        priorities.put(tag,5 + question.getCommentCount());
                    }
                }
            }
            offset += limit;
        }
        hotTagCache.updateTags(priorities);
        log.info("hotTagSchedule end {}", new Date());
    }
}
