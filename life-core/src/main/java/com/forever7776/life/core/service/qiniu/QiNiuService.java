package com.forever7776.life.core.service.qiniu;

import com.alibaba.fastjson.JSONObject;
import com.forever7776.life.core.common.enums.QiNiuEnum;
import com.forever7776.life.core.entity.sys.SysFile;
import com.forever7776.life.core.service.SysFileService;
import com.qiniu.common.QiniuException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qiniu.QiNiuApi;
import util.ConfigTool;
import util.FileUtil;

import java.io.File;
import java.util.Date;

/**
 * 七牛上传辅助类
 *
 * @author kz
 */
@Service
public class QiNiuService {
    private static Logger logger = LoggerFactory.getLogger(QiNiuService.class);
    private static String domain = StringUtils.EMPTY;

    @Autowired
    private SysFileService sysFileService;

    public static String url(String filename) {
        try {
            if (StringUtils.isBlank(domain)) {
                domain = ConfigTool.getProp("qiniu.domain");
            }
            return "http://" + domain + "/" + new URLCodec("UTF-8").encode(filename).replace("+", "%20");
        } catch (Exception e) {
            logger.error("QiNiuService@url error", e);
            return StringUtils.EMPTY;
        }
    }

    public void run(File file, SysFile sysFile) {
        Thread upThread = new Thread(new UploadProcess(file, sysFile));
        upThread.start();
        logger.info("QiNiu file-Thread[" + upThread.getId() + "] begin on");
    }

    public boolean upload(File file, SysFile sysFile) {
        try {
            JSONObject jo = QiNiuApi.upload(file);
            save(jo, sysFile);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("uploadProcess@QiNiuUpload save model error", e);
        }

        return false;
    }

    public boolean uploadFileByte(byte[] fileByte, String fileKey, SysFile sysFile) {
        try {
            JSONObject jo = QiNiuApi.uploadFileByte(fileByte, fileKey);
            save(jo, sysFile);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("uploadProcess@QiNiuUpload save model error", e);
        }

        return false;
    }

    public void save(JSONObject jo, SysFile sysFile) {
        if ("success".equals(jo.getString("result_code"))) {
            String key = jo.getString("key");
            sysFile.setFileKey(key);
            sysFile.setFileStatus(QiNiuEnum.STATUS.SUCCESS.getKey());
            sysFile.setFileSuffix(FileUtil.getFileSuffix(key));
            sysFile.setInsertDate(new Date());
            logger.info("[upload]上传结果返回-->key:" + key);
        } else {
            logger.info("[upload]上传失败");
            sysFile.setFileStatus(QiNiuEnum.STATUS.FAIL.getKey());
        }
        if (sysFileService != null) {
            sysFileService.save(sysFile);
        }

    }

    class UploadProcess implements Runnable {
        private File file;
        private SysFile sysFile;

        public UploadProcess(File file, SysFile sysFile) {
            this.file = file;
            this.sysFile = sysFile;
        }

        @Override
        public void run() {
            upload(file, sysFile);
        }
    }


    /**
     * 移动文件，要求空间在同一账号下
     *
     * @param fromBucket  源空间名称
     * @param fromFileKey 源文件名称
     * @param toBucket    目的空间名称
     * @param toFileKey   目的文件名称
     * @param force       强制覆盖空间中已有同名（和 toFileKey 相同）的文件
     * @throws QiniuException
     */
    public void moveFile(String fromBucket, String fromFileKey, String toBucket, String toFileKey, boolean force) throws QiniuException {
        QiNiuApi.moveFile(fromBucket, fromFileKey, toBucket, toFileKey, force);
    }

    /**
     * 删除指定空间、文件名的文件
     *
     * @param bucket 空间名称
     * @param key    文件名称
     * @throws QiniuException
     * @link http://developer.qiniu.com/kodo/api/delete
     */
    public void delete(String bucket, String key) throws QiniuException {
        QiNiuApi.delete(bucket, key);
    }
}
