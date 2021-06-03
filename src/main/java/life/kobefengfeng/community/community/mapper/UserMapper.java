package life.kobefengfeng.community.community.mapper;

import life.kobefengfeng.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_mod) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtMod})")
    void insert(User user);
}
