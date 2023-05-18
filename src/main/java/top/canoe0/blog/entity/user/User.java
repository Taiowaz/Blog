package top.canoe0.blog.entity.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
    private String avatarBase64;

    private String avatarType;

    public User() {
    }
}
