package com.projeto.concessionaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjetoConcessionariaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoConcessionariaApplication.class, args);
    }

}
