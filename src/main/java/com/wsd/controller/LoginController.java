package com.wsd.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.wsd.config.CaptchaConfig;
import com.wsd.entity.Menu;
import com.wsd.service.MenuService;
import com.wsd.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 登录controller
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-15 13:34
 * @updateDate 2020-3-15 13:34
 */
@RestController
public class LoginController {

    @Autowired
    private DefaultKaptcha defaultKaptcha; //验证码操作对象
    @Autowired
    private MenuService menuService;

    /**
     * 登录页面跳转
     *
     * @return
     */
    @GetMapping("/login_p")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    /**
     * 首页页面跳转
     *
     * @return
     */
    @GetMapping("/index")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        Long userId = SecurityUtils.getCurrentUserInfo().getUserId();
        List<Menu> menuList = menuService.getMenuListByUserId(userId);
        //查询用户菜单
        mv.addObject("menuList",menuList);
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("captcha.jpg")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //页面不用缓存
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = defaultKaptcha.createText();
        //生成图片验证码
        BufferedImage image = defaultKaptcha.createImage(text);
        //验证码存入session，用于登录时做对比
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        //输出验证码
        ImageIO.write(image, "jpg", out);
    }

}
