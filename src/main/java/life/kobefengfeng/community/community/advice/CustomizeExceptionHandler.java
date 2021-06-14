package life.kobefengfeng.community.community.advice;

import life.kobefengfeng.community.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
    ModelAndView handle(HttpServletRequest request, Throwable e, Model model) {
        if(e instanceof CustomizeException){//是能够处理的异常
            model.addAttribute("message",e.getMessage());
        }else {
            model.addAttribute("message","服务冒烟了，稍后再试");//异常确实处理不了
        }
        return new ModelAndView("error");//如果出现错误，就返回到我们刚才定义的error页面
    }
}
