package group.megamarket.storageservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;

@Configuration
@ComponentScan("group.megamarket.storageservice")
@PropertySource("classpath:/application.properties")
@RequiredArgsConstructor
public class ApplicationConfig {

    private final Environment env;

    @Bean
    public RestTemplate userRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(env.getRequiredProperty("user-service.url")));
        return restTemplate;
    }
}
