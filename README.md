# 🏦 Finance Microservices System

Sistema de **microservicios financieros** basado en **arquitectura hexagonal**, compuesto por **ms-customer** y **ms-transactions**, diseñado para **escalabilidad, mantenibilidad y modularidad**.  

---

## 🌐 Servicios y Endpoints

### 1️⃣ MS-Customer (Clientes)
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/v1/customer` | Listar todos los clientes |
| GET | `/v1/customer/{id}` | Obtener cliente por ID |
| POST | `/v1/customer` | Crear un nuevo cliente |
| PUT | `/v1/customer` | Actualizar un cliente existente |
| DELETE | `/v1/customer/{id}` | Eliminar un cliente |

**Puerto:** 8085

---

### 2️⃣ MS-Transactions (Cuentas y Movimientos)
**Puerto:** 8086

#### Cuentas (Account)
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/v1/accounts` | Listar todas las cuentas |
| GET | `/v1/accounts/{id}` | Obtener cuenta por ID |
| GET | `/v1/accounts/accountNumber/{accountNumber}` | Obtener cuenta por número |
| POST | `/v1/accounts` | Crear una nueva cuenta |
| PUT | `/v1/accounts` | Actualizar una cuenta |
| DELETE | `/v1/accounts/{id}` | Eliminar una cuenta |

#### Transacciones (Transaction)
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/v1/movements` | Listar todas las transacciones |
| POST | `/v1/movements` | Crear una transacción |
| DELETE | `/v1/movements/{id}` | Desactivar una transacción |

---

## 🏗 Arquitectura

Ambos microservicios utilizan **arquitectura hexagonal**, lo que permite:

- 🔹 Separación clara entre lógica de negocio e infraestructura  
- 🔹 Adaptadores y puertos para distintos clientes y servicios externos  
- 🔹 Fácil escalabilidad y mantenimiento  
- 🔹 Pruebas unitarias y de integración más confiables  

---

## 🗄 Base de Datos

**Nombre:** `finance_service_db`  
**Motor:** MySQL  

**Tablas principales:**  
| Tabla | Descripción |
|-------|-------------|
| `tbl_adm_customer` | Gestión de clientes |
| `tbl_adm_account` | Gestión de cuentas asociadas a clientes |
| `tbl_adm_transaction` | Registro de transacciones financieras |

**Relaciones principales:**  


---

## ⚡ Kafka

- **Puerto:** 9092  
- **Uso:** Comunicación asincrónica entre microservicios  
- Permite notificar eventos como creación de cuentas o transacciones sin acoplar los servicios directamente  

---

## 🧩 Funcionalidades Clave

- Gestión completa de clientes, cuentas y transacciones  
- Validaciones:
  - Saldo insuficiente para transacciones de retiro  
  - Validación de tipos de movimiento (Depósito/Retiro)  
  - Verificación de existencia de clientes antes de crear cuentas  
- Reportes de movimientos por rango de fechas  
- API RESTful clara y consistente  
- Arquitectura hexagonal para **escalabilidad y pruebas confiables**  

---

## 🛠 Tecnologías

- **Lenguaje:** Java 17  
- **Framework:** Spring Boot  
- **Base de Datos:** MySQL  
- **Mensajería:** Kafka  
- **Validación:** Jakarta Validation  
- **Construcción:** Maven  

---

## 🧪 Pruebas

- **Unitarias:** Mockito + JUnit 5 con patrón **triple A**  
- **Integración:** Spring Boot Test para endpoints REST  
- Cobertura en lógica de negocio, controladores y adaptadores  

---

## 🚀 Ejecución del proyecto

```bash
git clone <url-del-repo>
```
1. Configurar la base de datos finance_service_db en MySQL
2. Configurar puertos y variables de Kafka en application.properties
3. Ejecutar los microservicios usando Docker Compose:
```bash
docker-compose up --build -d
```
4. Ejecutar los microservicios:
  * MS-Customer → 8085
  * MS-Transactions → 8086
4. Probar endpoints con Postman o cualquier cliente REST
---

## 📈 Beneficios
* Escalabilidad y mantenibilidad gracias a arquitectura hexagonal
* APIs consistentes y claras
* Integración asincrónica mediante Kafka
* Base de datos relacional con trazabilidad completa
* Sistema modular, robusto y confiable para operaciones financieras
