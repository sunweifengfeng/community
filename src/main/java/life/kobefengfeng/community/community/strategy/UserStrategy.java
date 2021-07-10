package life.kobefengfeng.community.community.strategy;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/7/8 18:52
 * @Version 1.0
 */
public interface UserStrategy {
    LoginUserInfo getUser(String code,String state);
    //获取登录支持的类型 防止扩展时候的修改方便
    String getSupportedType();
}
