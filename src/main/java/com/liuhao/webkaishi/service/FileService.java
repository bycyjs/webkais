package com.liuhao.webkaishi.service;

import com.liuhao.webkaishi.common.R;
import com.liuhao.webkaishi.entity.File;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    R fileupload(HttpServletRequest request, @RequestBody MultipartFile myFile) throws IOException;
    R downloadFile(HttpServletRequest request);
    R deleteFile(HttpServletRequest request,File file);
}
