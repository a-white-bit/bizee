package com.sparta.bizee.controller;

import com.sparta.bizee.exception.InvalidFileTypeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Controller
public class FileController {

    // **application.properties 에 file.upload-dir=upload 설정하기
    // 설정이 없을 때 (전역)user 폴더 위치에 생성 (전혀 다른 곳!)
    @Value("${file.upload-dir:${user.home}}")
    private String uploadDir;

    @ResponseBody
    @PostMapping("/upload")
    public String imageUpload(@RequestParam("image") MultipartFile file) throws InvalidFileTypeException, IOException {

        // 확장자 구별
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        if (!"jpg".equals(extension) && !"jpeg".equals(extension) && !"png".equals(extension)) {
            throw new InvalidFileTypeException("이미지 파일이 아닙니다.");
        }

        // 저장할 파일 경로(path) 만들기
        // File.seperator: OS에 따라 경로 구분자 '/' or '\' ..
        // Spring에서 제공하는 cleanPath()를 통해서 ../ 내부 점들에 대해서 사용을 억제하는 방법?
        Path copyOfLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
        try {

            // MultipartFile은 파일은 청크단위로 쪼갠다. --> stream 사용
            // copyOfLocation (경로)에 write file
            // copy()의 옵션: 기존에 존재하면 덮어쓴다 (REPLACE_EXISTING)
            Files.copy(file.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.getStackTrace();
            throw new IOException("파일 저장에 실패했습니다. :" + file.getOriginalFilename());
        }
        return "서버에 저장된 파일: " + file.getOriginalFilename();
    }

}