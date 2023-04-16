package group.megamarket.storageservice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("group.megamarket.storageservice")
@PropertySource("classpath:/application.properties")
public class ApplicationConfig {
}
