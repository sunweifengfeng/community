package life.kobefengfeng.community.community.dto;

import lombok.Data;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/16 20:57
 * @Version 1.0
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
