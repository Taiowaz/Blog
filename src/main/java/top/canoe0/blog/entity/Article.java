package top.canoe0.blog.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import top.canoe0.blog.entity.user.Admin;
import top.canoe0.blog.entity.user.RegularUser;
import top.canoe0.blog.entity.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "articleId")
@Data
@Entity
public class Article {
    @Id
    @GeneratedValue
    private int articleId;

    @ManyToOne
    private RegularUser regularUser;

    @ManyToOne
    private Admin admin;

    @OneToOne(mappedBy = "article")
    private Favor favor;

    private String articleTitle;

    private String articleContent;
    private LocalDateTime releaseTime;
    private LocalDateTime lastModifyTime;

    @OneToMany(mappedBy = "article")
    private Set<ArticleType> articleTypeSet = new HashSet<ArticleType>();

    @OneToMany(mappedBy = "article")
    private Set<Comment> commentSet = new HashSet<Comment>();

}
