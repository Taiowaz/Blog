package top.canoe0.blog.entity.user;


import top.canoe0.blog.entity.Article;
import top.canoe0.blog.entity.Like;
import top.canoe0.blog.entity.log.LoginLog;
import top.canoe0.blog.entity.log.OperateLog;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Admin extends User {

    @OneToMany(mappedBy = "admin")
    private Set<Article> articleSet = new HashSet<>();

    @OneToMany(mappedBy = "admin")
    private Set<Like> likeSet = new HashSet<>();

    @OneToMany(mappedBy = "admin")
    private Set<LoginLog> loginLogSet = new HashSet<>();

    @OneToMany(mappedBy = "admin")
    private Set<OperateLog> operateLogSet = new HashSet<>();

    public Admin() {
    }
}
