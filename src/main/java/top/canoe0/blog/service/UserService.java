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

    //session
    public JSONObject getSession(HttpSession session) throws Exception {
        String id = (String) session.getAttribute("id");
        String account = (String) session.getAttribute("account");
        String avatarUrl = (String) session.getAttribute("avatarUrl");
        String userType = (String) session.getAttribute("userType");

        if (id == null) {
            return null;
        }

        JSONObject sessionJSON = new JSONObject();
        sessionJSON.put("id", id);
        sessionJSON.put("account", account);
        sessionJSON.put("avatarUrl", avatarUrl);
        sessionJSON.put("userType", userType);

        return sessionJSON;
    }

    //todo 添加管理员用户
    //设置session
    public void setSession(HttpSession session, String id, String account, String avatarUrl, String userType) {
        session.setAttribute("id", id);
        session.setAttribute("account", account);
        session.setAttribute("avatarUrl", avatarUrl);
        session.setAttribute("userType", userType);
    }

    //添加或更新管理员用户(禁止用户与管理员重名)
    public Admin saveAdmin(HttpSession session, Admin admin) {
        RegularUser regularUserDB = findRegularUserByAccount(admin.getAccount());
        Admin adminDB = findAdminByAccount(admin.getAccount());
        //第一次注册
        if (admin.getId() == 0) {
            //禁止用户与管理员重名
            if (regularUserDB != null) return null;
            if (adminDB != null) return null;

            admin.setRegisterTime(LocalDateTime.now());
        } else {
            //todo 属性空值问题
            //由于加载用户信息时，不会把头像加载到页面，故需要判断传入是否非空，防止覆盖原头像
            if (admin.getAvatarUrl() == null || admin.getAvatarUrl() == "") {
                admin.setAvatarUrl(regularUserDB.getAvatarUrl());
            }
            //todo 注册时间
            //该值不变，只是前端不传该值故设置
//            admin.setRegisterTime(regularUserDB.getRegisterTime());
            //更新信息时重新设置session
            setSession(session, String.valueOf(admin.getId()),
                    admin.getAccount(), admin.getAvatarUrl(), "admin");

        }
//        admin.setLastModifyTime(LocalDateTime.now());
        return adminRepository.save(admin);
    }

    //todo 这里有缺陷，未检查是否更改信息，只要传入就会改上次修改时间
    //添加或更新普通用户
    public RegularUser saveRegularUser(HttpSession session, RegularUser regularUser) {
        RegularUser regularUserDB = findRegularUserByAccount(regularUser.getAccount());
        Admin adminDB = findAdminByAccount(regularUser.getAccount());
        //第一次注册
        if (regularUser.getId() == 0) {
            //禁止用户与管理员重名
            if (regularUserDB != null) return null;
            if (adminDB != null) return null;

            regularUser.setRegisterTime(LocalDateTime.now());
        } else {
            //由于加载用户信息时，不会把头像加载到页面，故需要判断传入是否非空，防止覆盖原头像
            if (regularUser.getAvatarUrl() == null || regularUser.getAvatarUrl() == "") {
                regularUser.setAvatarUrl(regularUserDB.getAvatarUrl());
            }
            //该值不变，只是前端不传该值故设置
            regularUser.setRegisterTime(regularUserDB.getRegisterTime());
            //更新信息时重新设置session
            setSession(session, String.valueOf(regularUser.getId()),
                    regularUser.getAccount(), regularUser.getAvatarUrl(), "regularUser");

        }
        regularUser.setLastModifyTime(LocalDateTime.now());
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

    //通过账户查找管理员
    public Admin findAdminByAccount(String account) {
        return adminRepository.findAdminByAccount(account);
    }

    //通过账户查找普通用户
    public RegularUser findRegularUserByAccount(String account) {
        return regularUserRepository.findRegularUserByAccount(account);
    }


    //通过id查找普通用户
    public RegularUser findRegularUserById(int id) {
        RegularUser regularUser = regularUserRepository.findRegularUserById(id);
        return regularUser;
    }

    //通过id查找管理员
    public Admin findAdminById(int id) {
        return adminRepository.findAdminById(id);
    }

    //   登录管理员
    public Admin loginAdmin(Admin admin, HttpSession session) {
        Admin adminDB = findAdminByAccount(admin.getAccount());
        if (adminDB == null) {
            return null;
        }

        if (adminDB.getPassword().equals(admin.getPassword())) {
            //设置session
            this.setSession(session, String.valueOf(adminDB.getId()), adminDB.getAccount(), adminDB.getAvatarUrl(), "admin");
            LoginLog loginLog = new LoginLog();
            loginLog.setAdmin(adminDB);
            loginLog.setLoginStatus("登录成功");
            logService.saveLoginLog(loginLog);
            //成功登录返回数据库查询用户
            return adminDB;
        } else {
            LoginLog loginLog = new LoginLog();
            loginLog.setAdmin(adminDB);
            loginLog.setLoginStatus("登录失败");
            //登录失败情况下一定不要把数据库查询用户返回
            return admin;
        }
    }

    //登录普通用户
    public RegularUser loginRegularUser(RegularUser regularUser, HttpSession session) {
        RegularUser regularUserDB = findRegularUserByAccount(regularUser.getAccount());


        if (regularUserDB == null) return null;

        if (regularUserDB.getPassword().equals(regularUser.getPassword())) {
            //设置session
            if (session != null) {
                this.setSession(session, String.valueOf(regularUserDB.getId()), regularUserDB.getAccount(), regularUserDB.getAvatarUrl(), "regularUser");
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
