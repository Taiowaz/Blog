package top.canoe0.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.canoe0.blog.entity.ArticleType;

//使用Mybatis框架时，使用的是mapper包，使用SB自带的框架时，用repository包
public interface ArticleTypeRepository extends JpaRepository<ArticleType, Integer> {
}
