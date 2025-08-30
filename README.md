# 💳 Finance Service - Microservices Architecture

Bienvenido al repositorio del **Finance Service**, una solución de microservicios diseñada para la gestión de cuentas y transacciones financieras de manera eficiente, segura y escalable.  

---

## 🚀 Tecnologías Utilizadas

- **Lenguaje:** Java (Spring Boot)
- **Bases de datos:** MySQL
- **Mensajería:** Apache Kafka
- **Validación:** Jakarta Validation API
- **Mapper:** MapStruct (para DTOs y entidades)
- **Testing:** JUnit 5, Mockito, MockMvc

---

## 🏗 Arquitectura del Proyecto

El sistema está compuesto por dos microservicios principales, ambos desarrollados con **arquitectura hexagonal** para garantizar escalabilidad, mantenibilidad y separación clara de responsabilidades:  

| Microservicio       | Puerto | Descripción |
|--------------------|--------|-------------|
| `ms-customer`      | 8085   | Gestión de información de clientes. Exposición de endpoints para crear, actualizar, eliminar y consultar clientes. Arquitectura hexagonal para separación de dominio y adaptadores. |
| `ms-transactions`  | 8086   | Gestión de cuentas y transacciones. Control de movimientos, balance de cuentas y generación de reportes. Arquitectura hexagonal para escalabilidad y modularidad. |

Todos los servicios se conectan a la **base de datos MySQL `finance_service_db`** y utilizan **Kafka** en el puerto `9092` para mensajería asíncrona entre microservicios.

---

## 📦 Endpoints Disponibles

### **MS-Customer (`8085`)**

| Método | Endpoint | Descripción |
|--------|---------|-------------|
| GET    | `/v1/customer` | Listar todos los clientes |
| GET    | `/v1/customer/{id}` | Obtener cliente por ID |
| POST   | `/v1/customer` | Crear un nuevo cliente |
| PUT    | `/v1/customer` | Actualizar un cliente existente |
| DELETE | `/v1/customer/{id}` | Eliminar un cliente por ID |

---

### **MS-Transactions (`8086`)**

#### **Cuentas**

| Método | Endpoint | Descripción |
|--------|---------|-------------|
| GET    | `/v1/accounts` | Listar todas las cuentas |
| GET    | `/v1/accounts/{id}` | Obtener cuenta por ID |
| GET    | `/v1/accounts/accountNumber/{accountNumber}` | Obtener cuenta por número de cuenta |
| POST   | `/v1/accounts` | Crear una nueva cuenta |
| PUT    | `/v1/accounts` | Actualizar una cuenta existente |
| DELETE | `/v1/accounts/{id}` | Eliminar una cuenta por ID |

#### **Transacciones**

| Método | Endpoint | Descripción |
|--------|---------|-------------|
| GET    | `/v1/movements` | Listar todas las transacciones |
| POST   | `/v1/movements` | Crear una nueva transacción (depósito o retiro) |
| DELETE | `/v1/movements/{id}` | Cancelar o desactivar una transacción |

---

## 🗄 Base de Datos

- **Nombre:** `finance_service_db`
- **Motor:** MySQL
- **Tablas principales:**  
  - `tbl_adm_account` → gestión de cuentas  
  - `tbl_adm_transaction` → registro de movimientos financieros  
- **Relaciones:**  
  - Una cuenta (`Account`) puede tener múltiples transacciones (`Movement`)  
  - Cada transacción está asociada a un cliente a través de la cuenta

---

## ⚡ Kafka Integration

El sistema utiliza **Kafka** para la comunicación entre servicios, con la siguiente configuración:  

- **Puerto:** 9092
- **Uso principal:** Validación de existencia de clientes y registro de eventos de transacciones

---

## 🧪 Testing

- Pruebas unitarias implementadas con **JUnit 5** y **Mockito** siguiendo el patrón **AAA (Arrange, Act, Assert)**
- Pruebas de integración usando **MockMvc** para simular peticiones HTTP reales a los endpoints del microservicio

---

## 📝 Ejemplo de Uso

### Crear una cuenta

```bash
POST http://localhost:8086/v1/accounts
Content-Type: application/json

{
  "accountNumber": "12345678",
  "accountType": "SAVINGS",
  "availableBalance": 1000.0,
  "status": true,
  "customerId": 1
}
