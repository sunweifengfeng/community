package life.kobefengfeng.community.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/25 22:44
 * @Version 1.0
 */
@Data
public class TagDTO {
    private String categoryName;//种类名
    private List<String> tags;//里面所有的tag
}
