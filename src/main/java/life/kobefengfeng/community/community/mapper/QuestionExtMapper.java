package life.kobefengfeng.community.community.mapper;

import life.kobefengfeng.community.community.model.Question;
import life.kobefengfeng.community.community.model.QuestionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface QuestionExtMapper {
    int incView(Question record);
}