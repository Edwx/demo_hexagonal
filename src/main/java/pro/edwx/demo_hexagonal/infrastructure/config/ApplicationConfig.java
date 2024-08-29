package pro.edwx.demo_hexagonal.infrastructure.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import pro.edwx.demo_hexagonal.domain.ApplicationService;

@Configuration
@ComponentScan(
    basePackages = "pro.edwx.demo_hexagonal",
    includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ApplicationService.class)
)
public class ApplicationConfig {
}
