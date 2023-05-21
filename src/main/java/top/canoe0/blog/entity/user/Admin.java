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

@Entity
@Data
public class Admin extends User {

    public Admin() {
    }
}
