package com.kosmos.appointments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Kosmos Interview",
                description = "Appointments App",
                contact = @Contact(
                        name = "Carlos Kassab",
                        url = "https://ckassab.dev",
                        email = "me@ckassab.dev"
                ))
)
@Log4j2
public class KosmosAppointmentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(KosmosAppointmentsApplication.class, args);
    }

}
