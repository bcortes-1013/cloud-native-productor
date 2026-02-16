# 📡 Microservicio Productor: GPS Tracker

API Gateway encargada de recibir la telemetría de los autobuses en tiempo real.

## 📌 Funcionalidad Base
* **Rol:** Productor (Publisher).
* **Puerto:** 8080.
* **Endpoint:** `POST /api/gps`
* **Acción:** Recibe un JSON con coordenadas y lo envía a la cola `cola-gps` en RabbitMQ.

## 🛠️ Stack Tecnológico
* **Java 17 & Spring Boot Web**
* **RabbitTemplate:** Para el envío de mensajes asíncronos.