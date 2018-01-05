package com.forever776.life.admin.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.forever776.life.admin.controller.BaseController;
import com.forever7776.life.core.constants.SystemConstant;
import com.forever7776.life.core.entity.sys.SysFile;
import com.forever7776.life.core.service.qiniu.QiNiuService;
import com.qiniu.common.QiniuException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import util.DateUtil;
import util.FileUtil;

import java.io.IOException;
import java.util.Date;


/**
 * @author kz
 * @date 2017年12月1日14:32:07
 * @desc 七牛上传
 */
@Controller
@RequestMapping(value = "/up")
public class UploadController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private QiNiuService qiNiuService;

    @RequestMapping
    public String index() {
        return "front/index";
    }


    /**
     * 移动文件，要求空间在同一账号下
     *
     * @param fileName    源文件名
     * @param newBucket   目标空间名
     * @param newFileName 目标文件名
     * @throws QiniuException
     */
    public void moveQiNiuFile(String fileName, String newBucket, String newFileName) throws QiniuException {
        qiNiuService.moveFile(SystemConstant.QINIU_BUCKET, fileName, newBucket, newFileName, false);
    }

    /**
     * 根据文件名删除七牛对应的文件
     *
     * @param fileName
     */
    public void delQiNiuFile(String fileName) throws QiniuException {
        qiNiuService.delete(SystemConstant.QINIU_BUCKET, fileName);
    }


    /**
     * 图片上传，采用webUpload单次上传
     *
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "/pic")
    @ResponseBody
    public JSONObject pic(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        long begin = System.currentTimeMillis();
        JSONObject result = new JSONObject();
        if (multipartFile.isEmpty()) {
            result.put("msg", "图片为空");
            result.put("success", false);
            return result;
        }
        String originFileName = multipartFile.getOriginalFilename();
        if (!StringUtils.contains(SystemConstant.QINIU_SUFFIXIMAGE, FileUtil.getFileSuffix(originFileName).toUpperCase())) {
            result.put("msg", "图片类型不对");
            result.put("success", false);
            return result;
        }

        String newFileName = DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS) + SystemConstant.QINIU_FILELINK + originFileName;
        SysFile sysFile = new SysFile();
        sysFile.setFileType(0);
        sysFile.setFileName(originFileName);
        sysFile.setFileSize(new Long(multipartFile.getSize()).intValue());
        qiNiuService.uploadFileByte(multipartFile.getBytes(), newFileName, sysFile);
        logger.info("本次上传耗时：{}ms,文件名：{}，时间：{}", (System.currentTimeMillis() - begin), originFileName, DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));
        return result;
    }
}
