package life.kobefengfeng.community.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/10 20:03
 * @Version 1.0
 */
@Configuration
//@EnableWebMvc //要将其注释，不然页面无法加载CSS等文件，提示No mapping
public class WebConfig implements WebMvcConfigurer {
    @Autowired SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns:是在请求的时候，对哪些地址进行拦截；excludePathPatterns是把哪些地址进行略过，是哪些Controller返回的地址需要通过Interceptor处理拦截
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/callback/**", "/logout");
    }
}
