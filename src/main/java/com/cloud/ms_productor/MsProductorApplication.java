package com.cloud.ms_productor;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api/gps")
public class MsProductorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsProductorApplication.class, args);
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    private static final String NOMBRE_COLA = "cola-gps";

    // 1. POST: Enviar ubicación
    @PostMapping
    public String enviarUbicacion(@RequestBody String json) {
        rabbitTemplate.convertAndSend(NOMBRE_COLA, json);
        return "Ubicación ENVIADA a RabbitMQ.";
    }

    // 2. GET: Listar
    @GetMapping
    public List<String> obtenerEstado() {
        return List.of("Bus 1: En ruta", "Bus 2: En taller");
    }

    // 3. PUT: Actualizar estado (Avisa a Rabbit)
    @PutMapping("/{id}")
    public String actualizarBus(@PathVariable String id, @RequestBody String json) {
        rabbitTemplate.convertAndSend(NOMBRE_COLA, json); 
        return "Actualización del Bus " + id + " enviada a RabbitMQ.";
    }

    // 4. DELETE: Eliminar (Avisa a Rabbit)
    @DeleteMapping("/{id}")
    public String eliminarBus(@PathVariable String id) {
        String mensaje = "{ \"accion\": \"ELIMINAR\", \"idBus\": \"" + id + "\" }";
        rabbitTemplate.convertAndSend(NOMBRE_COLA, mensaje);
        return "Orden de eliminar Bus " + id + " enviada a RabbitMQ.";
    }
}