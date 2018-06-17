package com.shop.interceptors;

import com.shop.components.Session;
import com.shop.service.UserConnectionServices;
import com.shop.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Component
@Transactional
public class ConnectionManagement implements HandlerInterceptor {
    UserConnectionServices userConnectionServices;
    UserService userService;

    private static final long MAX_INACTIVE_SESSION_TIME = 3 * 100000; // time of the session 300s

    public ConnectionManagement(UserConnectionServices userConnectionServices, UserService userService) {
        this.userConnectionServices = userConnectionServices;
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {   // makes session
        if (httpServletRequest.isUserInRole("ROLE_USER")) {
            if (System.currentTimeMillis() - httpServletRequest.getSession().getLastAccessedTime()
                    > MAX_INACTIVE_SESSION_TIME) {
                httpServletResponse.sendRedirect("/logout");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if (httpServletRequest.getSession(true).isNew()) {
            Session session = userConnectionServices.getSession();
            session.setIp(userConnectionServices.getClientIp(httpServletRequest));
            userConnectionServices.startConnection();
        }
        if (httpServletRequest.isUserInRole("ROLE_USER")) {
            try {
                userConnectionServices.setUser(userService.getIdFromUsername(httpServletRequest.getUserPrincipal().getName()));
            } catch (NullPointerException npe) {
                return;
            }

        }
        try {
            userConnectionServices.lastAccess();
        } catch (NullPointerException npe) {
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
