package life.kobefengfeng.community.community.dto;

import life.kobefengfeng.community.community.model.User;
import lombok.Data;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/26 15:57
 * @Version 1.0
 */
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;//通知者
    private String notifierName;//通知者
    private Long outerid;
    private String outerTitle;//通知的标题
    private String typeName;
    private Integer type;
}
