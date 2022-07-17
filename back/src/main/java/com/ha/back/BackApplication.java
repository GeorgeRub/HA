package com.ha.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
        @PropertySource(value = "classpath:application.properties")
})
public class BackApplication {

    public static void main(String[] args) {
            SpringApplication.run(BackApplication.class, args);
    }

}
