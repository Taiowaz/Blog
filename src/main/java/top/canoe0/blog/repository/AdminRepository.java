package top.canoe0.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import top.canoe0.blog.entity.user.Admin;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findAdminByAccount(String account);

    Admin findAdminById(int id);

    @Modifying
    @Transactional
    void deleteAdminById(int id);
}
