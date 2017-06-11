package govsystem.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Title：登录拦截器
 * Description：对于非登录的用户，拦截一些action
 * Created by Myth on 6/10/2017.
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession httpSession = httpServletRequest.getSession();
        String roleName = (String)httpSession.getAttribute("roleName");
        if ("user".equals(roleName)||"admin".equals(roleName)) {
            return true;
        }
        String projectName = httpServletRequest.getContextPath();
        httpServletResponse.sendRedirect(projectName + "/toFrontEndIndex.action");
        return false;
    }



    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
