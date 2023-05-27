package top.canoe0.blog.controller;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.canoe0.blog.entity.log.LoginLog;
import top.canoe0.blog.entity.user.Admin;
import top.canoe0.blog.entity.user.RegularUser;
import top.canoe0.blog.entity.user.User;
import top.canoe0.blog.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //session
    @GetMapping("/getSession")
    public JSONObject getSession(HttpSession session) throws Exception {
        JSONObject sessionJSON = userService.getSession(session);
        return sessionJSON;
    }

    //注销
    @GetMapping("/logout")
    public void logout(HttpSession session) {
        userService.logout(session);
    }

    //显示所有普通用户
    @GetMapping("/listAllRegularUser")
    public List<RegularUser> listAllRegularUser() {
        return userService.listAllRegularUser();
    }

    @GetMapping("/listAllAdmin")
    public List<Admin> listAllAdmin() {
        return userService.listAllAdmin();
    }

    //注册或更新普通用户
    @PostMapping("/registerRegularUser")
    public RegularUser registerRegularUser(HttpSession session, @RequestParam String account, @RequestParam String password) {
        RegularUser regularUser = new RegularUser();
        regularUser.setAccount(account);
        regularUser.setPassword(password);
        RegularUser regularUserRes = userService.saveRegularUser(session, regularUser);
        return regularUserRes;
    }

    @PostMapping("/updateRegularUser")
    public RegularUser updateRegularUser(HttpSession session, @RequestBody RegularUser regularUser) {
        RegularUser regularUserRes = userService.saveRegularUser(session, regularUser);
        return regularUserRes;
    }

    @PostMapping("/addAdmin")
    //添加管理员用户
    public Admin addAdmin(HttpSession session, @RequestParam String account, @RequestParam String password) {
        Admin admin = new Admin();
        admin.setAccount(account);
        admin.setPassword(password);
        return userService.saveAdmin(session, admin);
    }

    @PostMapping("/updateAdmin")
    public Admin updateAdmin(HttpSession session, @RequestBody Admin admin) {
        return userService.saveAdmin(session, admin);
    }
    //todo 登录与注册代码可优化

    //登录管理员用户
    @PostMapping("loginAdmin")
    public Admin loginAdmin(@RequestParam String account, @RequestParam String password, HttpSession session) {
        Admin admin = new Admin();
        admin.setAccount(account);
        admin.setPassword(password);
        return userService.loginAdmin(admin, session);
    }

    @PostMapping("/loginRegularUser")
    public RegularUser loginRegularUser(@RequestParam String account,
                                        @RequestParam String password, HttpSession session) {
        RegularUser regularUser = new RegularUser();
        regularUser.setAccount(account);
        regularUser.setPassword(password);
        return userService.loginRegularUser(regularUser, session);
    }

    @PostMapping("/findRegularUserById")
    public RegularUser findRegularUserById(@RequestParam int id) {
        return userService.findRegularUserById(id);
    }

    @PostMapping("/findAdminById")
    public Admin findAdminById(@RequestParam int id) {
        return userService.findAdminById(id);
    }

    @PostMapping("/deleteRegularUserById")
    public void deleteRegularUserById(@RequestParam int id, HttpSession session) {
        userService.deleteRegularUserById(session, id);
    }

    @PostMapping("/deleteAdminById")
    public String deleteAdminById(@RequestParam int id, HttpSession session) {
        if (session.getAttribute("id").equals(String.valueOf(id))) return "false";
        userService.deleteAdminById(session, id);
        return "true";
    }

    @PostMapping("/updateAdminPassword")
    public Admin updateAdminPassword(HttpSession session, @RequestParam int id, @RequestParam String password) {
        Admin admin = userService.findAdminById(id);
        admin.setPassword(password);
        return userService.saveAdmin(session, admin);
    }

    @PostMapping("/updateRegularUserPassword")
    public RegularUser updateRegularUserPassword(HttpSession session, @RequestParam int id,
                                                 @RequestParam String password) {
        RegularUser regularUser = userService.findRegularUserById(id);
        regularUser.setPassword(password);
        return userService.saveRegularUser(session, regularUser);
    }


}
