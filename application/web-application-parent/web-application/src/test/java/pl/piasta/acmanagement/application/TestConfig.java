package pl.piasta.acmanagement.application;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@ComponentScan("pl.piasta.acmanagement")
@EntityScan("pl.piasta.acmanagement")
public class TestConfig {
}
