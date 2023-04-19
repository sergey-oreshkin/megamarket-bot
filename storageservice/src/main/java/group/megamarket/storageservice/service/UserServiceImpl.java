package group.megamarket.storageservice.service;

import group.megamarket.storageservice.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UserServiceImpl implements UserService {

    @Qualifier("userRestTemplate")
    private final RestTemplate restTemplate;
    @Override
    public boolean userHasRole(Long userId, Role role) {
        Role[] roles = restTemplate.getForObject("/42", Role[].class);
        System.out.println(Arrays.toString(roles));
        return true;
    }
}
