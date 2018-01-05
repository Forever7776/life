package com.forever776.life.admin.controller;

import com.forever7776.life.core.service.sys.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author kz
 * @date 2017年12月1日14:32:07
 * @desc 项目首页
 */
@Controller
@RequestMapping(value = "/")
public class GlobalController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping
    public String index() {
        return "page/live/front/index";
    }

    @RequestMapping("record")
    public String life() {
        return "page/live/front/record";
    }

    @RequestMapping("community")
    public String record() {
        return "page/live/front/community";
    }

    @RequestMapping("blog")
    public String blog() {
        return "page/live/front/blog";
    }

    @RequestMapping("tool")
    public String tool() {
        return "page/live/front/tool";
    }

    @RequestMapping("about")
    public String about() {
        return "page/live/front/about";
    }

    @RequestMapping("login")
    public String login() {
        return "front/user/login1";
    }


    @RequestMapping("register")
    public String register() {
        return "front/register";
    }

    /**
     * ajax登录请求
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> submitLogin(String username, String password, Boolean rememberMe, Model model) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>(2);
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, false);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            subject.getSession().setTimeout(1000 * 60 * 60);
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功~");

        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", e.getMessage());
        }
        return resultMap;
    }

    /**
     * 退出
     *
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        try {
            //退出
            sysUserService.updateLogoutTime(getUserName());
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            logger.warn("登出系统异常");
        }
        return "redirect:/";
    }
}
