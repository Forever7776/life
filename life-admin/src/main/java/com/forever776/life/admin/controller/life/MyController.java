package com.forever7776.life.web.controller.life;

import com.forever7776.life.core.entity.sys.SysUser;
import com.forever7776.life.web.controller.BaseController;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kz
 * @date 2017年12月29日14:32:07
 * @desc 用户首页
 */
@Controller
@RequestMapping(value = "/m")
public class MyController extends BaseController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping
    public String index(ModelMap map) {
        SysUser user = getSysUser();
        map.put("user", user);
        amqpTemplate.convertAndSend("test","test.#","hello");
        return "page/live/user/index";
    }

}
