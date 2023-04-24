package group.megamarket.userservice.repository;

import group.megamarket.userservice.model.entity.Role;
import group.megamarket.userservice.model.entity.RoleEnum;
import group.megamarket.userservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select user from User user join user.roles r where r.roleEnum in (?1)")
    List<User> findAllAdminAndSeller(List<RoleEnum> roleEnums);

}
