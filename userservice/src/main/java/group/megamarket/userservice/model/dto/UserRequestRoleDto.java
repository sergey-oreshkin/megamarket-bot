package group.megamarket.userservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestRoleDto {
    private Long userId;
    private Boolean isAdmin;
    private Boolean isSeller;
}
