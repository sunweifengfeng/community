package life.kobefengfeng.community.community.enums;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/26 14:44
 * @Version 1.0
 */
public enum NotificationTypeEnum {
    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(2,"回复了评论");

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int type;
    private String name;

    NotificationTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    //返回通知的类型
    public static String nameOfType(int Type){
        for (NotificationTypeEnum notificationTypeEnum : NotificationTypeEnum.values()) {
            if(notificationTypeEnum.getType() == Type){
                return notificationTypeEnum.getName();
            }
        }
        return "";
    }
}
