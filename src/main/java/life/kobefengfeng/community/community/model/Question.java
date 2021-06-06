package life.kobefengfeng.community.community.model;

import lombok.Data;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/5 17:26
 * @Version 1.0
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtMod;
    private Integer Creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
}
