package top.canoe0.blog.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import top.canoe0.blog.entity.Article;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findByArticleTitleLike(String keyword);

    Article findByArticleId(int articleId);

    List<Article> findByUserIdAndUserType(int userId,String userType);
}
