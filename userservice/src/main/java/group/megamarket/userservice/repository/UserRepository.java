package group.megamarket.userservice.repository;

import group.megamarket.userservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u.id from users as u " +
            "inner join user_role as ur on u.id=ur.user_id " +
            "inner join roles as r on ur.role_id=r.id where r.role in ('ADMIN','SELLER')", nativeQuery = true)
    List<User> findAllAdminAndSeller();
}
