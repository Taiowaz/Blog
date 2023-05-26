package top.canoe0.blog.controller;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.canoe0.blog.service.ArticleService;
import top.canoe0.blog.service.ArticleTypeService;
import top.canoe0.blog.service.UserService;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @GetMapping("/loginRegister")
    public String loginRegister() {
        return "/login_register";
    }

    @GetMapping("/articleType")
    public String articleType() {
        return "/article_type";
    }

    @GetMapping("/userInfo")
    public String userInfo() {
        return "/user_info";
    }

    @GetMapping("/userList")
    public String userList() {
        return "/user_list";
    }

    @GetMapping("/editArticle")
    public String editArticle() {
        return "/edit_article";
    }

    @GetMapping("/article")
    public String article() {
        return "/article";
    }

    @GetMapping("/myArticle")
    public String myArticle() {
        return "/my_article";
    }


    @GetMapping("/search")
    public String search() {
        return "/search";
    }

    @GetMapping("/logList")
    public String logList() {
        return "/log_list";
    }

    //获取搜索匹配用户与文章
    @PostMapping("/searchContent")
    @ResponseBody
    public JSONObject getSearchContent(String keyword) {
        JSONObject resultJSON = new JSONObject();
        JSONArray regularUserJSONArray = userService.getRegularUserByAccountOrDetail(keyword);
        JSONArray articleJSONArray = articleService.listArticleByTitle(keyword);

        resultJSON.put("userList", regularUserJSONArray);
        resultJSON.put("articleList", articleJSONArray);
        return resultJSON;
    }

}
