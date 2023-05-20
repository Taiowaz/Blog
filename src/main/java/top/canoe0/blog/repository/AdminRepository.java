package top.canoe0.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.canoe0.blog.entity.user.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findAdminByAccount(String account);
}