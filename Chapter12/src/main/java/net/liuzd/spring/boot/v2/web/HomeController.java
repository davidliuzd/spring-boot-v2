package net.liuzd.spring.boot.v2.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    
    @RequestMapping({"/","/index"})
    public String index(){
        return"/index";
    }
    
    @GetMapping("/login")
    public String login() {
        //如果已经认证通过，直接跳转到首页
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/index";
        }
        return "login";
    }

    
    @PostMapping("/login")
    public Object login(String username, String password, Model model) {
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            //shiro帮我们匹配密码什么的，我们只需要把东西传给它，它会根据我们在UserRealm里认证方法设置的来验证
            user.login(token);
            return "redirect:/index";
        } catch (UnknownAccountException e) {
            //账号不存在和下面密码错误一般都合并为一个账号或密码错误，这样可以增加暴力破解难度
            model.addAttribute("message", "账号不存在！");
        } catch (DisabledAccountException e) {
            model.addAttribute("message", "账号未启用！");
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("message", "密码错误！");
        } catch (Throwable e) {
            model.addAttribute("message", "未知错误！");
        }
        return "login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }
    
    
    @GetMapping("/logout")
    public String logout() {        
        SecurityUtils.getSubject().logout();
        return "login";
    }

}