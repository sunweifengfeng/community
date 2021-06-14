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

    public CustomizeException(String message) {
        this.message = message;
    }

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }
    @Override
    public String getMessage() {
        return message;
    }
}
