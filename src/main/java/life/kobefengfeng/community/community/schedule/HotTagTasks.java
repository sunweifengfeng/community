package life.kobefengfeng.community.community.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/7/19 19:40
 * @Version 1.0
 */
@Component
@Slf4j
public class HotTagTasks {
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", new Date());
    }
}
