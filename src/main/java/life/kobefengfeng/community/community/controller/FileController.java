package life.kobefengfeng.community.community.controller;

import life.kobefengfeng.community.community.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/27 14:43
 * @Version 1.0
 */
@Controller
public class FileController {
    @ResponseBody
    @RequestMapping("/file/upload")
    public FileDTO upload(){
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);//默认上传成功  message不需要先
        fileDTO.setUrl("/images/img/divideSpace.png");
        return fileDTO;
    }
}
