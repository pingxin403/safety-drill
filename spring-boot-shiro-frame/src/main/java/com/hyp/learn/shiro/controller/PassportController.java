package com.hyp.learn.shiro.controller;


import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.hyp.learn.shiro.framework.object.ResponseVO;
import com.hyp.learn.shiro.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 登录相关
 */
@Slf4j
@Controller
@RequestMapping(value = "/passport")
public class PassportController {

    @GetMapping("/login")
    public ModelAndView login(Model model) {
        return ResultUtil.view("/login");
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/signin")
    @ResponseBody
    public ResponseVO submitLogin(String username, String password, boolean rememberMe, String kaptcha) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);

        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到xxRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            currentUser.login(token);
            return ResultUtil.success("登录成功！");
        } catch (Exception e) {
            log.error("登录失败，用户名[{}]", username, e);
            token.clear();
            return ResultUtil.error(e.getMessage());
        }
    }

    /**
     * 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
     *
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/logout")
    public ModelAndView logout(RedirectAttributes redirectAttributes) {
        // http://www.oschina.net/question/99751_91561
        // 此处有坑： 退出登录，其实不用实现任何东西，只需要保留这个接口即可，也不可能通过下方的代码进行退出
        // SecurityUtils.getSubject().logout();
        // 因为退出操作是由Shiro控制的
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return ResultUtil.redirect("index");
    }

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    /**
     * 获取验证码
     *
     * @param response
     */
    @GetMapping("/getCode")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        byte[] verByte = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
            HttpSession session = httpServletRequest.getSession();

            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            session.setAttribute(Constants.KAPTCHA_SESSION_KEY, createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        verByte = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(verByte);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
