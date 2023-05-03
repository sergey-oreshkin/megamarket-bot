package group.megamarket.userservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestRoleDto {
    private Long userId;
    private RoleDto roleDto;

    @Override
    public String toString() {
        return "RequestRoleDto: " +
                "userId = " + userId +
                ", roleDto = " + roleDto;
    }
}
