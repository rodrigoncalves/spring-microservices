package academy.devdojo.youtube.course;

import academy.devdojo.youtube.core.config.JwtConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@SpringBootApplication
@EntityScan({"academy.devdojo.youtube.core.domain"})
@EnableJpaRepositories({"academy.devdojo.youtube.core.repository"})
@EnableConfigurationProperties(JwtConfiguration.class)
@ComponentScan("academy.devdojo.youtube")
public class CourseApplication implements CommandLineRunner {

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("spring.datasource.url: " + env.getProperty("spring.datasource.url"));
    }
}
