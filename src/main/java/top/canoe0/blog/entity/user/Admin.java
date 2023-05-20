package top.canoe0.blog.entity.user;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import top.canoe0.blog.entity.Article;
import top.canoe0.blog.entity.Favor;
import top.canoe0.blog.entity.log.LoginLog;
import top.canoe0.blog.entity.log.OperateLog;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Data
public class Admin extends User {

    @OneToMany(mappedBy = "admin")
    private Set<Article> articleSet = new HashSet<>();

    @OneToMany(mappedBy = "admin")
    private Set<Favor> favorSet = new HashSet<>();

    @OneToMany(mappedBy = "admin")
    private Set<LoginLog> loginLogSet = new HashSet<>();

    @OneToMany(mappedBy = "admin")
    private Set<OperateLog> operateLogSet = new HashSet<>();

    public Admin() {
    }
}
