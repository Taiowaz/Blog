package top.canoe0.blog.entity.log;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import javax.persistence.Entity;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "logId")
@Entity
@Data
public class LoginLog extends Log {
    private String loginStatus;

    private int userId;

    private String userType;

    public LoginLog() {
    }
}
