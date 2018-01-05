package com.forever776.life.admin.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import util.RandomValidateCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 验证码
 *
 * @author kz
 * @date 2017年12月1日18:55:17
 */
@Controller
@RequestMapping("/api/captcha")
public class CaptchaController {

    /**
     * 生成验证码
     *
     * @param request
     * @param response
     */
    @RequestMapping
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        //设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Cache-Control", "no-store");
        //设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0L);
        response.setContentType("image/jpeg");
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            //输出图片方法
            randomValidateCode.getRandcode(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 校验验证按
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/check")
    public void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0L);
        response.setContentType("text/html; charset=UTF-8");
        String yzm = request.getParameter("imageCode");
        String sessionYzm = request.getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY).toString();

        PrintWriter out = response.getWriter();
        if (!sessionYzm.equalsIgnoreCase(yzm)) {
            out.print("0");
        } else {
            out.print("1");
        }
        out.flush();
        out.close();
    }
}
