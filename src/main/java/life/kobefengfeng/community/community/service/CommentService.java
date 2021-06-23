package life.kobefengfeng.community.community.service;

import life.kobefengfeng.community.community.dto.CommentDTO;
import life.kobefengfeng.community.community.enums.CommentTypeEnum;
import life.kobefengfeng.community.community.exception.CustomizeErrorCode;
import life.kobefengfeng.community.community.exception.CustomizeException;
import life.kobefengfeng.community.community.mapper.*;
import life.kobefengfeng.community.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/17 22:00
 * @Version 1.0
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private CommentExtMapper commentExtMapper;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void insert(Comment comment) {
        if(comment.getParentId() == null || comment.getParentId() == 0){
            //抛出异常，期待最外层CommentController拦住exception
            //进入处理异常的方法CustomizeExceptionHandler
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        //当前端传来的JSON的type不存在 或者不匹配(回复评论和回复问题时) 抛出异常
        if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if(comment.getType() == CommentTypeEnum.COMMENT.getType()){
            //回复评论
            //该方法用于查找满足要求字段的数据库行  数据中可能有多个，但是只要有就插入评论 没有就抛出异常
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }else {
                commentMapper.insert(comment);
                //增加评论的评论数
                dbComment.setCommentCount(1);
                commentExtMapper.incCommentCount(dbComment);
            }
        }else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            //问题不存在 抛出异常
            if(question == null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            //否则 问题存在 插入评论
            commentMapper.insert(comment);
            //增加问题的评论数
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }
    }

    //根据问题id去评论数据库中查找评论。并附加user信息，返回到CommentDTO
    public List<CommentDTO> ListByTargetId(Long id, CommentTypeEnum type) {
        //首先去commentMapper中查评论
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create desc");//按创建时间的倒序排列
        List<Comment> comments = commentMapper.selectByExample(commentExample);


        if(comments.size() == 0){
            return new ArrayList<>();
        }
        //使用lambda 获取去重评论人  获取数据表的一列数据 因为一个人可能会评论多条 所以有重复的creator 且creator中存储的是user的id
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());

        //将commentators转换为userIds 的list的形式
       List<Long> userIds = new ArrayList<>();
       userIds.addAll(commentators);

       //获取评论人并转换为Map Map可以降低时间复杂度

       //在user数据库中查找id为userIds的所有user对象
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);//该方法是传入数据库中一列元素 然后去寻找满足条件的多行
        List<User> users = userMapper.selectByExample(userExample);
        //将users转换为map形式 为了避免将users放入comment中时需要for循环的问题
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        //  转换comment为commentDTO 将userMap放入commentDTO 中
        List<CommentDTO> commentDTOs = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);//将comment转为commentDTO
            commentDTO.setUser(userMap.get(comment.getCommentator()));//多层嵌套 设置每一个CommentDTO的User 使用的额方法是将UserId对应的User与CommentDTO对应起来
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOs;
    }
}
