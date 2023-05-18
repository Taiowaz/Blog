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

    public ArticleType saveArticleType(ArticleType articleType){
        return articleTypeRepository.save(articleType);
    }

    public List<ArticleType> listAllArticleType(){
        return articleTypeRepository.findAll();
    }
}
