package life.kobefengfeng.community.community.controller;

import life.kobefengfeng.community.community.dto.QuestionDTO;
import life.kobefengfeng.community.community.dto.CommentDTO;
import life.kobefengfeng.community.community.service.CommentService;
import life.kobefengfeng.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/10 21:24
 * @Version 1.0
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")//浏览器通过get（查询）方式访问url的地址/question/{id}， 这里写了{参数}，下面的方法中@PathVariable拿到该值，传递到下面
    public String question(@PathVariable(name = "id") Long id,
                           Model model){
        //希望从数据库中查询到，并且传到页面总去
        QuestionDTO questionDTO = questionService.getById(id);//传递给questionDTO是因为其内容里比较丰富，相比较与model中的question

        List<CommentDTO> comments = commentService.ListByQuestionId(id); //根据问题id查询评论列表
        //累加阅读数
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);//将评论渲染到页面显示
        return "question";
    }
}
