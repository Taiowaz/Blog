package top.canoe0.blog.controller;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.canoe0.blog.entity.Article;
import top.canoe0.blog.service.ArticleService;

import java.util.List;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    //显示所有文章
    @PostMapping("/listAllArticle")
    public JSONArray listAllArticle(String orderAttr, boolean isAsc) {
        return articleService.listAllArticle(orderAttr, isAsc);
    }

    //通过文章
    @PostMapping("listArticleByTitle")
    public List<Article> listArticleByTitle(String keyword) {
        return listArticleByTitle(keyword);
    }

    //保存文章
    @PostMapping("/saveArticle")
    public Article saveArticle(int userId, String userType,
                               int articleId, String articleTitle, String articleContent,
                               int articleTypeId) {
        return articleService.saveArticle(userId, userType,
                articleId, articleTitle, articleContent,
                articleTypeId);
    }

    //获取文章详细信息
    @PostMapping("/getArticleDetail")
    public JSONObject getArticleDetail(@RequestParam int articleId) {
        return articleService.getArticleJSON(articleId);
    }

    //获取特定用户文章
    @PostMapping(("/getArticleByUserIdAndUserType"))
    public JSONArray getArticleByUserId(@RequestParam int userId, @RequestParam String userType) {
        return articleService.listArticleByUserIdAndUserType(userId, userType);
    }

    @PostMapping("/deleteArticle")
    public void deleteArticle(@RequestParam int articleId) {
        articleService.deleteArticleByArticleId(articleId);
    }
}
