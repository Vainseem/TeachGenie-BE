package com.ai.controller;

import com.ai.entity.RestBean;
import com.ai.entity.dto.Detail;
import com.ai.service.DetailService;
import com.ai.utils.Const;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/detail")
public class DetailController {
    @Resource
    DetailService detailService;

    @GetMapping("/fetch/{uid}")
    public String getDetail(@PathVariable("uid") String uid) {
        Detail detail = detailService.getDetail(uid);
        return detail == null ?
                RestBean.failure(400, "请先提交details").asJsonString()
                : RestBean.success(detail).asJsonString();
    }

    @PostMapping("/update/{uid}")
    public String updateDetail(
            @PathVariable("uid") String uid,
            @RequestBody Detail detail
    ) {
        detailService.updateDetail(uid, detail);
        return RestBean.success("成功更新details").asJsonString();
    }

    @PostMapping("/updatePhoto/{uid}")
    public String updatePhoto(
            @PathVariable("uid") String uid,
            @RequestParam("photo") MultipartFile photo
    ) {
        if (photo.isEmpty()) {
            return RestBean.failure(400, "上传失败，图片为空").asJsonString();
        }

        Path path = Path.of(Const.PHOTO_PATH + uid + ".jpg");
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            return RestBean.failure(400, "上传失败，创建文件夹失败").asJsonString();
        }

        try {
            photo.transferTo(path);
        } catch (IOException e) {
            return RestBean.failure(400, "上传失败，保存图片失败").asJsonString();
        }

        return RestBean.success("成功更新头像").asJsonString();
    }

    // @GetMapping("/getPhoto/{uid}")
    // public ResponseEntity<byte[]> getPhoto(@PathVariable("uid") String uid) {
    //     Path path = Path.of(Const.PHOTO_PATH + uid + ".jpg");
    //     try {
    //         byte[] photo = Files.readAllBytes(path);
    //         return ResponseEntity
    //                 .ok()
    //                 .header("Content-Type", "image/jpeg")
    //                 .body(photo);
    //
    //     } catch (IOException e) {
    //         try {
    //             Path errorPath = Path.of(Const.ERROR_PHOTO_PATH);
    //             byte[] photo = Files.readAllBytes(errorPath);
    //             return ResponseEntity
    //                     .ok()
    //                     .header("Content-Type", "image/jpeg")
    //                     .body(photo);
    //         } catch (IOException ex) {
    //             return ResponseEntity.notFound().build();
    //         }
    //     }
    // }

}
