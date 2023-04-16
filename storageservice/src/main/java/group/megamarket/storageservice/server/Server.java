package group.megamarket.storageservice.server;

import group.megamarket.storageservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.xml.ws.Endpoint;

@Component
@RequiredArgsConstructor
public class Server {
    public static final String DEFAULT_ADDRESS = "http://localhost:8000/storageservice";

    private final ProductService productService;

    public void start(){
        Endpoint.publish(DEFAULT_ADDRESS, productService);
    }

}
