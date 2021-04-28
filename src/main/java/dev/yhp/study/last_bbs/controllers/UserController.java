package dev.yhp.study.last_bbs.controllers;

import dev.yhp.study.last_bbs.dtos.UserDto;
import dev.yhp.study.last_bbs.enums.user.LoginResult;
import dev.yhp.study.last_bbs.enums.user.RegisterResult;
import dev.yhp.study.last_bbs.services.UserService;
import dev.yhp.study.last_bbs.vos.user.LoginVo;
import dev.yhp.study.last_bbs.vos.user.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/user/")
@SessionAttributes({UserDto.MODEL_NAME})
public class UserController extends StandardController{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String loginGet(@ModelAttribute(UserDto.MODEL_NAME)UserDto user) {
        if(user != null){
            return "redirect:/";
        }

        return "/user/login";
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String loginPost(
            HttpServletResponse response,
            @ModelAttribute(UserDto.MODEL_NAME)UserDto user,
            LoginVo loginVo,
            Model model) {
        if(user != null){
            return "redirect:/";
        }

        this.userService.login(loginVo);
        if(loginVo.getResult() == LoginResult.SUCCESS){
            if(loginVo.isAutoSign()){
                this.userService.putAutoSignKey(loginVo.getUser());

                Cookie cookie = new Cookie("ask", loginVo.getUser().getAutoSignKey());
                cookie.setMaxAge(60 * 60 * 24 * UserService.Config.AUTO_SIGN_VALID_DAYS);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            model.addAttribute(UserDto.MODEL_NAME, loginVo.getUser());
            return "redirect:/";
        }else{
            model.addAttribute("vo", loginVo);
            return "user/login";
        }
    }

    @RequestMapping(
            value = "/register",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String registerGet(@ModelAttribute(UserDto.MODEL_NAME)UserDto user) {
        if(user != null){
            return "redirect:/";
        }

        return "user/register";
    }

    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String registerPost(
            @ModelAttribute(UserDto.MODEL_NAME)UserDto user,
            RegisterVo registerVo,
            Model model) {
        if(user != null){
            return "redirect:/";
        }

        this.userService.register(registerVo);
        if(registerVo.getResult() == RegisterResult.SUCCESS){
            return "user/register.success";
        }else{
            model.addAttribute("vo", registerVo);
            return "user/register";
        }
    }
}
