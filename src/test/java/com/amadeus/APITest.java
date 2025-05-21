package com.amadeus;

import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class APITest 
    
{
    @Test
    public void verifyStatusCode() {
        given()
            .when()
                .get("https://api.github.com")
            .then()
                .statusCode(200);
    }
    
    @Test
    public void verifyUserId() {
        given()
            .when().get("https://jsonplaceholder.typicode.com/posts/1")
            .then().body("userId", equalTo(1));
    }
    
    @Test
    public void verifyContentTypeHeader(){
        given()
            .when().get("https://jsonplaceholder.typicode.com/posts")
            .then().header("Content-Type", "application/json; charset=utf-8");
    }
    
    @Test
    public void postRequest(){
        String requestBody = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";
            given()
                .contentType("application/json")
                .body(requestBody)
            .when()
                .post("https://jsonplaceholder.typicode.com/posts")
            .then()
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1))
                .statusCode(201);
    }
    
    @Test
    public void verifyStatusCode2(){
        given()
            .when()
                .get("https://jsonplaceholder.typicode.com/comments/5")
            .then().statusCode(200);
    }
    
    @Test
    public void verifyUserId2(){
        given()
            .when().get("https://jsonplaceholder.typicode.com/todos")
            .then().body("[0].userId", equalTo(1));
            // Verifica que el primer elemento de la lista tenga userId igual a 1 con el index [0], o el que necesites.
    }
    
    @Test
    public void verifyMoreThanFivePostsforUserId(){
        given()
            .when().get("https://jsonplaceholder.typicode.com/posts")
            .then().body("size()", greaterThan(5));
            // Verifica que el número de posts para userId 1 sea igual a 5.
    }
    
    
}
