package top.canoe0.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import top.canoe0.blog.entity.user.RegularUser;

import java.util.List;

public interface RegularUserRepository extends JpaRepository<RegularUser, Integer> {
    RegularUser findRegularUserByAccount(String account);

    RegularUser findRegularUserById(int id);

    @Modifying
    @Transactional
    void deleteRegularUserById(int id);


    List<RegularUser> findRegularUserByAccountLike(String keyword);

    List<RegularUser> findRegularUserByDetailLike(String keyword);

}
