package life.kobefengfeng.community.community.mapper;

import life.kobefengfeng.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
}
