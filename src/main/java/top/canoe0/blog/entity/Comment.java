package top.canoe0.blog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private int commentID;

    private String account;

    private int replyCommentID;

    private String commentContent;


    @ManyToOne
    private Article article;

}
