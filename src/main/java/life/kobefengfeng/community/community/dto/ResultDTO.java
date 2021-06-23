package life.kobefengfeng.community.community.dto;

import life.kobefengfeng.community.community.exception.CustomizeErrorCode;
import life.kobefengfeng.community.community.exception.CustomizeException;
import lombok.Data;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/17 21:42
 * @Version 1.0
 */
@Data
public class ResultDTO<T> {
    private Integer code;
    private String message;
    private  T data;

    //返回错误信息
    public static ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(),errorCode.getMessage());
    }

    public static ResultDTO errorOf(CustomizeException e) {
        return errorOf(e.getCode(),e.getMessage());
    }

    public static ResultDTO okOf(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public static <T> ResultDTO okOf(T t){/*okOf参数是泛型的 且返回值也是泛型的*/
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        resultDTO.setData(t);
        return resultDTO;
    }
}
