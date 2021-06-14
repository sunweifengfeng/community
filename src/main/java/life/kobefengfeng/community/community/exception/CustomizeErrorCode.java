package life.kobefengfeng.community.community.exception;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/14 17:42
 * @Version 1.0
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND("你找的问题不在了，要不要换个问题试试");

    @Override
    public String getMessage() {
        return message;
    }
    private String message;
    //调用CustomizeErrorCode.QUESTION_NOT_FOUND时调用该构造方法  QUESTION_NOT_FOUND是该类实例
    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
