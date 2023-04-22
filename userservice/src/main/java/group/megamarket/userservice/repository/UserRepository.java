package group.megamarket.userservice.repository;

import group.megamarket.userservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select users.id from users " +
            "inner join user_role on users.id=user_role.user_id " +
            "inner join roles on user_role.role_id=roles.id where roles.role in ('ADMIN','SELLER')", nativeQuery = true)
    List<User> findAllAdminAndSeller();
}
