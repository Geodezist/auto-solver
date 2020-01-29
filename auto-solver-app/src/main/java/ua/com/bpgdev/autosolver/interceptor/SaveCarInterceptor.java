package ua.com.bpgdev.autosolver.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SaveCarInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        logger.info("[preHandle][" + request + "]" + "[" + request.getMethod()
                + "]" + request.getRequestURI());
        return true;
    }
}
