package com.atguigu.controller;

import base.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.service.HouseImageService;
import entity.HouseImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import result.Result;
import util.QiniuUtils;

import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("/houseImage")
public class HouseImageController extends BaseController {

    @Reference
    private HouseImageService houseImageService;


    @RequestMapping("/uploadShow/{houseId}/{type}")
    public String uploadShow(@PathVariable Long houseId, @PathVariable Integer type, Map map) {
        map.put("houseId", houseId);
        map.put("type", type);

        return "house/upload";
    }

    ;

    @RequestMapping("/upload/{houseId}/{type}")
    @ResponseBody
    public Result upload(@PathVariable Long houseId, @PathVariable Integer type, @RequestParam("file") MultipartFile[] files) throws Exception {
        if (files.length > 0) {
            for (MultipartFile file : files) {
                String newFileName = UUID.randomUUID().toString() + file.getOriginalFilename();
                byte[] fileBytes = file.getBytes();
                QiniuUtils.upload2Qiniu(fileBytes, newFileName);

                String url = "http://rhfg61j0e.hn-bkt.clouddn.com/" + newFileName;
                HouseImage houseImage = new HouseImage();
                houseImage.setHouseId(houseId);
                houseImage.setType(type);
                houseImage.setImageUrl(url);
                houseImage.setImageName(file.getOriginalFilename());
                houseImageService.insert(houseImage);

            }
        }
        return Result.ok();


    }


}
