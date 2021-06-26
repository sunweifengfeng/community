package life.kobefengfeng.community.community.enums;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/26 15:00
 * @Version 1.0
 */
public enum NotificationStatusEnum {
    UNREAD(0),READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
