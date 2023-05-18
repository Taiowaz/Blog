package top.canoe0.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.canoe0.blog.entity.log.LoginLog;
import top.canoe0.blog.entity.log.OperateLog;
import top.canoe0.blog.repository.LoginLogRepository;
import top.canoe0.blog.repository.OperateLogRepository;

import java.util.List;

@Service
public class LogService {
    @Autowired
    private LoginLogRepository loginLogRepository;

    @Autowired
    private OperateLogRepository operateLogRepository;

    //列出所有登录日志
    public List<LoginLog> listAllLoginLog() {
        return loginLogRepository.findAll();
    }

    //添加登录日志
    public LoginLog saveLoginLog(LoginLog loginLog) {
        return loginLogRepository.save(loginLog);
    }

    //列出所有操作日志
    public List<OperateLog> listAllOperateLog() {
        return operateLogRepository.findAll();
    }

    //添加操作日志
    public OperateLog saveOperateLog(OperateLog operateLog) {
        return operateLogRepository.save(operateLog);
    }
}
