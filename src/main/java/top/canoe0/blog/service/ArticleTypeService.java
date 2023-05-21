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
}
