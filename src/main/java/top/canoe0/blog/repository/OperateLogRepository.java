package top.canoe0.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import top.canoe0.blog.entity.log.OperateLog;

public interface OperateLogRepository extends JpaRepository<OperateLog, Integer> {
    @Modifying
    @Transactional
    void deleteAllByAdminId(int adminId);
}
