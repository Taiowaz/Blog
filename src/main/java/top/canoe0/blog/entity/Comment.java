package top.canoe0.blog.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private int commentId;

    private String account;

    private int replyCommentId;

    private String commentContent;


    @ManyToOne(cascade = CascadeType.ALL)
    private Article article;

}
