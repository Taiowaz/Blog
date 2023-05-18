package top.canoe0.blog.entity.log;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class Log {
    @Id
    @GeneratedValue
    protected int logId;

    protected LocalDateTime logTime = LocalDateTime.now();

    public Log() {
    }
}
