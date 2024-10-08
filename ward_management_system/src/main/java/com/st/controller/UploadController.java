package com.st.controller;


import com.st.bean.RespBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/user/upload")
public class UploadController {
    @Value("${stdeu.picDir}")
    private String picDir;

    @PostMapping
    public RespBean upload(MultipartFile pic) throws IOException {
        //获取图片的名字
        String filename = pic.getOriginalFilename();
        //保证图片名字唯一
        filename = UUID.randomUUID().toString().replace("-", "") + "_" + filename;
        //将图片保存在服务器
        //pic.transferTo(new File(picDir + filename));
        // 获取项目根目录的相对路径
        String projectRootPath = System.getProperty("user.dir");
        File uploadDir = new File(projectRootPath, picDir);
        File dest = new File(uploadDir, filename);
        pic.transferTo(dest);

        return RespBean.ok("上传成功", filename);
    }
}
