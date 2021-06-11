package life.kobefengfeng.community.community.mapper;

import life.kobefengfeng.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

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

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator = #{id} limit #{offset},#{size}")
    List<Question> listById(@Param(value = "id") Integer id, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question where creator = #{id}")
    Integer countById(@Param(value = "id") Integer id);

    @Select("select * from question where id = #{id}")//where后的id是question表中的字段名id，#{id}是下方传入的参数id
    Question getById(@Param("id") Integer id);
}
