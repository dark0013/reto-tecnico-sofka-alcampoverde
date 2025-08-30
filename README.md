# 🏦 Finance Microservices System

Sistema de microservicios financieros, compuesto por **ms-customer** y **ms-transactions**, desarrollado con arquitectura hexagonal para garantizar **escalabilidad, mantenibilidad y separación de responsabilidades**.  

---

## 🌐 Servicios y Endpoints

### 1️⃣ MS-Customer
- **Puerto:** 8085
- **Propósito:** Gestión de clientes
- **Principales Endpoints:**
  - `GET /v1/customer` → Listar todos los clientes
  - `GET /v1/customer/{id}` → Obtener un cliente por ID
  - `POST /v1/customer` → Crear un cliente
  - `PUT /v1/customer` → Actualizar un cliente
  - `DELETE /v1/customer/{id}` → Eliminar un cliente

### 2️⃣ MS-Transactions
- **Puerto:** 8086
- **Propósito:** Gestión de cuentas y transacciones financieras
- **Principales Endpoints:**
  - **Cuentas (Account):**
    - `GET /v1/accounts` → Listar todas las cuentas
    - `GET /v1/accounts/{id}` → Obtener cuenta por ID
    - `GET /v1/accounts/accountNumber/{accountNumber}` → Obtener cuenta por número
    - `POST /v1/accounts` → Crear cuenta
    - `PUT /v1/accounts` → Actualizar cuenta
    - `DELETE /v1/accounts/{id}` → Eliminar cuenta
  - **Transacciones (Transaction):**
    - `GET /v1/movements` → Listar todas las transacciones
    - `POST /v1/movements` → Crear una transacción
    - `DELETE /v1/movements/{id}` → Desactivar una transacción

---

## 🏗 Arquitectura

Ambos microservicios (**ms-customer** y **ms-transactions**) utilizan **arquitectura hexagonal**, lo que permite:

- Separar la lógica de negocio de la infraestructura
- Facilitar la escalabilidad horizontal y vertical
- Permitir la implementación de adaptadores y puertos para distintos clientes y servicios externos
- Garantizar pruebas unitarias y de integración más limpias y confiables

---

## 🗄 Base de Datos

- **Nombre:** `finance_service_db`
- **Motor:** MySQL
- **Tablas principales:**  
  - `tbl_adm_customer` → Gestión de clientes  
  - `tbl_adm_account` → Gestión de cuentas, asociadas a clientes  
  - `tbl_adm_transaction` → Registro de movimientos financieros  

- **Relaciones principales:**  
  - Un **cliente** (`Customer`) puede tener múltiples **cuentas** (`Account`)  
  - Una **cuenta** puede tener múltiples **transacciones** (`Movement`)  
  - Cada **transacción** está asociada a una cuenta y a su cliente correspondiente  


---

## ⚡ Kafka

- **Puerto:** 9092
- **Uso:** Comunicación asincrónica entre microservicios
- Permite notificar eventos como la creación de cuentas o transacciones sin acoplar los servicios directamente.

---

## 🧩 Funcionalidades Clave

- Gestión completa de **clientes, cuentas y transacciones**
- Validación de reglas de negocio:
  - No permitir transacciones con saldo insuficiente
  - Validación de tipos de movimiento
  - Verificación de existencia de clientes antes de crear cuentas
- Reportes de movimientos por rango de fecha
- API RESTful bien estructurada con documentación de endpoints clara
- Arquitectura hexagonal que facilita escalabilidad y pruebas

---

## 🛠 Tecnologías

- **Lenguaje:** Java 17
- **Framework:** Spring Boot  
- **Base de Datos:** MySQL  
- **Mensajería:** Kafka  
- **Validación:** Jakarta Validation (Bean Validation)  
- **Construcción:** Maven  

---

## 📦 Pruebas

- **Unitarias:** Mockito + JUnit 5 con patrón **triple A**
- **Integración:** Spring Boot Test para endpoints REST
- **Cobertura de pruebas:** Lógica de negocio, controladores y adaptadores

---

## 🚀 Ejecución del proyecto

1. Clonar el repositorio:  
```bash
git clone <url-del-repo>
```
2. Configurar la base de datos finance_service_db en MySQL

3. Configurar los puertos y variables de Kafka en application.properties o .yml

4. Ejecutar los microservicios:

  * MS-Customer: 8085

  * MS-Transactions: 8086

5. Probar endpoints con Postman o cualquier cliente REST

--
##📈 Beneficios

Escalabilidad y mantenibilidad gracias a arquitectura hexagonal

APIs bien definidas y consistentes entre microservicios

Integración con mensajería asincrónica para desacoplar servicios

Base de datos relacional con trazabilidad completa de clientes, cuentas y transacciones
