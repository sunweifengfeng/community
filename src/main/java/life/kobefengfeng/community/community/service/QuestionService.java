package life.kobefengfeng.community.community.service;

import life.kobefengfeng.community.community.dto.PaginationDTO;
import life.kobefengfeng.community.community.dto.QuestionDTO;
import life.kobefengfeng.community.community.exception.CustomizeErrorCode;
import life.kobefengfeng.community.community.exception.CustomizeException;
import life.kobefengfeng.community.community.mapper.QuestionMapper;
import life.kobefengfeng.community.community.mapper.UserMapper;
import life.kobefengfeng.community.community.model.Question;
import life.kobefengfeng.community.community.model.QuestionExample;
import life.kobefengfeng.community.community.model.User;
import org.apache.ibatis.session.RowBounds;
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
//查询数据库
    public PaginationDTO list(Integer page, Integer size) {

        //查询数据库之前判断页面是否符合要求
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());
        Integer totalPage;
        if(totalCount % size == 0){
            totalPage = totalCount / size;
        }else{
            totalPage = totalCount / size + 1;
        }

        if(page<1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);//创建一个方法，根据2个参数计算页面展示所需要的元素
        //size*(page-1)
        Integer offset = size*(page-1);
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(),new RowBounds(offset,size));//查到所有question对象
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        //questionDTO的建立就是比question多了一个user  是为了查询user的avatarUrl
        for (Question question : questions) {            //循环question对象
            User user = userMapper.selectByPrimaryKey(question.getCreator());//根据创建者问题的creator在user表中查询id号 就是avatar_url的id，返回user对象
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//把question里的所有对象放到questionDTO中
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(Integer id, Integer page, Integer size) {
        //查询数据库之前判断页面是否符合要求
        PaginationDTO paginationDTO = new PaginationDTO();
        //根据用户的id,在question表中查询creator等于id的所有问题的数量
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(id);
        Integer totalCount = (int) questionMapper.countByExample(questionExample);
        Integer totalPage;
        if(totalCount % size == 0){
            totalPage = totalCount / size;
        }else{
            totalPage = totalCount / size + 1;
        }

        if(page<1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage,page);//创建一个方法，根据三个参数计算页面展示所需要的元素

        //size*(page-1)
        Integer offset = size*(page-1);
        //查到所有question对象
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(id);
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(example,new RowBounds(offset,size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        //questionDTO的建立就是比question多了一个user  是为了查询user的avatarUrl
        for (Question question : questions) {            //循环question对象
            User user = userMapper.selectByPrimaryKey(question.getCreator());//根据创建者问题的creator在user表中查询id号 就是avatar_url的id，返回user对象
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//把question里的所有对象放到questionDTO中
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);//
        if(question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);//把question里的所有对象放到questionDTO中
        User user = userMapper.selectByPrimaryKey(question.getCreator());//根据创建者问题的creator在user表中查询id号 就是avatar_url的id，返回user对象
        questionDTO.setUser(user);
        return questionDTO;

    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtMod(question.getGmtCreate());
            questionMapper.insert(question);
        }else{
            //更新
            Question updateQuestion = new Question();
            updateQuestion.setGmtMod(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria()
                    .andCreatorEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQuestion, example);//第一个参数是更新得对象的信息，第二个参数是根据id寻找到的要更新的对象
            //判断是否更新了
            if(updated != 1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }
}
