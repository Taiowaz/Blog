package top.canoe0.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.canoe0.blog.entity.user.Admin;
import top.canoe0.blog.entity.user.RegularUser;
import top.canoe0.blog.entity.user.User;
import top.canoe0.blog.service.UserService;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    //显示所有普通用户
    @GetMapping("/listAllRegularUser")
    public List<RegularUser> listAllRegularUser() {
        return userService.listAllRegularUser();
    }

    //注册或更新普通用户
    @PostMapping("/registerRegularUser")
    public RegularUser registerOrUpdeateRegularUser(RegularUser regularUser) {
        return userService.saveRegularUser(regularUser);
    }

    //??? 怎么获取当前登录用户
    @PostMapping("/addOrUpdateAdminUser")
    //添加或更新管理员用户
    public Admin addOrUpdateAdminUser(Admin admin) {
        return userService.saveAdmin(admin);
    }

    //    登录管理员用户
    @PostMapping("loginAdmin")
    @ResponseBody
    public Admin loginAdmin(User user) {
        return userService.loginAdmin((Admin) user);
    }

    @PostMapping("loginRegularUser")
    @ResponseBody
    public RegularUser loginRegularUser(User user) {
        return userService.loginRegularUser((RegularUser) user);
    }


}
