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

//todo 普通用户头像保存有问题
//todo 注册时间有问题

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

    //设置session
    public void setSession(HttpSession session, String id, String account, String avatarUrl, String userType) {
        if (session != null) {
            session.setAttribute("id", id);
            session.setAttribute("account", account);
            session.setAttribute("avatarUrl", avatarUrl);
            session.setAttribute("userType", userType);
        }
    }

    //添加或更新管理员用户(禁止用户与管理员重名)
    public Admin saveAdmin(HttpSession session, Admin admin) {
        //第一次注册
        if (admin.getId() == 0) {
            if (isAccountExist(admin.getAccount())) {
                return null;
            }
            admin.setRegisterTime(LocalDateTime.now());
        } else {
            //更新用户信息根据Id来更新
            Admin adminDB = findAdminById(admin.getId());
            //由于加载用户信息时，不会把头像加载到页面，故需要判断传入是否非空，防止覆盖原头像
            if (admin.getAvatarUrl() == null || admin.getAvatarUrl() == "") {
                admin.setAvatarUrl(adminDB.getAvatarUrl());
            }
            //该值不变，只是前端不传该值故设置
            admin.setRegisterTime(adminDB.getRegisterTime());
            //更新信息时重新设置session
            setSession(session, String.valueOf(admin.getId()),
                    admin.getAccount(), admin.getAvatarUrl(), "admin");
        }
        admin.setLastModifyTime(LocalDateTime.now());
        return adminRepository.save(admin);
    }

    //todo 这里有缺陷，未检查是否更改信息，只要传入就会改上次修改时间
    //添加或更新普通用户
    public RegularUser saveRegularUser(HttpSession session, RegularUser regularUser) {
        if (regularUser.getId() == 0) {
            if (isAccountExist(regularUser.getAccount())) {
                return null;
            }
            regularUser.setRegisterTime(LocalDateTime.now());
            System.out.println("regularUser = " + regularUser.getRegisterTime());
        } else {
            RegularUser regularUserDB = findRegularUserById(regularUser.getId());
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

    //判断账户是否存在
    public boolean isAccountExist(String account) {
        RegularUser regularUserDB = findRegularUserByAccount(account);
        Admin adminDB = findAdminByAccount(account);
        if (regularUserDB != null || adminDB != null) {
            return true;
        }
        return false;
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
            loginLog.setUserId(adminDB.getId());
            loginLog.setUserType("admin");
            loginLog.setLoginStatus("登录成功");
            logService.saveLoginLog(loginLog);
            //成功登录返回数据库查询用户
            return adminDB;
        } else {
            LoginLog loginLog = new LoginLog();
            loginLog.setUserId(adminDB.getId());
            loginLog.setUserType("admin");
            loginLog.setLoginStatus("登录失败");
            //登录失败情况下一定不要把数据库查询用户返回
            return null;
        }
    }

    //登录普通用户
    public RegularUser loginRegularUser(RegularUser regularUser, HttpSession session) {
        RegularUser regularUserDB = findRegularUserByAccount(regularUser.getAccount());


        if (regularUserDB == null) return null;

        if (regularUserDB.getPassword().equals(regularUser.getPassword())) {
            //设置session
            this.setSession(session, String.valueOf(regularUserDB.getId()), regularUserDB.getAccount(), regularUserDB.getAvatarUrl(), "regularUser");
            LoginLog loginLog = new LoginLog();
            loginLog.setUserId(regularUserDB.getId());
            loginLog.setUserType("regularUser");
            loginLog.setLoginStatus("登录成功");
            logService.saveLoginLog(loginLog);
            return regularUserDB;
        } else {
            LoginLog loginLog = new LoginLog();
            loginLog.setUserId(regularUserDB.getId());
            loginLog.setUserType("regularUser");
            loginLog.setLoginStatus("登录失败");
            return null;
        }

    }

    //删除管理员通过账户
    public void deleteAdminById(int id) {
        adminRepository.deleteAdminById(id);
    }

    //删除普通用户通过账户
    public void deleteRegularUserById(int id) {
        System.out.println("id = " + id);
        regularUserRepository.deleteRegularUserById(id);
    }
}
