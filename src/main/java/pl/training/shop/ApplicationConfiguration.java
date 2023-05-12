package pl.training.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.training.shop.payments.adapters.time.SystemTimeProvider;
import pl.training.shop.payments.ports.TimeProvider;

//@Profile("devlopment")
@Configuration
public class ApplicationConfiguration implements WebMvcConfigurer {

    @Bean
    public TimeProvider systemTimeProvider() {
        return new SystemTimeProvider();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedHeaders("*")
                .allowedMethods("*");
    }

}
