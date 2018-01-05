package com.forever776.life.admin.controller.api;

import com.forever7776.life.remote.common.vo.ResultVO;
import com.forever7776.life.remote.rpc.SendEmailRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.RandomValidateCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 发送邮件
 *
 * @author kz
 * @date 2017-12-19
 */
@Controller
@RequestMapping("/send/email")
public class SendEmailController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SendEmailRpcService sendEmailRpcService;
    @Autowired
    private CaptchaController captchaController;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送纯文本的简单邮件
     *
     * @param email
     * @param request
     * @param response
     * @return
     */
    @RequestMapping
    @ResponseBody
    public ResultVO index(String email, HttpServletRequest request, HttpServletResponse response) {
        captchaController.captcha(request, response);
        Object jo = request.getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY);
        if (jo == null) {
            return null;
        }
        String code = jo.toString();
        String subject = "注册账号";
        String content = "您的验证码为:" + code;
        return sendEmailRpcService.sendSimpleMail(email, subject, content);
    }


    /**
     * 发送html格式的邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    public ResultVO sendHtmlMail(String to, String subject, String content) {
        return sendEmailRpcService.sendHtmlMail(to, subject, content);
    }

    /**
     * 发送带附件的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    public ResultVO sendAttachmentsMail(String to, String subject, String content, String filePath) {
        return sendEmailRpcService.sendAttachmentsMail(to, subject, content, filePath);
    }

    /**
     * 发送嵌入静态资源（一般是图片）的邮件
     *
     * @param to
     * @param subject
     * @param content 邮件内容，需要包括一个静态资源的id，比如：<img src=\"cid:rscId01\" >
     * @param rscPath 静态资源路径和文件名
     * @param rscId   静态资源id
     */
    public ResultVO sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        return sendEmailRpcService.sendInlineResourceMail(to, subject, content, rscPath, rscId);
    }

}
