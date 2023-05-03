package group.megamarket.userservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;

    @Override
    public String toString() {
        return "UserDto: " +
                "id = " + id +
                ", username = " + username;
    }
}
