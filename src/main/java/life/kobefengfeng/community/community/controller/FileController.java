package life.kobefengfeng.community.community.controller;

import life.kobefengfeng.community.community.dto.FileDTO;
import life.kobefengfeng.community.community.provider.UCloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/27 14:43
 * @Version 1.0
 */
@Controller
public class FileController {
    @Autowired
    private UCloudProvider uCloudProvider;

    @ResponseBody
    @RequestMapping("/file/upload")
    public FileDTO upload(HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");//分段 分片上传
        try {
            uCloudProvider.upload(file.getInputStream(),file.getContentType(),file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);//默认上传成功  message不需要先
        fileDTO.setUrl("/images/img/divideSpace.png");
        return fileDTO;
    }
}
