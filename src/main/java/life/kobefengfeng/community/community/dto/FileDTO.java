package life.kobefengfeng.community.community.dto;

import lombok.Data;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/27 14:43
 * @Version 1.0
 */
@Data
public class FileDTO {
    private int success;//用于返回到前端的JSON格式
    private String message;
    private String url;
}
