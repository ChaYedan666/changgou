package com.changgou.file.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.file.util.FastDFSClient;
import com.changgou.file.util.FastDFSFile;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author chayedan666
 * @version 1.0
 * @className: FileController
 * @description:
 * @date: 2020/5/2
 */
@RestController
@RequestMapping("/file")
public class FileController {

    // 这里MultipartFile的变量名需要等于页面上传域的名字
    // 例如前端<input type="file" name="file"/>
    // MultipartFile的变量名等于前端name属性的值
    @PostMapping
    public Result fileUpload(MultipartFile file){
        try {
            // 1.获取完整的文件名
            String fileName = file.getOriginalFilename();
            // 2.获取文件拓展名
            if (StringUtils.isEmpty(fileName)){
                throw new RuntimeException("文件不存在");
            }
            String ext = fileName.substring(fileName.lastIndexOf("."));
            // 3.获取文件内容
            byte[] contents = file.getBytes();
            // 4.创建上传文件实体类
            FastDFSFile fastDFSFile = new FastDFSFile(fileName, contents, ext);
            // 5.调用上传文件工具类，上传并返回存储后文件的路径和文件名
            String[] path = FastDFSClient.upload(fastDFSFile);
            // 6.返回上传后的路径和文件名
            String url = FastDFSClient.getTrackerUrl() + path[0] + "/" + path[1];
            return new Result(true, StatusCode.OK,"上传成功", path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(false, StatusCode.ERROR,"上传失败");

    }
}
