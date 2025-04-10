package org.example.vue3manager.controller.test;

import jakarta.annotation.Resource;
import java.io.IOException;
import org.example.vue3manager.common.exception.OssOperateException;
import org.example.vue3manager.common.response.Response;
import org.example.vue3manager.core.oss.EasyOss;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/test")
public class TestController {

  @Resource
  private EasyOss easyOss;

  @PostMapping("/upload")
  public Response uploadFile(@RequestParam("file") MultipartFile file) throws IOException, OssOperateException {
    String path = easyOss.uploadFile(file.getBytes());
    return Response.ok().msg(path).build();
  }
}
