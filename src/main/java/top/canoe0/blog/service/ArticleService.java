package top.canoe0.blog.service;

import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import top.canoe0.blog.entity.Article;
import top.canoe0.blog.entity.user.Admin;
import top.canoe0.blog.entity.user.RegularUser;
import top.canoe0.blog.entity.user.User;
import top.canoe0.blog.repository.ArticleRepository;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserService userService;


    //按不同字段排序查找文章
    public List<Article> listAllArticle(String orderAttr, boolean isAsc) {
        if (orderAttr == null) {
            return articleRepository.findAll();
        }
        if (isAsc) {
            return articleRepository.findAll(Sort.by(Sort.Direction.ASC, orderAttr));
        }
        return articleRepository.findAll(Sort.by(Sort.Direction.DESC, orderAttr));
    }

    //按文章名称模糊查询所有用户的文章
    public List<Article> listArticleByTitle(String keyword) {
        return articleRepository.findByArticleTitleLike("%" + keyword + "%");
    }
}
