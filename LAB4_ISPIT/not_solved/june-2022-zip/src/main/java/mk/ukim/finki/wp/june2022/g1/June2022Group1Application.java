package mk.ukim.finki.wp.june2022.g1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class June2022Group1Application {

    public static void main(String[] args) {
        SpringApplication.run(June2022Group1Application.class, args);
    }


}
