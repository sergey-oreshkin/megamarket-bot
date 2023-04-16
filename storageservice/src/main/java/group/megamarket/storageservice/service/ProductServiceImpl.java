package group.megamarket.storageservice.service;

import group.megamarket.storageservice.model.User;
import group.megamarket.storageservice.repository.ProductRepository;
import group.megamarket.storageservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

@Service
@RequiredArgsConstructor
@WebService(
        name = "ProductService",
        portName = "ProductPort",
        targetNamespace = "http://storageservice",
        endpointInterface = "group.megamarket.storageservice.service.ProductService")
public class ProductServiceImpl implements ProductService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public void saveUser(Long id) {
        User user = new User(id);
        userRepository.save(user);
    }

}
