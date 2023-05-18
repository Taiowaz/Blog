package top.canoe0.blog.entity.log;

import lombok.Data;
import top.canoe0.blog.entity.user.Admin;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class OperateLog extends Log {
    private String operateType;

    @ManyToOne
    private Admin admin;

    public OperateLog() {
    }
}
