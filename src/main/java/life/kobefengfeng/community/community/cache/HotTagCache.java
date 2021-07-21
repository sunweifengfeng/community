package life.kobefengfeng.community.community.cache;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/7/21 19:51
 * @Version 1.0
 */
@Component
@Data
public class HotTagCache {
    private Map<String,Integer> tags = new HashMap<>();
}
