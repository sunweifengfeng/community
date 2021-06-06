package life.kobefengfeng.community.community.mapper;

import life.kobefengfeng.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/5 17:21
 * @Version 1.0
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_mod,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtMod},#{creator},#{tag}) ")
    void create(Question question);

    @Select("select * from question")
    List<Question> list();
}
