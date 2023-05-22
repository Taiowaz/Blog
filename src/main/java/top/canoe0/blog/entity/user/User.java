package top.canoe0.blog.entity.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class User {
    @Id
    @GeneratedValue
    protected int id;
    @NotBlank
    @Length(min = 2, max = 10)
    protected String account;
    @Length(min = 6, max = 20)
    protected String password;
    @Lob
    protected String avatarUrl;

    protected LocalDateTime registerTime;
    protected LocalDateTime lastModifyTime;

    public User() {
    }
}
