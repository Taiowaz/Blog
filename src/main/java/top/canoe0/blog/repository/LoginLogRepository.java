package top.canoe0.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import top.canoe0.blog.entity.log.LoginLog;

public interface LoginLogRepository extends JpaRepository<LoginLog, Integer> {
    @Modifying
    @Transactional
    void deleteAllByUserIdAndUserType(int userId, String userType);
}
