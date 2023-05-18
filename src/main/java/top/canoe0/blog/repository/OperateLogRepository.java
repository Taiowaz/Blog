package top.canoe0.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.canoe0.blog.entity.log.OperateLog;

public interface OperateLogRepository extends JpaRepository<OperateLog, Integer> {
}
