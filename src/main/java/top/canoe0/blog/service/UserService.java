package top.canoe0.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.canoe0.blog.entity.log.LoginLog;
import top.canoe0.blog.entity.user.Admin;
import top.canoe0.blog.entity.user.RegularUser;
import top.canoe0.blog.repository.AdminRepository;
import top.canoe0.blog.repository.RegularUserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private RegularUserRepository regularUserRepository;
    @Autowired
    private LogService logService;


    //添加或更新管理员用户(禁止用户与管理员重名)
    public Admin saveAdmin(Admin admin) {
        if (findRegularUserByAccount(admin.getAccount()) != null) return null;
        return adminRepository.save(admin);
    }

    //添加或更新普通用户(禁止用户与管理员重名)
    public RegularUser saveRegularUser(RegularUser regularUser) {
        if (findAdminByAccount(regularUser.getAccount()) != null) return null;
        return regularUserRepository.save(regularUser);
    }

    //显示所有普通用户
    public List<RegularUser> listAllRegularUser() {
        return regularUserRepository.findAll();
    }

    //显示所有管理员用户
    public List<Admin> listAllAdmin() {
        return adminRepository.findAll();
    }

    //    查找管理员
    public Admin findAdminByAccount(String account) {
        return adminRepository.findAdminByAccount(account);
    }

    //查找普通用户
    public RegularUser findRegularUserByAccount(String account) {
        return regularUserRepository.findRegularUserByAccount(account);
    }

    //   登录管理员
    public Admin loginAdmin(Admin admin) {
        Admin adminDB = findAdminByAccount(admin.getAccount());
        if (adminDB == null) {
            return null;
        }

        if (adminDB.getPassword() == admin.getPassword()) {
            LoginLog loginLog = new LoginLog();
            loginLog.setAdmin(admin);
            loginLog.setLoginStatus("登录成功");
            logService.saveLoginLog(loginLog);
            //成功登录返回数据库查询用户
            return adminDB;
        } else {
            LoginLog loginLog = new LoginLog();
            loginLog.setAdmin(admin);
            loginLog.setLoginStatus("登录失败");
            //登录失败情况下一定不要把数据库查询用户返回
            return admin;
        }
    }

    //登录普通用户
    public RegularUser loginRegularUser(RegularUser regularUser) {
        RegularUser regularUserDB = findRegularUserByAccount(regularUser.getAccount());
        if (regularUserDB == null) return null;

        if (regularUserDB.getPassword() == regularUser.getPassword()) {
            LoginLog loginLog = new LoginLog();
            loginLog.setRegularUser(regularUser);
            loginLog.setLoginStatus("登录成功");
            logService.saveLoginLog(loginLog);
            return regularUserDB;
        } else {
            LoginLog loginLog = new LoginLog();
            loginLog.setRegularUser(regularUser);
            loginLog.setLoginStatus("登录失败");
            return regularUser;
        }

    }
}
