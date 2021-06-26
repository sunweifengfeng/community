package life.kobefengfeng.community.community.controller;

import life.kobefengfeng.community.community.dto.PaginationDTO;
import life.kobefengfeng.community.community.model.User;
import life.kobefengfeng.community.community.service.NotificationService;
import life.kobefengfeng.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/profile/{action}")   //下面的方法是一个get方法，所以要用GetMapping，从profile页面去获取信息，网页面写东西并获取用PostMapping
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page",defaultValue = "1") Integer page, //页码数   获取页面参数
                          @RequestParam(name = "size",defaultValue = "5") Integer size  //每页的话题数
                          ){  //action是从页面获取到的

        User user = (User) request.getSession().getAttribute("user");//获取写入到session中的user对象，由于是Object类型的，要对其进行强制类型转换
        //如果未登录成功，则回到首页
        if(user == null){
            return "redirect:/";
        }
        //当点击的是我的问题，则显示我的问题在左上角，并显示问题
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
            //查询某个用户创建的所有问题
            PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);//根据用户的id,在question表中查询creator等于id的所有问题的数量，此时id是变换的，所以查不到问题
            model.addAttribute("pagination",paginationDTO);
        }else if("replies".equals(action)){
            //查询显示与用户有关的所有回复内容列表
            PaginationDTO paginationDTO = notificationService.list(user.getId(), page, size);
            Long unreadCount = notificationService.unreadCount(user.getId());
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
            model.addAttribute("unreadCount",unreadCount);
            model.addAttribute("pagination",paginationDTO);

        }
        return "profile";
    }
}
