package com.shop.interceptors;

import com.shop.components.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Mike on 22.08.2017.
 */
@Component
public class SessionManagement implements HandlerInterceptor {

    @Autowired
    private Session session;

    @Override
    @Transactional
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(true);

        if (request
                .getAttribute("javax.servlet.error.status_code") != null) {

            response.sendRedirect("/errors?nr=" + request
                    .getAttribute("javax.servlet.error.status_code"));
        }

        if (session.getAttribute("sesh") == null) {
            session.setAttribute("sesh", this.session);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
