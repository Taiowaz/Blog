package top.canoe0.blog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class ArticleType {
    @Id
    @GeneratedValue
    private int articleTypeId;
    private String articleTypeName;

    public ArticleType() {
    }

    @ManyToOne
    private Article article;
}
