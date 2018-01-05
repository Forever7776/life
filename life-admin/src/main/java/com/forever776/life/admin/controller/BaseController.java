package com.forever776.life.admin.controller;

import com.forever7776.life.core.constants.CoreConstants;
import com.forever7776.life.core.entity.sys.SysUser;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author kz
 * @date 2017年12月1日14:32:07
 */
public class BaseController {
    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = false)
    protected HttpServletRequest request;

    @Autowired(required = false)
    protected HttpServletResponse response;

    @ModelAttribute
    protected void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public SysUser getSysUser() {
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return sysUser == null ? null : sysUser;
    }

    public String getUserId() {
        return getSysUser().getId();
    }

    public String getUserName() {
        return getSysUser().getUsername();
    }

    public String getNickName() {
        return getSysUser().getNickname();
    }


    /**
     * 上传为空文件
     *
     * @param multipartFile
     * @return
     */
    protected boolean isEmptyFile(MultipartFile multipartFile) {
        return multipartFile == null || multipartFile.getSize() == 0L;
    }

    /**
     * 文件大小效验
     *
     * @param multipartFile
     * @return
     */
    protected boolean fileSizeValidator(MultipartFile multipartFile) {
        if (null != multipartFile) {
            try {
                return (multipartFile.getBytes().length <= CoreConstants.SIGN_FILE_MAX_SIZE);
            } catch (IOException e) {
                logger.error("文件上传异常", e);
            }
        }
        return true;
    }

    protected OutputStream getExcelOutputStream(String title, HttpServletResponse response) {
        try {
            OutputStream os = response.getOutputStream();
            response.reset();
            // 设定输出文件头
            response.setHeader("Content-disposition", "attachment; filename=" + new String(title.getBytes("UTF-8"), "ISO8859-1") + ".xls");
            response.setContentType("application/msexcel");
            return os;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void writeImageOutputStream(String title, byte[] bytes, HttpServletResponse response) {
        try {
            OutputStream os = response.getOutputStream();
            response.reset();
            // 设定输出文件头
            response.setHeader("Content-disposition", "attachment; filename=" + new String(title.getBytes("UTF-8"), "ISO8859-1") + ".png");
            response.setContentType("application/echartsPng");
            os.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private <T> T getFromSession(HttpServletRequest request, String key) {
        Object object = request.getSession().getAttribute(key);
        if (object == null) {
            return null;
        }
        return (T) object;
    }

    protected void removeSession(HttpServletRequest request, String key) {
        request.getSession().removeAttribute(key);
    }
}
