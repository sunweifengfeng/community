package life.kobefengfeng.community.community.controller;

import life.kobefengfeng.community.community.dto.PaginationDTO;
import life.kobefengfeng.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page, //页码数   获取页面参数
                        @RequestParam(name = "size",defaultValue = "5") Integer size  //每页的话题数
                        ){

        PaginationDTO pagination = questionService.list(page,size);
        model.addAttribute("pagination",pagination);
        return "index";
    };
}
