package life.kobefengfeng.community.community.exception;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/14 17:42
 * @Version 1.0
 */
//枚举类需要定义构造方法，才会出现枚举实例
//错误信息都放在枚举类中，增加可重用性
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"你找的问题不在了，要不要换个问题试试"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题进行回复"),
    NO_LOGIN(2003,"当前操作需要登录请登录后重试"),
    SYSTEM_ERROR(2004,"服务冒烟了，稍后再试"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在了"),
    CONTENT_NOT_EMPTY(2006,"输入内容不能为空")
    ;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    private String message;
    private Integer code;
    //调用CustomizeErrorCode.QUESTION_NOT_FOUND时调用该构造方法  QUESTION_NOT_FOUND是该类实例

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
