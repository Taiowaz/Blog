package top.canoe0.blog.entity.log;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import top.canoe0.blog.entity.user.Admin;
import top.canoe0.blog.entity.user.RegularUser;
import top.canoe0.blog.entity.user.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "logId")
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
