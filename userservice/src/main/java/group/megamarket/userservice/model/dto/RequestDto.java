package group.megamarket.userservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
    private Long id;
    private UserDto userDto;
    private RoleDto roleDto;

    @Override
    public String toString() {
        return "RequestDto: " +
                "id = " + id +
                ", userDto = " + userDto +
                ", roleDto = " + roleDto;
    }
}
