package life.kobefengfeng.community.community.strategy;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/7/8 20:04
 * @Version 1.0
 */
@Service
public class UserStrategyFactory {
    @Autowired//通过此注解 可以将实现了UserStrategy的类GiteeUserStrategy和GItHubUserStrategy全部注入到strategies其中
    private List<UserStrategy> strategies;

    //当策略类和传入的type一致时 返回策略
    public UserStrategy getStrategy(String type){
        for (UserStrategy strategy : strategies) {
            if(StringUtils.equals(strategy.getSupportedType(),type)){//不直接用==是为了报空指针
                return strategy;
            }
        }
        return null;
    }
}
