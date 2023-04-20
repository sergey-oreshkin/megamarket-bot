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
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private Set<Request> requests;
}
