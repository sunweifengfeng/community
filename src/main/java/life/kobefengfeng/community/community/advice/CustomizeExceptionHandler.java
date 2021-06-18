package life.kobefengfeng.community.community.advice;

import com.alibaba.fastjson.JSON;
import life.kobefengfeng.community.community.dto.ResultDTO;
import life.kobefengfeng.community.community.exception.CustomizeErrorCode;
import life.kobefengfeng.community.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/14 17:02
 * @Version 1.0
 */
@ControllerAdvice  //扫描所有异常
public class CustomizeExceptionHandler {
//希望出现错误时自动跳转到我们刚才定义的一个页面
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable e, Model model, HttpServletResponse response) {
        String contentType = request.getContentType();
        //回复问题的错误 返回JSON
        if("application/json".equals(contentType)){
            //返回json
            ResultDTO resultDTO;
            if(e instanceof CustomizeException){//是能够处理的异常
                resultDTO = ResultDTO.errorOf((CustomizeException)e);
            }else {
                resultDTO =  ResultDTO.errorOf(CustomizeErrorCode.SYSTEM_ERROR);//异常确实处理不了
            }
            try{
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();//通过response强制写resultDTO到页面
                writer.write(JSON.toJSONString(resultDTO));//由于不能用Spring了，要强转为JSON对象
                writer.close();//关闭流
            } catch (IOException ex) {
            }
            return null;
        }else{ //其他错误
            //错误页面跳转页面跳转
            if(e instanceof CustomizeException){//是能够处理的异常
                model.addAttribute("message",e.getMessage());
            }else {
                model.addAttribute("message",CustomizeErrorCode.SYSTEM_ERROR.getMessage());//异常确实处理不了
            }
            return new ModelAndView("error");//如果出现错误，就返回到我们刚才定义的error页面
        }
    }
}
