package kz.project.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class SecurityServiceApplication {

    public static void main(String[] args) throws TelegramApiException {

        SpringApplication.run(SecurityServiceApplication.class, args);
    }

}
