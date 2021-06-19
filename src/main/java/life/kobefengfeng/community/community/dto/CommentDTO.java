package life.kobefengfeng.community.community.dto;

import life.kobefengfeng.community.community.model.User;
import lombok.Data;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/19 17:57
 * @Version 1.0
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtMod;
    private Long likeCount;
    private String content;
    private User user;
}
