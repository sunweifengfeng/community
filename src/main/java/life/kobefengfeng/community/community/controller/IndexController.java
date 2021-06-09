package life.kobefengfeng.community.community.controller;

import life.kobefengfeng.community.community.dto.PaginationDTO;
import life.kobefengfeng.community.community.dto.QuestionDTO;
import life.kobefengfeng.community.community.mapper.QuestionMapper;
import life.kobefengfeng.community.community.mapper.UserMapper;
import life.kobefengfeng.community.community.model.Question;
import life.kobefengfeng.community.community.model.User;
import life.kobefengfeng.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page, //页码数   获取页面参数
                        @RequestParam(name = "size",defaultValue = "5") Integer size  //每页的话题数
                        ){
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0)
        {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);//从数据库中查找有无此token值
                    if(user != null){
                        request.getSession().setAttribute("user",user);//登录成功，则将user信息写入到session中，在indexhtml中显示了session的user信息
                    }
                    break;
                }
            }
        }
        PaginationDTO pagination = questionService.list(page,size);
        model.addAttribute("pagination",pagination);
        return "index";
    };
}
