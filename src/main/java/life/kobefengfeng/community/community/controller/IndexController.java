package life.kobefengfeng.community.community.controller;

import life.kobefengfeng.community.community.cache.HotTagCache;
import life.kobefengfeng.community.community.dto.PaginationDTO;
import life.kobefengfeng.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private HotTagCache hotTagCache;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page, //页码数   获取页面参数
                        @RequestParam(name = "size",defaultValue = "5") Integer size,  //每页的话题数
                        @RequestParam(name = "search",required = false) String search,  //搜索功能
                        @RequestParam(name = "tag",required = false) String tag  //接收url传递过来的tag
                        ){

        PaginationDTO pagination = questionService.list(page,size,search,tag);
        List<String> tags = hotTagCache.getHots();
        model.addAttribute("pagination",pagination);
        model.addAttribute("search",search);
        model.addAttribute("tag",tag);
        model.addAttribute("tags",tags);
        return "index";
    };
}
