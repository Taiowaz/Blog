package top.canoe0.blog.entity.log;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import top.canoe0.blog.entity.user.Admin;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "logId")
@Entity
@Data
public class OperateLog extends Log {
    private String operateType;

    private int adminId;

    public OperateLog() {
    }
}
