package life.kobefengfeng.community.community.service;

import life.kobefengfeng.community.community.dto.QuestionDTO;
import life.kobefengfeng.community.community.mapper.QuestionMapper;
import life.kobefengfeng.community.community.mapper.UserMapper;
import life.kobefengfeng.community.community.model.Question;
import life.kobefengfeng.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/6 11:49
 * @Version 1.0
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;//需要依赖index以来的东西，questionMapper和userMapper

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();//查到所有question对象
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {            //循环question对象
            User user = userMapper.findByID(question.getCreator());//根据创建者问题的creator在user表中查询id号 就是avatar_url的id，返回user对象
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//把question里的所有对象放到questionDTO中
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
