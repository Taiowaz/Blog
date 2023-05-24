package top.canoe0.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.canoe0.blog.entity.ArticleType;
import top.canoe0.blog.repository.ArticleTypeRepository;

import java.util.List;

@Service
public class ArticleTypeService {
    @Autowired
    private ArticleTypeRepository articleTypeRepository;

    public ArticleType saveArticleType(ArticleType articleType) {
        if (articleTypeRepository.findArticleTypeByArticleTypeName(articleType.getArticleTypeName()) != null) {
            return null;
        }
        return articleTypeRepository.save(articleType);
    }

    public List<ArticleType> listAllArticleType() {
        return articleTypeRepository.findAll();
    }


    //删除文章类型
    public void deleteArticleTypeById(int articleTypeId) {
        articleTypeRepository.deleteById(articleTypeId);
    }


    //根据用户ID查找文章类型
    public List<ArticleType> findAllArticleTypeByUserId(int userId) {
        return articleTypeRepository.findArticleTypesByUserId(userId);
    }

    //根据Id查找文章类型
    public ArticleType findAllArticleTypeById(int articleTypeId) {
        return articleTypeRepository.findArticleTypeByArticleId(articleTypeId);
    }
}
