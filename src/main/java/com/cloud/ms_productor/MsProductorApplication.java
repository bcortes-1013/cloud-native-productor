package com.cloud.ms_productor;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired; // <--- Importante
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MsProductorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsProductorApplication.class, args);
    }

    // CORRECCIÓN: Inyección de Dependencias
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/enviar")
    public String enviar(@RequestBody String mensaje) { 
        
        // Al ser String, RabbitMQ lo enviará como texto plano/JSON
        rabbitTemplate.convertAndSend("cola-ruta", mensaje);
        
        return "Mensaje enviado...";
    }
}