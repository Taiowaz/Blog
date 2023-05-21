package top.canoe0.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.canoe0.blog.entity.ArticleType;
import top.canoe0.blog.service.ArticleTypeService;

import java.util.List;

@RestController
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService articleTypeService;

    @GetMapping("/listAllArticleType")
    public List<ArticleType> listAllArticleType() {
        return articleTypeService.listAllArticleType();
    }

    //todo 待改善路径分级
    @PostMapping("/addArticleType")
    public ArticleType addArticleType(String articleTypeName) {
        System.out.println("articleTypeName = " + articleTypeName);
        ArticleType articleType = new ArticleType();
        articleType.setArticleTypeName(articleTypeName);
        return articleTypeService.saveArticleType(articleType);
    }
}
