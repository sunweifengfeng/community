package life.kobefengfeng.community.community.controller;

import life.kobefengfeng.community.community.dto.PaginationDTO;
import life.kobefengfeng.community.community.mapper.UserMapper;
import life.kobefengfeng.community.community.model.User;
import life.kobefengfeng.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/9 19:27
 * @Version 1.0
 */
@Controller
public class ProfileController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")   //下面的方法是一个get方法，所以要用GetMapping，从profile页面去获取信息，网页面写东西并获取用PostMapping
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page",defaultValue = "1") Integer page, //页码数   获取页面参数
                          @RequestParam(name = "size",defaultValue = "5") Integer size  //每页的话题数
                          ){  //action是从页面获取到的
        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0)
        {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);//从数据库中查找有无此token值
                    if(user != null){
                        request.getSession().setAttribute("user",user);//登录成功，则将user信息写入到session中，在indexhtml中显示了session的user信息
                    }
                    break;
                }
            }
        }
        //如果未登录成功，则回到首页
        if(user == null){
            return "redirect:/";
        }
        //当点击的是我的问题，则显示我的问题在左上角，并显示问题
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }

        PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination",paginationDTO);
        return "profile";
    }
}
