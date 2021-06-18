package life.kobefengfeng.community.community.dto;

import life.kobefengfeng.community.community.model.User;
import lombok.Data;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/6 11:43
 * @Version 1.0
 */
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtMod;
    private Long Creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
