package group.megamarket.userservice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("group.megamarket.userservice")
@PropertySource("classpath:/application.properties")
public class ApplicationConfig {
}
