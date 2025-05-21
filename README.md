RestAssured - Pruebas de API REST Simplificadas

![Rest Assured Logo](https://rest-assured.io/img/logo-transparent.png)

## 📋 Descripción

Este proyecto demuestra el uso de **Rest Assured**, una poderosa biblioteca Java que facilita el testing de APIs REST. Rest Assured permite escribir pruebas de API expresivas y legibles, siguiendo un estilo de programación BDD (Behavior-Driven Development) con su sintaxis `given-when-then`.

## ✨ Características

- **Sintaxis fluida e intuitiva** para pruebas de API
- **Integración perfecta con JUnit** y otros frameworks de testing
- **Validación simplificada** de respuestas JSON, XML y HTML
- **Soporte para autenticación** (OAuth, certificados, etc.)
- **Verificación de esquemas** JSON y XML

## 🛠️ Requisitos previos

- Java JDK 11 o superior
- Maven o Gradle como herramienta de construcción
- Conocimientos básicos de APIs REST y JSON

## 🔧 Dependencias

Añade estas dependencias a tu archivo `pom.xml`:

```xml
<dependencies>
    <!-- Rest Assured -->
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>5.4.0</version>
        <scope>test</scope>
    </dependency>
    
    <!-- JUnit 5 -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.8.1</version>
        <scope>test</scope>
    </dependency>
    
    <!-- Hamcrest para aserciones -->
    <dependency>
        <groupId>org.hamcrest</groupId>
        <artifactId>hamcrest</artifactId>
        <version>2.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## 📚 Ejemplos de uso

### 1. Verificación básica de código de estado

```java
@Test
public void verifyStatusCode() {
    given()
        .when()
            .get("https://api.github.com")
        .then()
            .statusCode(200); // Verifica que la respuesta sea 200 OK
}
```

### 2. Verificación de propiedades JSON

```java
@Test
public void verifyJsonProperty() {
    given()
        .when()
            .get("https://jsonplaceholder.typicode.com/posts/1")
        .then()
            .body("userId", equalTo(1))
            .body("title", not(emptyString()));
}
```

### 3. Creación de recursos con POST

```java
@Test
public void createResource() {
    String requestBody = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";
    given()
        .contentType("application/json")
        .body(requestBody)
    .when()
        .post("https://jsonplaceholder.typicode.com/posts")
    .then()
        .statusCode(201)
        .body("title", equalTo("foo"))
        .body("userId", equalTo(1));
}
```

### 4. Verificación de cabeceras HTTP

```java
@Test
public void verifyContentTypeHeader() {
    given()
        .when()
            .get("https://jsonplaceholder.typicode.com/posts")
        .then()
            .header("Content-Type", "application/json; charset=utf-8");
}
```

### 5. Trabajando con colecciones

```java
@Test
public void verifyCollectionSize() {
    given()
        .when()
            .get("https://jsonplaceholder.typicode.com/posts")
        .then()
            .body("size()", greaterThan(5))
            .body("findAll{it.userId==1}.size()", greaterThan(5));
}
```

## 🧰 Patrón Given-When-Then

Rest Assured se basa en el patrón BDD que hace que los tests sean fáciles de leer y mantener:

- **Given**: Configura las precondiciones (headers, parámetros, autenticación, etc.)
- **When**: Especifica la acción a realizar (GET, POST, PUT, DELETE, etc.)
- **Then**: Define las verificaciones y aserciones a ejecutar sobre la respuesta

## 🏃‍♂️ Ejecución de los tests

```bash
# Usando Maven
mvn test

# Ejecutando una clase específica
mvn test -Dtest=APITest

# Ejecutando un método específico
mvn test -Dtest=APITest#verifyStatusCode
```

## 🌟 Buenas prácticas

1. **Organiza tus tests** según los recursos o endpoints de la API
2. **Crea métodos de ayuda** para operaciones comunes
3. **Usa RequestSpecification y ResponseSpecification** para reutilizar configuraciones
4. **Implementa un manejo adecuado de errores** para pruebas más robustas
5. **Añade logs** para facilitar la depuración

## 📈 Casos de uso avanzados

- Autenticación OAuth 2.0
- Validación de esquemas JSON/XML
- Testing de servicios SOAP
- Manejo de cookies y sesiones
- Verificación de tiempos de respuesta

## 🔗 Enlaces útiles

- [Documentación oficial de Rest Assured](https://rest-assured.io/)
- [Repositorio GitHub](https://github.com/rest-assured/rest-assured)
- [Guía completa de Rest Assured](https://github.com/rest-assured/rest-assured/wiki/Usage)
- [Javadoc](https://www.javadoc.io/doc/io.rest-assured/rest-assured/latest/io/restassured/RestAssured.html)


