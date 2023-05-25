package top.canoe0.blog.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import top.canoe0.blog.entity.Article;
import top.canoe0.blog.entity.ArticleType;
import top.canoe0.blog.entity.Comment;
import top.canoe0.blog.entity.user.Admin;
import top.canoe0.blog.entity.user.RegularUser;
import top.canoe0.blog.entity.user.User;
import top.canoe0.blog.repository.ArticleRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleTypeService articleTypeService;

    @Autowired
    private CommentService commentService;


    //按不同字段排序查找文章 orderAttr 为排序属性
    public JSONArray listAllArticle(String orderAttr, boolean isAsc) {
        List<Article> articleList;
        if (orderAttr == null) {
            articleList = articleRepository.findAll();
        } else if (isAsc) {
            articleList = articleRepository.findAll(Sort.by(Sort.Direction.ASC, orderAttr));
        } else {
            articleList = articleRepository.findAll(Sort.by(Sort.Direction.DESC, orderAttr));
        }

        if (articleList == null) return null;
        else {
            return formatArticleList(articleList);
        }
    }

    //todo 文章接口
    public JSONArray formatArticleList(List<Article> articleList) {
        JSONArray articleListJSON = new JSONArray();
        for (Article article : articleList) {
            JSONObject articleJSON = new JSONObject();
            articleJSON.put("articleId", article.getArticleId());
            articleJSON.put("articleTitle", article.getArticleTitle());
            String account;
            String avatarUrl;
            if (article.getUserType() == "admin") {
                account = userService.findAdminById(article.getUserId()).getAccount();
                avatarUrl = userService.findAdminById(article.getUserId()).getAvatarUrl();
            } else {
                account = userService.findRegularUserById(article.getUserId()).getAccount();
                avatarUrl = userService.findRegularUserById(article.getUserId()).getAvatarUrl();
            }
            articleJSON.put("account", account);
            articleJSON.put("avatarUrl", avatarUrl);
            articleJSON.put("lastModifyTime", article.getLastModifyTime());
            articleJSON.put("releaseTime", article.getReleaseTime());

            articleListJSON.add(articleJSON);
        }
        return articleListJSON;
    }

    public JSONObject getArticleJSON(int articleId) {
        Article article = findArticleById(articleId);

        JSONObject articleJSON = new JSONObject();
        articleJSON.put("articleId", article.getArticleId());
        articleJSON.put("articleTitle", article.getArticleTitle());

        String account;
        if (article.getUserType() == "admin") {
            account = userService.findAdminById(article.getUserId()).getAccount();
        } else {
            account = userService.findRegularUserById(article.getUserId()).getAccount();
        }

        articleJSON.put("account", account);

        articleJSON.put("articleContent", article.getArticleContent());

        System.out.println(articleId);
        System.out.println("article.getArticleId() = " + article.getArticleId());
        ArticleType articleType = articleTypeService.findByArticleId(article.getArticleId());

        articleJSON.put("articleTypeId", articleType.getArticleTypeId());
        articleJSON.put("articleTypeName", articleType.getArticleTypeName());

        System.out.println("articleType = " + articleType);

        JSONArray commentJSONArray = commentService.findCommentJSONListByArticleId(articleId);

        articleJSON.put("commentList", commentJSONArray);

        return articleJSON;

    }

    //按文章名称模糊查询所有用户的文章
    public List<Article> listArticleByTitle(String keyword) {
        return articleRepository.findByArticleTitleLike("%" + keyword + "%");
    }

    //更新或新建文章
    public Article saveArticle(int userId, String userType,
                               int articleId, String articleTitle, String articleContent,
                               int articleTypeId) {
        ArticleType articleType = articleTypeService.findAllArticleTypeById(articleTypeId);
        if (articleType.getArticleId() != 0) {
            return null;
        }

        Article article;
        if (articleId == 0) {
            article = new Article();
            article.setReleaseTime(LocalDateTime.now());
        } else {
            article = findArticleById(articleId);
        }

        //设置article
        article.setArticleTitle(articleTitle);
        article.setArticleContent(articleContent);


        //设置userId
        article.setUserId(userId);
        article.setUserType(userType);

        article.setLastModifyTime(LocalDateTime.now());
        Article articleRes = articleRepository.save(article);

        //设置articleType

        articleType.setArticleId(articleRes.getArticleId());
        articleTypeService.saveArticleType(articleType);

        return article;
    }


    public Article findArticleById(int articleId) {
        return articleRepository.findByArticleId(articleId);
    }
}
