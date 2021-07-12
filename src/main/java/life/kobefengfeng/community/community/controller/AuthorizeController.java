package life.kobefengfeng.community.community.controller;

import life.kobefengfeng.community.community.dto.AccessTokenDTO;
import life.kobefengfeng.community.community.provider.dto.GithubUser;
import life.kobefengfeng.community.community.model.User;
import life.kobefengfeng.community.community.provider.GithubProvider;
import life.kobefengfeng.community.community.service.UserService;
import life.kobefengfeng.community.community.strategy.LoginUserInfo;
import life.kobefengfeng.community.community.strategy.UserStrategy;
import life.kobefengfeng.community.community.strategy.UserStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@Slf4j
public class AuthorizeController {
    @Autowired
    private UserStrategyFactory userStrategyFactory;

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientID;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserService userService;

    @GetMapping("/callback/{type}")           //进入登录地址中，因为前文配置了登录状态进入callback地址
    public String newCallback(@PathVariable(name="type")String type,
                           @RequestParam(name="code")String code,
                           @RequestParam(name="state",required = false)String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        UserStrategy userStrategy = userStrategyFactory.getStrategy(type);
        LoginUserInfo loginUserInfo = userStrategy.getUser(code,state);

        if(loginUserInfo != null && loginUserInfo.getId() != null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(loginUserInfo.getName());
            user.setAccountId(String.valueOf(loginUserInfo.getId()));
            user.setAvatarUrl(loginUserInfo.getAvatar_url());
            user.setType(type);
            userService.createOrUpdate(user);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 60 * 24 * 30 * 6);
            cookie.setPath("/");
            response.addCookie(cookie);//并将token信息写入cookie中
            return "redirect:/";
        }
        else{
            //登录失败重新登录
            log.error("callback get github error,{}",loginUserInfo);
            return "redirect:/";
        }
    }

    //退出登录
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        //移除掉session（因为之前本项目的session的就是user，所以此处移除user）  session是通过request获取
        //移除cookie  cookie是通过response设置的

        //用于清除session的所有信息
        request.getSession().invalidate();
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/";
    }
}
