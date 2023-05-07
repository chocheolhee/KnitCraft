package Toy.KnitCraft.config;

import Toy.KnitCraft.exception.Unauthorized;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * AuthResolver 사용으로 인해 적용된 클래스 X (Interceptor 적용 기록으로 남겨둔 클래스)
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String accessToken = request.getParameter("accessToken");
        if (accessToken != null && !accessToken.equals("")) {
            request.setAttribute("userName", accessToken);
            return true;
        }

        throw new Unauthorized();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
