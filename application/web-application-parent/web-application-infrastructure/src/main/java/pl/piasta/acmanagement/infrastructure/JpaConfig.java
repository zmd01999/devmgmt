package pl.piasta.acmanagement.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("pl.piasta.acmanagement")
public class JpaConfig {
}
