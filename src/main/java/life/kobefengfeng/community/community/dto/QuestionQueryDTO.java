package life.kobefengfeng.community.community.dto;

import lombok.Data;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/7/1 19:57
 * @Version 1.0
 */
@Data
public class QuestionQueryDTO {
    private String search;
    private Integer page;
    private Integer size;
}
