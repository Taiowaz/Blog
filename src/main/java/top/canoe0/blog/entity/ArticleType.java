package top.canoe0.blog.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "articleTypeId")
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
