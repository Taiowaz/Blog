package top.canoe0.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.canoe0.blog.entity.log.LoginLog;
import top.canoe0.blog.entity.log.OperateLog;
import top.canoe0.blog.service.LogService;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

    //列出所有登入日志
    @GetMapping("/listAllLoginLog")
    public List<LoginLog> listAllLoginLog() {
        return logService.listAllLoginLog();
    }

    //列出所有操作日志
    @GetMapping("/listAllOperateLog")
    public List<OperateLog> listAllOperateLog() {
        return logService.listAllOperateLog();
    }



}
