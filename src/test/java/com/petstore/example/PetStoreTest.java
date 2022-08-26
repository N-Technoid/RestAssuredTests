package com.petstore.example;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;


public class PetStoreTest {

    @Test
    public void test_find_pets_by_status_is_successful() {
        testInput();
        given().
            param("status", "available").
            get(baseURI + "/pet/findByStatus").
        then().
            assertThat().statusCode(200).
            statusLine("HTTP/1.1 200 OK");
    }

    @Test @Ignore
    public void test_find_pets_by_status_is_returning_available_pet() {
        testInput();
        given().
            param("status", "available").
            get(baseURI + "/pet/findByStatus").
        then().
            statusCode(200);
    }

    @Test
    public void test_add_new_pet_to_store() {
        testInput();

        JSONObject newPet = new JSONObject();
        newPet.put("name","Fluffy");
        newPet.put("id",12345678);
        newPet.put("status","available");

        given().
            header("Content-Type","application/json").
            contentType(ContentType.JSON).
            accept(ContentType.JSON).
            body(newPet.toJSONString()).
        when().
            post(baseURI+"/pet").
        then().
            statusCode(200);
    }

    private void testInput() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }


}