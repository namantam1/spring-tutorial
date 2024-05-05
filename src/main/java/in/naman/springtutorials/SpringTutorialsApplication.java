package in.naman.springtutorials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;

@SpringBootApplication(exclude = {ThymeleafAutoConfiguration.class})
public class SpringTutorialsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTutorialsApplication.class, args);
    }
}
