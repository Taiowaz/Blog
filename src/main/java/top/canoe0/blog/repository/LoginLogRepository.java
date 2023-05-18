package top.canoe0.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.canoe0.blog.entity.log.LoginLog;

public interface LoginLogRepository extends JpaRepository<LoginLog, Integer> {
}
