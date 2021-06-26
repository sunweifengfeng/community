package life.kobefengfeng.community.community.controller;

import life.kobefengfeng.community.community.dto.NotificationDTO;
import life.kobefengfeng.community.community.enums.NotificationTypeEnum;
import life.kobefengfeng.community.community.model.User;
import life.kobefengfeng.community.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/26 21:25
 * @Version 1.0
 */
@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")   //下面的方法是一个get方法，所以要用GetMapping，从profile页面去获取信息，网页面写东西并获取用PostMapping
    public String profile(@PathVariable(name = "id") Long id,
                          Model model,
                          HttpServletRequest request){
        //id是从url获取到的
        //获取写入到session中的user对象，由于是Object类型的，要对其进行强制类型转换
        User user = (User) request.getSession().getAttribute("user");
        //如果未登录成功，则回到首页
        if(user == null){
            return "redirect:/";
        }

        //一定要是我自己 不能谁都校验
        NotificationDTO notificationDTO = notificationService.read(id,user);

        if(NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType()
           || NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()){
            return "redirect:/question/"+notificationDTO.getOuterid();
        }else{
            return "redirect:/";
        }
    }
}
