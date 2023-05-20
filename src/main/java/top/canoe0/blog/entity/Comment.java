package top.canoe0.blog.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "articleTypeId")
@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private int commentId;

    private String account;

    private int replyCommentId;

    private String commentContent;


    @ManyToOne
    private Article article;

}
