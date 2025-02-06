# Inditex Pricing API

## 📖 Descripción
Este proyecto es una API REST desarrollada en **Java 21** y **Spring Boot 3.4.2** para la correcta aplicación de precios de productos para diferentes fechas y marcas.

Se implementa **Arquitectura Hexagonal** para mejorar la escalabilidad y mantenibilidad del código.

## 🚀 Tecnologías utilizadas
- **Java 21**
- **Spring Boot 3.4.2**
- **Spring Data JPA**
- **Hibernate**
- **H2 Database (en memoria)**
- **JUnit 5 & MockMvc (para tests)**
- **Lombok**
- **Maven**

---

## ⚙️ Instalación y Ejecución

### 1️⃣ **Clonar el repositorio**
```sh
git clone https://github.com/javitoro/inditex-pricing-api.git
cd inditex-pricing-api
```

### 2️⃣ **Compilar y ejecutar**
```sh
./mvnw clean install
./mvnw spring-boot:run
```

### 3️⃣ **Acceder a la API**
La API estará disponible en:
```
http://localhost:8080/api/prices
```

---

## 📌 **Uso de la API**
La API permite obtener el **precio de un producto en una fecha específica**.

### **Endpoint**
```
GET /api/prices
```

### **Parámetros**
| Parámetro         | Tipo      | Obligatorio | Descripción |
|------------------|----------|------------|------------|
| `applicationDate` | `String` | ✅ Sí      | Fecha en formato `yyyy-MM-dd HH:mm:ss` |
| `productId`      | `Integer` | ✅ Sí      | ID del producto |
| `brandId`        | `Integer` | ✅ Sí      | ID de la marca |

### **Ejemplo de uso**
```sh
curl -G --data-urlencode "applicationDate=2020-06-14 10:00:00" \
     --data-urlencode "productId=35455" \
     --data-urlencode "brandId=1" \
     "http://localhost:8080/api/prices"
```

### **Respuesta esperada (JSON)**
```json
{
    "product_id": 35455,
    "brand_id": 1,
    "price_list": 1,
    "start_date": "2020-06-14T00:00:00",
    "end_date": "2020-12-31T23:59:59",
    "price": 35.50
}
```

---

## ✅ **Casos de prueba**
El proyecto incluye **tests automatizados** con `MockMvc` para validar la API.

### **Ejecutar tests**
```sh
./mvnw test
```

### **Casos probados**
✔️ **Caso 1**: `2020-06-14 10:00:00` → `priceList = 1`, `price = 35.50`  
✔️ **Caso 2**: `2020-06-14 16:00:00` → `priceList = 2`, `price = 25.45`  
✔️ **Caso 3**: `2020-06-14 21:00:00` → `priceList = 1`, `price = 35.50`  
✔️ **Caso 4**: `2020-06-15 10:00:00` → `priceList = 3`, `price = 30.50`  
✔️ **Caso 5**: `2020-06-16 21:00:00` → `priceList = 4`, `price = 38.95`

---

## 🛠 **Arquitectura del Proyecto**
El código sigue **Arquitectura Hexagonal** para desacoplar la lógica de negocio de la infraestructura.

**Estructura de paquetes:**
```plaintext
src
└── main
    └── java
        └── com.javitoro.inditex
            ├── application
            │   ├── controller
            │   │   └── PriceController.java
            │   └── service
            │       └── PriceService.java
            ├── domain
            │   ├── model
            │   │   └── Price.java
            │   └── port
            │       └── PricePort.java
            ├── infrastructure
            │   ├── adapter
            │   │   └── PriceAdapter.java
            │   └── repository
            │       └── PriceRepository.java
            └── InditexApplication.java
└── test
    └── java
        └── com.javitoro.inditex
            ├── application
            │   └── controller
            │       └── PriceControllerTest.java
            └── InditexApplicationTests.java
```
---

## 📜 **Licencia**
**Uso restringido**  
Este software es propiedad del autor y no está autorizado para su distribución, uso comercial o modificación sin permiso explícito.

**Cualquier uso no autorizado será considerado una violación de derechos.**

---

**Desarrollado por [Javi Toro](https://github.com/javitoro)** | **Inditex Pricing API**