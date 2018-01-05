package com.forever7776.life.remote.rpc;


import com.forever7776.life.remote.common.vo.ResultVO;

/**
 * @author kz
 * @date 2018-01-03
 */
public interface SendEmailRpcService {

    /**
     * 发送简单邮件
     *
     * @param to
     * @param subject
     * @param content
     * @return
     */
    ResultVO sendSimpleMail(String to, String subject, String content);

    /**
     * 发送html格式的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @return
     */
    ResultVO sendHtmlMail(String to, String subject, String content);

    /**
     * 发送带附件的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param filePath
     * @return
     */
    ResultVO sendAttachmentsMail(String to, String subject, String content, String filePath);

    /**
     * 发送嵌入静态资源（一般是图片）的邮件
     *
     * @param to
     * @param subject
     * @param content 邮件内容，需要包括一个静态资源的id，比如：<img src=\"cid:rscId01\" >
     * @param rscPath 静态资源路径和文件名
     * @param rscId   静态资源id
     */
    ResultVO sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
}
