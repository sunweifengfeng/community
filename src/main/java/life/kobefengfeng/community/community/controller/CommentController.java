package life.kobefengfeng.community.community.controller;

import life.kobefengfeng.community.community.dto.CommentCreateDTO;
import life.kobefengfeng.community.community.dto.CommentDTO;
import life.kobefengfeng.community.community.dto.ResultDTO;
import life.kobefengfeng.community.community.enums.CommentTypeEnum;
import life.kobefengfeng.community.community.exception.CustomizeErrorCode;
import life.kobefengfeng.community.community.model.Comment;
import life.kobefengfeng.community.community.model.User;
import life.kobefengfeng.community.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/16 20:33
 * @Version 1.0
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    //前端向服务端的question进行评论 post服务端JSON对象  服务端添加完评论信息后 返回前端JSON 显示一级评论列表
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,//RequestBody自动将我们传递过来的JSON的key和v赋值到CommentDTO中
                       HttpServletRequest request){ //定义HttpServletRequest request的目的是拿到user
       //判断是否登录
        //未登录
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        //输入内容不能为空 抛出异常
        if(commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())){//要用equals
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_NOT_EMPTY);
        }
        //已登录
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtMod(System.currentTimeMillis());
        comment.setCommentator(user.getId());//评论人的id
        comment.setLikeCount(0L);//long类型要以L结尾
        commentService.insert(comment,user);//存入数据库
        return ResultDTO.okOf();//返回的值是community.js中success中的response获取的
    }

    //获取二级评论
    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id){//拿到子集评论的id
        List<CommentDTO> commentDTOS = commentService.ListByTargetId(id, CommentTypeEnum.COMMENT);//获取type类型是评论的id的comments
        return ResultDTO.okOf(commentDTOS);
    }
}
