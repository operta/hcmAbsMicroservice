package ba.infostudio.com.config;

import io.github.jhipster.config.JHipsterProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JhipsterPropertiesConfiguration {
    @Bean
    public JHipsterProperties jHipsterProperties(){
        return new JHipsterProperties();
    }
}
