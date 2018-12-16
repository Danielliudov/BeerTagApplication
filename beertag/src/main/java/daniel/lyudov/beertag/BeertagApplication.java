package daniel.lyudov.beertag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("daniel.lyudov.beertag.repositories")
public class BeertagApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BeertagApplication.class, args);
    }

}

