package top.canoe0.blog.controller;

import com.alibaba.fastjson2.JSONArray;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping("/listAllLoginLog")
    public JSONArray listAllLoginLog() {
        return logService.listAllLoginLog();
    }

    //列出所有操作日志
    @PostMapping("/listAllOperateLog")
    public JSONArray listAllOperateLog() {
        return logService.listAllOperateLog();
    }


}
