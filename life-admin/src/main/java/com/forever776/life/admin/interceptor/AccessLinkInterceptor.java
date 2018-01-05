package com.forever776.life.admin.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户访问链接拦截器
 * 说明：
 * 1.会务后台管理链接只允许管理员查看
 * @author kz
 * @date 2017年12月1日14:32:07
 */
public class AccessLinkInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("ctx", "http://"+request.getRemoteHost()+":"+request.getLocalPort()+"/live");
        return true;
    }


    /**
     * 判断是否需要过滤
     *
     * @param request
     * @return
     * @author zbk
     */
    private boolean isFilterUrl(HttpServletRequest request, String [] filterUrl) {
        boolean flag = false;
        int len = filterUrl.length;
        String url = request.getRequestURI();
        for (int i = 0; i < len; i++) {
            if (url.indexOf(filterUrl[i]) > -1) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}