package com.amadeus;

import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

/**
 * APITest - Suite de pruebas para demostrar el uso de Rest Assured para testing de APIs REST
 * 
 * Esta clase contiene ejemplos de tests de API usando Rest Assured, una poderosa biblioteca
 * para automatizar pruebas de APIs REST en Java. Los tests se realizan contra APIs públicas
 * de ejemplo (GitHub API y JSONPlaceholder).
 * 
 * Características demostradas:
 * - Verificación de códigos de estado HTTP
 * - Validación de cuerpos de respuesta JSON
 * - Verificación de cabeceras HTTP
 * - Peticiones POST con cuerpo JSON
 * - Uso de matchers para validaciones complejas
 */
public class APITest 
{
    /**
     * Prueba básica que verifica que la API de GitHub responde con un código 200 OK
     * 
     * El código 200 indica que la solicitud HTTP fue procesada correctamente
     * y que el servidor devolvió una respuesta exitosa.
     */
    @Test
    public void verifyStatusCode() {
        given()
            .when()
                .get("https://api.github.com")
            .then()
                .statusCode(200); // Verifica que la respuesta sea 200 OK
    }
    
    /**
     * Verifica que un post específico (ID=1) pertenezca al usuario con ID=1
     * 
     * Este test muestra cómo acceder a propiedades específicas de un objeto JSON
     * en la respuesta y verificar su valor usando el matcher 'equalTo'.
     */
    @Test
    public void verifyUserId() {
        given()
            .when().get("https://jsonplaceholder.typicode.com/posts/1")
            .then().body("userId", equalTo(1)); // Verifica que el campo userId sea igual a 1
    }
    
    /**
     * Verifica que la API responda con el tipo de contenido correcto
     * 
     * Las cabeceras HTTP proporcionan metadatos importantes sobre la respuesta.
     * Este test verifica que la API devuelve los datos en formato JSON con codificación UTF-8.
     */
    @Test
    public void verifyContentTypeHeader(){
        given()
            .when().get("https://jsonplaceholder.typicode.com/posts")
            .then().header("Content-Type", "application/json; charset=utf-8"); // Verifica el formato de respuesta
    }
    
    /**
     * Realiza una solicitud POST para crear un nuevo recurso
     * 
     * Este test demuestra cómo:
     * 1. Configurar el tipo de contenido de la solicitud
     * 2. Enviar un cuerpo JSON en la solicitud
     * 3. Verificar múltiples propiedades en la respuesta
     * 4. Comprobar que el código de estado sea 201 (Created)
     * 
     * Nota: JSONPlaceholder simula la creación pero no persiste realmente los datos.
     */
    @Test
    public void postRequest(){
        String requestBody = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";
            given()
                .contentType("application/json") // Especifica el tipo de contenido de la solicitud
                .body(requestBody) // Define el cuerpo de la solicitud
            .when()
                .post("https://jsonplaceholder.typicode.com/posts") // Realiza una petición POST
            .then()
                .body("title", equalTo("foo")) // Verifica que el título en la respuesta sea "foo"
                .body("body", equalTo("bar")) // Verifica que el cuerpo en la respuesta sea "bar"
                .body("userId", equalTo(1)) // Verifica que el userId en la respuesta sea 1
                .statusCode(201); // 201 Created - indica que el recurso se creó correctamente
    }
    
    /**
     * Verifica que un comentario específico (ID=5) esté disponible
     * 
     * Este test es similar a verifyStatusCode pero accede a un recurso diferente
     * y más específico dentro de la API de JSONPlaceholder.
     */
    @Test
    public void verifyStatusCode2(){
        given()
            .when()
                .get("https://jsonplaceholder.typicode.com/comments/5") // Obtiene el comentario con ID=5
            .then().statusCode(200); // Verifica que el recurso exista y sea accesible
    }
    
    /**
     * Verifica el userId del primer elemento en una lista de tareas (todos)
     * 
     * Este test demuestra cómo:
     * 1. Acceder a elementos de un array en la respuesta JSON usando la notación [índice]
     * 2. Verificar propiedades de elementos específicos del array
     */
    @Test
    public void verifyUserId2(){
        given()
            .when().get("https://jsonplaceholder.typicode.com/todos") // Obtiene la lista de tareas
            .then().body("[0].userId", equalTo(1)); // Verifica que el primer elemento tenga userId=1
    }
    
    /**
     * Verifica que la API devuelva más de 5 posts en total
     * 
     * Este test demuestra el uso del matcher 'greaterThan' para verificaciones numéricas
     * y la función 'size()' para contar el número de elementos en una respuesta.
     * 
     * Es útil para verificar que un endpoint que devuelve colecciones tenga
     * un mínimo de elementos esperados.
     */
    @Test
    public void verifyMoreThanFivePostsforUserId(){
        given()
            .when().get("https://jsonplaceholder.typicode.com/posts") // Obtiene todos los posts
            .then().body("size()", greaterThan(5)); // Verifica que haya más de 5 posts en total
            
        // Para verificar específicamente para un userId:
        // .then().body("findAll{it.userId==1}.size()", greaterThan(5));
    }
}
