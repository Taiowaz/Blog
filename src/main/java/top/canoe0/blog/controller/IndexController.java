package top.canoe0.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import top.canoe0.blog.service.ArticleService;
import top.canoe0.blog.service.ArticleTypeService;

@Controller
public class IndexController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String index() {
        return "/index";
    }

}
