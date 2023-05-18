package top.canoe0.blog.entity.log;

import lombok.Data;
import top.canoe0.blog.entity.user.Admin;
import top.canoe0.blog.entity.user.RegularUser;
import top.canoe0.blog.entity.user.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class LoginLog extends Log {
    private String loginStatus;

    @ManyToOne
    private RegularUser regularUser;

    @ManyToOne
    private Admin admin;

    public LoginLog() {
    }
}
