package top.canoe0.blog.entity;

import lombok.Data;
import top.canoe0.blog.entity.user.Admin;
import top.canoe0.blog.entity.user.RegularUser;
import top.canoe0.blog.entity.user.User;

import javax.persistence.*;

@Data
@Entity
public class Like {
    @GeneratedValue
    @Id
    private int likeId;

    private boolean flag;

    @ManyToOne
    private Admin admin;

    @ManyToOne
    private RegularUser regularUser;

    @OneToOne
    private Article article;


}
