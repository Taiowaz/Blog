package top.canoe0.blog.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.canoe0.blog.entity.log.LoginLog;
import top.canoe0.blog.entity.log.OperateLog;
import top.canoe0.blog.repository.AdminRepository;
import top.canoe0.blog.repository.LoginLogRepository;
import top.canoe0.blog.repository.OperateLogRepository;
import top.canoe0.blog.repository.RegularUserRepository;


import java.util.List;

@Service
public class LogService {
    @Autowired
    private LoginLogRepository loginLogRepository;

    @Autowired
    private OperateLogRepository operateLogRepository;

    @Autowired
    private RegularUserRepository regularUserRepository;

    @Autowired
    private AdminRepository adminRepository;

    //列出所有登录日志
    public JSONArray listAllLoginLog() {
        List<LoginLog> loginLogList = loginLogRepository.findAll();
        return formatLoginLogList(loginLogList, null);
    }

    public JSONArray formatLoginLogList(List<LoginLog> loginLogList, List<OperateLog> operateLogList) {

        JSONArray logJSONArray = new JSONArray();

        if (loginLogList != null) {
            for (LoginLog loginLog : loginLogList) {
                JSONObject loginLogJSON = new JSONObject();

                String account;
                if (loginLog.getUserType().equals("admin")) {
                    account = adminRepository.findAdminById(loginLog.getUserId()).getAccount();
                } else {
                    account = regularUserRepository.findRegularUserById(loginLog.getUserId()).getAccount();
                }
                loginLogJSON.put("account", account);
                loginLogJSON.put("operation", loginLog.getLoginStatus());
                loginLogJSON.put("time", loginLog.getLogTime());
                logJSONArray.add(loginLogJSON);
            }
            return logJSONArray;
        } else if (operateLogList != null) {
            for (OperateLog operateLog : operateLogList) {
                JSONObject operateLogJSON = new JSONObject();

                String account = adminRepository.findAdminById(operateLog.getAdminId()).getAccount();
                operateLogJSON.put("account", account);
                operateLogJSON.put("operation", operateLog.getOperateType());
                operateLogJSON.put("time", operateLog.getLogTime());
                logJSONArray.add(operateLogJSON);
            }
            return logJSONArray;
        } else return null;
    }


    //添加登录日志
    public LoginLog saveLoginLog(LoginLog loginLog) {
        return loginLogRepository.save(loginLog);
    }

    //列出所有操作日志
    public JSONArray listAllOperateLog() {
        List<OperateLog> operateLogList = operateLogRepository.findAll();
        return formatLoginLogList(null, operateLogList);
    }

    //添加操作日志
    public OperateLog saveOperateLog(int adminId, String operateType) {
        OperateLog operateLog = new OperateLog();
        operateLog.setAdminId(adminId);
        operateLog.setOperateType(operateType);
        return operateLogRepository.save(operateLog);
    }

    //删除操作日志
    public void deleteLoginLog(int userId, String userType) {
        loginLogRepository.deleteAllByUserIdAndUserType(userId, userType);
    }

    //删除登录日志
    public void deleteOperateLog(int adminId) {
        operateLogRepository.deleteAllByAdminId(adminId);
    }
}
