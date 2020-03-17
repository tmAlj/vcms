package com.wsd.filter;

import com.google.code.kaptcha.Constants;
import com.wsd.exception.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图片验证码过滤器
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String contextPath = httpServletRequest.getContextPath();
        //登录请求验证
        if (StringUtils.equals(contextPath + "/login", httpServletRequest.getRequestURI())
                && StringUtils.equalsIgnoreCase("post", httpServletRequest.getMethod())) {
            try {
                validate(httpServletRequest);
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /**
     * 验证码匹配方法
     *
     * @param httpServletRequest
     * @throws ValidateCodeException
     */
    private void validate(HttpServletRequest httpServletRequest) throws ValidateCodeException {
        String requestCode = httpServletRequest.getParameter("code");
        String code = httpServletRequest.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString();
        if (StringUtils.isBlank(requestCode) || StringUtils.isBlank(code) || !StringUtils.equals(requestCode, code)) {
            throw new ValidateCodeException("验证码不匹配！");
        }
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }
}

