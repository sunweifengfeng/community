package life.kobefengfeng.community.community.mapper;

import life.kobefengfeng.community.community.dto.QuestionQueryDTO;
import life.kobefengfeng.community.community.model.Question;
import life.kobefengfeng.community.community.model.QuestionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
    List<Question> selectRelated(Question question);

    //根据page size 和search搜索问题
    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}