# Catalog Module

Este repositorio contiene el módulo **Catalog**, organizado siguiendo una arquitectura en capas: **Dominio**, **Aplicación** e **Infraestructura**.

---

## Estructura del Módulo

### 1. Dominio
En esta capa se encuentran los conceptos y términos que representan el negocio.  
Incluye:

- **Value Objects (Objetos de Valor)**
- **Aggregates**
- **Entidades**

Esta capa es independiente de cualquier detalle técnico o tecnológico.

### 2. Aplicación
Aquí se definen los **casos de uso** que coordinan las acciones que los usuarios pueden realizar.  
Ejemplo de caso de uso implementado:

- **Ordenación de productos** según criterios definidos (ventas, stock, etc.).

### 3. Infraestructura
Contiene las implementaciones concretas relacionadas con la tecnología y librerías utilizadas:

- **Endpoints de la API**
- **Repositorio con MongoDB**

---

## API

La API expone un endpoint principal:


GET /api/products/weighted

### Parámetros
- `wSales`: ponderación de las ventas.
- `wStock`: ponderación del stock disponible.

### Ejemplos de uso

1. Ordenar por ventas principalmente: http://localhost:8080/api/products/weighted?wSales=1.0&wStock=0.2
2. Ordenar por stock principalmente: http://localhost:8080/api/products/weighted?wSales=0.2&wStock=1.0
3. Ordenar alfabéticamente por nombre de producto (ambos criterios = 0.0): http://localhost:8080/api/products/weighted?wSales=0.0&wStock=0.0

### Añadir un nuevo criterio de ordenación
Para incluir un nuevo criterio, se deben realizar los siguientes pasos:

1. Modificar los parámetros del endpoint para incluir el nuevo criterio.
2. Actualizar el caso de uso correspondiente con la lógica del nuevo criterio.
3. Posiblemente modificar la capa de dominio, por ejemplo, agregando un método getter en el aggregate root para exponer la nueva propiedad del producto.

---

## Docker

Para levantar un contenedor con MongoDB, se ha creado un archivo `docker-compose.yml`.

### Requisitos
- Tener Docker instalado y corriendo.

### Comando para levantar el contenedor
```bash
docker compose up -d