package com.forever7776.life.core.service.rpc;


import com.forever7776.life.remote.common.vo.ResultVO;
import com.forever7776.life.remote.rpc.SendEmailRpcService;
import enums.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author kz
 * @date 2018-01-03
 */
@Service("sendEmailRpcService")
@Transactional
public class SendEmailRpcServiceImpl implements SendEmailRpcService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender sender;

    /**
     * 发送简单邮件
     *
     * @param to
     * @param subject
     * @param content
     * @return
     */
    @Override
    public ResultVO sendSimpleMail(String to, String subject, String content) {
        ResultVO vo = new ResultVO();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        Integer code = ResultEnum.STATUS.FAIL.getCode();
        String msg = "发送邮件异常!";
        try {
            sender.send(message);
            logger.info("简单邮件已经发送。");
            code = ResultEnum.STATUS.SUCCESS.getCode();
            msg = "发送邮件成功";
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }
        vo.setCode(code);
        vo.setMsg(msg);
        return vo;
    }

    /**
     * 发送html格式的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @return
     */
    @Override
    public ResultVO sendHtmlMail(String to, String subject, String content) {
        ResultVO vo = new ResultVO();
        Integer code = ResultEnum.STATUS.FAIL.getCode();
        String msg = "发送邮件异常!";
        MimeMessage message = sender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            sender.send(message);
            logger.info("html邮件已经发送。");
            code = ResultEnum.STATUS.SUCCESS.getCode();
            msg = "发送邮件成功";
        } catch (MessagingException e) {
            logger.error("发送html邮件时发生异常！", e);
        }
        vo.setCode(code);
        vo.setMsg(msg);
        return vo;
    }

    /**
     * 发送带附件的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param filePath
     * @return
     */
    @Override
    public ResultVO sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = sender.createMimeMessage();
        ResultVO vo = new ResultVO();
        Integer code = ResultEnum.STATUS.FAIL.getCode();
        String msg = "发送邮件异常!";
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);

            sender.send(message);
            logger.info("带附件的邮件已经发送。");
            code = ResultEnum.STATUS.SUCCESS.getCode();
            msg = "发送邮件成功";
        } catch (MessagingException e) {
            logger.error("发送带附件的邮件时发生异常！", e);
        }
        vo.setCode(code);
        vo.setMsg(msg);
        return vo;
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
    @Override
    public ResultVO sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = sender.createMimeMessage();
        ResultVO vo = new ResultVO();
        Integer code = ResultEnum.STATUS.FAIL.getCode();
        String msg = "发送邮件异常!";
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            sender.send(message);
            logger.info("嵌入静态资源的邮件已经发送。");
            code = ResultEnum.STATUS.SUCCESS.getCode();
            msg = "发送邮件成功";
        } catch (MessagingException e) {
            logger.error("发送嵌入静态资源的邮件时发生异常！", e);
        }
        vo.setCode(code);
        vo.setMsg(msg);
        return vo;
    }
}
