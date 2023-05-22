package top.canoe0.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.canoe0.blog.entity.user.RegularUser;

public interface RegularUserRepository extends JpaRepository<RegularUser, Integer> {
    RegularUser findRegularUserByAccount(String account);

    RegularUser findRegularUserById(int id);
}
