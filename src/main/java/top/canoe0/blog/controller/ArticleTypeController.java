package top.canoe0.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/addArticleType")
    public ArticleType addArticleType(int userId, String userType, int articleTypeId, String articleTypeName) {
        ArticleType articleType = new ArticleType();
        if (articleTypeId != 0) {
            articleType.setArticleTypeId(articleTypeId);
        }
        articleType.setUserId(userId);
        articleType.setUserType(userType);
        articleType.setArticleTypeName(articleTypeName);
        ArticleType articleTypeRes = articleTypeService.saveArticleType(articleType);

        return articleTypeRes;
    }

    @PostMapping("/deleteArticleTypeById")
    public String deleteArticleTypeById(int articleTypeId) {
        return articleTypeService.deleteArticleTypeById(articleTypeId);
    }

    //根据用户id查找文章类型
    @PostMapping("/listArticleTypeByUser")
    public List<ArticleType> listArticleTypeByUserId(@RequestParam int userId, @RequestParam String userType) {
        return articleTypeService.findAllArticleTypeByUserId(userId, userType);
    }
}
