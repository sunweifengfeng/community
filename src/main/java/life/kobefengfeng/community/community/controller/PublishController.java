package life.kobefengfeng.community.community.controller;

import life.kobefengfeng.community.community.mapper.QuestionMapper;
import life.kobefengfeng.community.community.model.Question;
import life.kobefengfeng.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")//request get读取页面
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")//Post response 页面返回来的信息写入
    public String doPublish(
            @RequestParam("title") String title,//括号内的title类型是String类型的，获得的页面的输入的信息
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model
    ){
        model.addAttribute("title",title);//model可以将title的值写入"title"中，并在html文件中获取到，可以写出来
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(title == null || title == ""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description == null || description == ""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        if(tag == null || tag == ""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        //获取写入到session中的user对象，由于是Object类型的，要对其进行强制类型转换
        User user = (User) request.getSession().getAttribute("user");
        //发布失败
        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";//发布失败还在本页面
        }
        //发布成功
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());//将问题创建者与用户的id绑定
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtMod(question.getGmtCreate());

        questionMapper.create(question);
        return "redirect:/";//发布成功返回首页
    }
}
