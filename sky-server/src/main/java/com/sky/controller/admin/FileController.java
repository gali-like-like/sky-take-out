package com.sky.controller.admin;


import com.sky.result.Result;
import com.sky.service.impl.FileService;
import com.sky.util.PathUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/file")
@Api(tags = "文件上传与下载")
public class FileController {
    @Resource
    private FileService fileService;

    /**
     * 文件上传
     */
    @PostMapping("/multi/upload")
    @ApiOperation(value = "多文件上传")
    public Result<?> upload(HttpServletRequest request,
                            @RequestParam("file") MultipartFile[] fileList) throws Exception {
        // String uploadUser = request.getParameter("uploadUser");
        // if (uploadUser.isEmpty()) {
        //     return CommonResult.failed("upload-user is empty");
        // }
        // log.info("upload-user:{}", uploadUser);
        List<HashMap<String, String>> urlList = new ArrayList<>();
        for (MultipartFile multipartFile : fileList) {
            ArrayList<HashMap<String, String>> item = new ArrayList<>();
            // 解析文件信息和保存
            String originalFilename = multipartFile.getOriginalFilename();
            if (originalFilename == null) {
                return Result.error("file name has mistake");
            }
            String filePath = PathUtils.generateFilePath(originalFilename);
            String url = fileService.uploadOss(multipartFile, filePath);
            HashMap<String, String> uploadInfo = new HashMap<>();
            uploadInfo.put("name", originalFilename);
            uploadInfo.put("url", url);
            urlList.add(uploadInfo);
        }
        return Result.success(urlList);
    }


    /**
     * 文件下载
     */
    @GetMapping("/single/download")
    @ApiOperation(value = "单文件下载")
    public Result<?> upload(@RequestParam("fileName") String fileName,
                                  HttpServletResponse response) throws Exception {
        return null;
    }
}
