package group.megamarket.userservice.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "roles")
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleEnum roleEnum;

    //нужно ли?
    @ToString.Exclude
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<User> users;

    @ToString.Exclude
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private Set<Request> requests;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && roleEnum == role.roleEnum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleEnum);
    }
}
