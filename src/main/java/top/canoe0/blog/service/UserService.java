package top.canoe0.blog.service;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.canoe0.blog.entity.log.LoginLog;
import top.canoe0.blog.entity.user.Admin;
import top.canoe0.blog.entity.user.RegularUser;
import top.canoe0.blog.repository.AdminRepository;
import top.canoe0.blog.repository.RegularUserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private RegularUserRepository regularUserRepository;
    @Autowired
    private LogService logService;

    //session
    public JSONObject getSession(HttpSession session) throws Exception {
        String id = (String) session.getAttribute("id");
        String account = (String) session.getAttribute("account");
        String avatarBase64 = (String) session.getAttribute("avatarBase64");
        String avatarType = (String) session.getAttribute("avatarType");
        String userType = (String) session.getAttribute("userType");

        JSONObject sessionJSON = new JSONObject();
        sessionJSON.put("id", id);
        sessionJSON.put("account", account);
        sessionJSON.put("avatarBase64", avatarBase64);
        sessionJSON.put("avatarType", avatarType);
        sessionJSON.put("userType", userType);

        return sessionJSON;
    }

    //设置session
    public void setSession(HttpSession session, String id, String account, String avatarBase64, String avatarType, String userType) {
        System.out.println("setSession  Content= " + id + " " + account + " " + avatarBase64 + " " + avatarType + " " + userType);
        session.setAttribute("id", id);
        session.setAttribute("account", account);
        session.setAttribute("avatarBase64", avatarBase64);
        session.setAttribute("avatarType", avatarType);
        session.setAttribute("userType", userType);
    }

    //添加或更新管理员用户(禁止用户与管理员重名)
    public Admin saveAdmin(Admin admin) {
        if (adminRepository.findAdminByAccount(admin.getAccount()) != null) return null;
        return adminRepository.save(admin);
    }

    //添加或更新普通用户(禁止用户与管理员重名)
    public RegularUser saveRegularUser(RegularUser regularUser) {
        if (regularUserRepository.findRegularUserByAccount(regularUser.getAccount()) != null) return null;
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


    //   登录管理员
    public Admin loginAdmin(Admin admin) {
        Admin adminDB = findAdminByAccount(admin.getAccount());
        if (adminDB == null) {
            return null;
        }

        if (adminDB.getPassword().equals(admin.getPassword())) {
            //设置session
//            this.setSession(session, String.valueOf(adminDB.getId()), adminDB.getAccount(),
//                    adminDB.getAvatarBase64(), adminDB.getAvatarType(), "admin");
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
    public RegularUser loginRegularUser(RegularUser regularUser, HttpSession session) {
        RegularUser regularUserDB = regularUserRepository.findRegularUserByAccount(regularUser.getAccount());

        System.out.println("regularUserDB = " + (regularUserDB == null ? "null" : (regularUserDB.getAccount() + " " + regularUserDB.getPassword())));

        if (regularUserDB == null) return null;
        System.out.println("true = " + true);
        if (regularUserDB.getPassword().equals(regularUser.getPassword())) {
            System.out.println("regularUserDB password = " + regularUserDB.getPassword());
            System.out.println("regularUser password = " + regularUser.getPassword());
            //设置session
            if (session == null)
                System.out.println("session is null");
            else {
                this.setSession(session, String.valueOf(regularUserDB.getId()), regularUserDB.getAccount(),
                        regularUserDB.getAvatarBase64(), regularUserDB.getAvatarType(), "regularUser");
            }
            LoginLog loginLog = new LoginLog();
            loginLog.setRegularUser(regularUserDB);
            loginLog.setLoginStatus("登录成功");
            logService.saveLoginLog(loginLog);
            return regularUserDB;
        } else {
            LoginLog loginLog = new LoginLog();
            loginLog.setRegularUser(regularUserDB);
            loginLog.setLoginStatus("登录失败");
            return null;
        }

    }
}
