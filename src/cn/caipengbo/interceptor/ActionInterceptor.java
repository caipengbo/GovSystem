package cn.caipengbo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 拦截器
 * Created by Myth on 3/5/2017.
 */
public class ActionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("执行preHandler()");
        long startTime = System.currentTimeMillis();
        httpServletRequest.setAttribute("startTime", startTime);
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("执行postHandler()");
        long startTime = (Long)httpServletRequest.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        modelAndView.addObject("message",executeTime);
        System.out.println("executeTime : " + executeTime + "ms");
    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("执行afterCompletion()");
    }
}
