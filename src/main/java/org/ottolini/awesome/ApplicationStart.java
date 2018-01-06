package org.ottolini.awesome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//----------------------------
@SpringBootApplication // @Configuration + @EnableAutoConfiguration + @ComponentScan
//@ComponentScan //(basePackages = "org.ottolini.awesome.endpoint")
//@Configuration
//@EnableAutoConfiguration
//---------------------------

public class ApplicationStart {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }
}
