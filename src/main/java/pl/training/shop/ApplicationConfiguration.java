package pl.training.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import pl.training.shop.time.SystemTimeProvider;
import pl.training.shop.time.TimeProvider;

@EnableAspectJAutoProxy
@ComponentScan
@Configuration
public class ApplicationConfiguration {

    @Bean
    public TimeProvider systemTimeProvider() {
        return new SystemTimeProvider();
    }

}
