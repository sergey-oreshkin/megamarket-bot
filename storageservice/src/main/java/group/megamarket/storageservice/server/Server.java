package group.megamarket.storageservice.server;

import group.megamarket.storageservice.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.xml.ws.Endpoint;

/**
 * SOAP сервер
 */
@Component
@RequiredArgsConstructor
public class Server {
    public static final String DEFAULT_ADDRESS = "http://0.0.0.0:8000/soap";
    public static final String DEFAULT_NAMESPACE = "http://localhost:8000/soap";

    private final StorageService storageService;

    /**
     * Запуск сервера
     */
    public void start() {
        Endpoint.publish(DEFAULT_ADDRESS, storageService);
    }
}
