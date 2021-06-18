package life.kobefengfeng.community.community.exception;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/14 17:24
 * @Version 1.0
 */
//这里继承RuntimeException的作用是为了不在别的层用try catch 去处理异常，只想在controller中去捕获异常
public class CustomizeException extends RuntimeException {
    private String message;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode)
    {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
