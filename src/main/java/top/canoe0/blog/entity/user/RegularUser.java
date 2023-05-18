package top.canoe0.blog.entity.user;

import lombok.Data;
import top.canoe0.blog.entity.Article;
import top.canoe0.blog.entity.Like;
import top.canoe0.blog.entity.log.LoginLog;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
//每个类单独一张表，字段都有
public class RegularUser extends User {
    private String name;
    private String gender;
    private String birthday;
    private String phoneNumber;
    private String email;
    private String detail;
    private LocalDateTime registerTime;
    private LocalDateTime lastModifyTime;

    @OneToMany(mappedBy = "regularUser")
    private Set<Article> articleSet = new HashSet<>();

    @OneToMany(mappedBy = "regularUser")
    private Set<Like> likeSet = new HashSet<>();

    @OneToMany(mappedBy = "regularUser")
    private Set<LoginLog> loginLogSet = new HashSet<>();

    public RegularUser() {
    }
}
