package com.wsd.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsd.entity.LoginLog;
import com.wsd.exception.ValidateCodeException;
import com.wsd.filter.ValidateCodeFilter;
import com.wsd.service.LoginLogService;
import com.wsd.service.impl.LoginServiceImpl;
import com.wsd.utils.ClientUtils;
import com.wsd.utils.ResultData;
import com.wsd.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * spring security配置类
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginServiceImpl loginService;
    @Autowired
    UserSecurityMetadataSource securityMetadataSource;
    @Autowired
    UserAccessDecisionManager accessDecisionManager;
    @Autowired
    UserAccessDeniedHandler userAccessDeniedHandler;
    @Autowired
    LoginLogService loginLogService;

    /*配置密码策略*/
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SessionRegistry sessionRegistry(){
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

    /*验证用户名密码*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    /*过滤静态资源*/
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/favicon.ico", "/login_p", "/captcha.jpg");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        //自定义验证码过滤器处理失败逻辑
        validateCodeFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest req,
                                                HttpServletResponse resp,
                                                AuthenticationException e) throws IOException {
                ResultData resultData = null;
                resp.setContentType("application/json;charset=utf-8");
                if (e instanceof ValidateCodeException) {
                    resultData = ResultData.error().put("msg", "验证码不匹配!");
                } else {
                    resultData = ResultData.error().put("msg", "验证码匹配失败!");
                }
                ObjectMapper om = new ObjectMapper();
                PrintWriter out = resp.getWriter();
                out.write(om.writeValueAsString(resultData));
                out.flush();
                out.close();
            }
        });
        //匹配用户权限
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class).
                authorizeRequests()
                /*.anyRequest().authenticated()*/
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(securityMetadataSource);
                        o.setAccessDecisionManager(accessDecisionManager);
                        return o;
                    }
                })
                //配置无权限时输出内容
                .and()
                .exceptionHandling()
                .accessDeniedHandler(userAccessDeniedHandler)
                .and()
                .formLogin().loginPage("/login_p").loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                //处理登录失败
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req,
                                                        HttpServletResponse resp,
                                                        AuthenticationException e) throws IOException {
                        ResultData resultData = null;
                        String msg = "";
                        resp.setContentType("application/json;charset=utf-8");
                        if (e instanceof BadCredentialsException ||
                                e instanceof UsernameNotFoundException) {
                            resultData = ResultData.error().put("msg", "账户名或者密码输入错误!");
                            msg = "账户名或者密码输入错误!";
                        } else if (e instanceof LockedException) {
                            resultData = ResultData.error().put("msg", "账户被锁定，请联系管理员!");
                            msg = "账户被锁定!";
                        } else if (e instanceof CredentialsExpiredException) {
                            resultData = ResultData.error().put("msg", "密码过期，请联系管理员!");
                            msg = "密码过期!";
                        } else if (e instanceof AccountExpiredException) {
                            resultData = ResultData.error().put("msg", "账户过期，请联系管理员!");
                            msg = "账户过期!";
                        } else if (e instanceof DisabledException) {
                            resultData = ResultData.error().put("msg", "账户被禁用，请联系管理员!");
                            msg = "账户被禁用!";
                        } else {
                            resultData = ResultData.error().put("msg", "登录失败!");
                            msg = "未知异常!";
                        }

                        LoginLog loginLog = writeLoginLog(req, msg);
                        loginLogService.addLoginLog(loginLog);

                        ObjectMapper om = new ObjectMapper();
                        PrintWriter out = resp.getWriter();
                        out.write(om.writeValueAsString(resultData));
                        out.flush();
                        out.close();
                    }
                })
                //处理登录成功
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req,
                                                        HttpServletResponse resp,
                                                        Authentication auth) throws IOException {

                        LoginLog loginLog = writeLoginLog(req, "登录成功！");
                        loginLogService.addLoginLog(loginLog);

                        ResultData resultData = ResultData.ok().put("userInfo", SecurityUtils.getCurrentUserInfo());
                        resp.setContentType("application/json;charset=utf-8");
                        ObjectMapper om = new ObjectMapper();
                        PrintWriter out = resp.getWriter();
                        out.write(om.writeValueAsString(resultData));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                //处理用户退出
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {

                        LoginLog loginLog = writeLoginLog(req, "注销成功！");
                        loginLogService.addLoginLog(loginLog);

                        resp.setContentType("application/json;charset=utf-8");
                        ResultData.ok("注销成功!");
                        ObjectMapper om = new ObjectMapper();
                        PrintWriter out = resp.getWriter();
                        out.write(om.writeValueAsString("注销成功!"));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and().csrf().disable()
                //解决iframe不能加载问题
                .headers().frameOptions().disable();
        http.sessionManagement().maximumSessions(1).expiredUrl("/login_p").maxSessionsPreventsLogin(true).sessionRegistry(sessionRegistry());
    }

    /**
     * 封装登录日志信息
     * @param req
     * @param msg
     * @return
     * @throws UnsupportedEncodingException
     */
    public static LoginLog writeLoginLog(HttpServletRequest req, String msg) throws UnsupportedEncodingException {
        String ipaddr = ClientUtils.getIpAddr(req);
        //记录用户登录信息
        String userName =req.getParameter("username");
        String ip = ClientUtils.getIpAddr(req);
        String browser = ClientUtils.getRequestBrowserInfo(req);
        String system = ClientUtils.getRequestSystemInfo(req);
        String location = ClientUtils.getLocationInfo(ipaddr,"GBK");
        Date loginTime = new Date();
        String info = msg;
        String hostName = ClientUtils.getHostName(ipaddr);

        LoginLog loginLog = new LoginLog();
        loginLog.setUserName(userName);
        loginLog.setIp(ip);
        loginLog.setBrowser(browser);
        loginLog.setSystem(system);
        loginLog.setLocation(location);
        loginLog.setCreateTime(loginTime);
        loginLog.setInfo(info);
        loginLog.setHostName(hostName);
        return loginLog;
    }
}
