package pl.training.shop;

import org.springframework.boot.actuate.autoconfigure.metrics.JvmMetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.LogbackMetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.training.shop.payments.adapters.time.SystemTimeProvider;
import pl.training.shop.payments.ports.TimeProvider;

//@Profile("devlopment")
@EnableAutoConfiguration(exclude = {
        JacksonAutoConfiguration.class, JvmMetricsAutoConfiguration.class,
        LogbackMetricsAutoConfiguration.class, MetricsAutoConfiguration.class
})
@EnableWebMvc
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

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("").setViewName("index");
        registry.addViewController("index.html").setViewName("index");
        registry.addViewController("login.html").setViewName("login-form");
        registry.addViewController("payments/payment-summary").setViewName("payments/payment-summary");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
