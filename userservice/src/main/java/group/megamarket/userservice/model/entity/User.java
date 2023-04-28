package group.megamarket.userservice.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    private Long id;

    @Column(name = "username")
    private String username;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Request> requests;
}
